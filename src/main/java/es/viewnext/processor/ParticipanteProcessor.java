package es.viewnext.processor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.util.StringUtils;

import es.viewnext.dao.ParticipanteDao;
import es.viewnext.domain.Atributo;
import es.viewnext.domain.Estadistica;
import es.viewnext.domain.Participante;
import es.viewnext.util.Utils;
import es.viewnext.writer.GuardarParticipanteDescartado;

public class ParticipanteProcessor implements ItemProcessor<Participante, Participante> {

    private static final Logger LOG = LoggerFactory.getLogger(ParticipanteProcessor.class);

    private Estadistica estadistica;

    private ParticipanteDao participanteDao;

    private FlatFileItemReader<Participante> datosPersonalesReader;

    private GuardarParticipanteDescartado guardarParticipanteDescartado;

    public ParticipanteProcessor(Estadistica estadistica, ParticipanteDao participanteDao,
            FlatFileItemReader<Participante> datosPersonalesReader,
            GuardarParticipanteDescartado guardarParticipanteDescartado) {
        this.estadistica = estadistica;
        this.participanteDao = participanteDao;
        this.datosPersonalesReader = datosPersonalesReader;
        this.guardarParticipanteDescartado = guardarParticipanteDescartado;
    }

    public ParticipanteProcessor() {
    }

    @Override
    public Participante process(Participante participante) throws Exception {
        if (participante == null) {
            return null;
        }

        String nombre = participante.getNombre();
        String apellido1 = participante.getApellido1();
        String apellido2 = participante.getApellido2();
        String email = participante.getEmail();
        String idioma = participante.getIdioma();

        participante.setNombre(nombre.toUpperCase());
        participante.setApellido1(apellido1.toUpperCase());
        participante.setApellido2(apellido2.toUpperCase());
        participante.setIdioma(idioma.toUpperCase());
        participante.setEmail(email.toUpperCase());

        Long idParticipante = participanteDao.nextId();

        if (idParticipante == null) {
            idParticipante = 0l;
        }

        participante.setIdParticipante(idParticipante.intValue() + 1);

        LOG.info("Procesando el participante " + participante.getNombre() + " " + participante.getApellido1() + " "
                + participante.getApellido2());

        estadistica.setParticipantesProcesados(estadistica.getParticipantesProcesados() + 1);

        if (!validarParticipante(participante)) {
            return null;
        }

        if (participante.getAtributos() != null) {
            Atributo[] atributosArray = participante.getAtributos();
            for (Atributo atributo : atributosArray) {
                if (atributo != null) {
                    atributo.setIdParticipante(participante.getIdParticipante());
                    atributo.setValor(atributo.getValor().toUpperCase());
                }
            }
        }

        return participante;
    }

    /**
     * Valida un participante según ciertos criterios.
     * 
     * @param participante el participante a validar
     * @return true si el participante es válido, false en caso contrario
     * @throws Exception si ocurre un error durante el proceso de validación
     */
    public boolean validarParticipante(Participante participante) throws Exception {
        if (participante == null) {
            return false;
        }

        if (!Utils.validateEmail(participante.getEmail())) {
            LOG.info("Error al procesar el participante: " + participante.getNombre() + " "
                    + participante.getApellido1() + " " + participante.getApellido2()
                    + ", el email tiene un formato inválido... No se ha podido dar de alta.");
            estadistica.setErroresProceso(estadistica.getErroresProceso() + 1);

            guardarParticipanteDescartado.write(participante);

            return false;
        }

        List<Participante> participantes;

        try {
            participantes = participanteDao.select(participante.getNombre(), participante.getApellido1(),
                    participante.getApellido2(), participante.getIdioma(), participante.getEmail());

        } catch (Exception e) {
            LOG.error("Error con la consulta de participante, mensaje error: ", e);
            participantes = new ArrayList<>();
        }

        if (participantes != null && !participantes.isEmpty()) {
            LOG.info("El participante " + participante.getNombre() + " " + participante.getApellido1() + " "
                    + participante.getApellido2() + " ya está dado de alta.");
            estadistica.setParticipantesDuplicados(estadistica.getParticipantesDuplicados() + 1);

            guardarParticipanteDescartado.write(participante);

            return false;
        }

        Participante datosPersonalesParticipante = buscarParticipantePorEmail(participante.getEmail());

        if (datosPersonalesParticipante != null) {
            participante.setFechaNacimiento(datosPersonalesParticipante.getFechaNacimiento());
        }

        if (participante.getFechaNacimiento() == null) {
            return false;
        }

        if (!Utils.validateAge(participante.getFechaNacimiento())) {
            LOG.info("El participante " + participante.getNombre() + " " + participante.getApellido1() + " "
                    + participante.getApellido2() + " no es mayor de edad y no se puede dar de alta.");
            estadistica.setParticipantesMenoresEdad(estadistica.getParticipantesMenoresEdad() + 1);

            guardarParticipanteDescartado.write(participante);

            return false;
        }

        estadistica.setParticipantesMayoresEdad(estadistica.getParticipantesMayoresEdad() + 1);
        estadistica.setProcesadosCorrectamente(estadistica.getProcesadosCorrectamente() + 1);

        return true;
    }

    /**
     * Busca un participante por su dirección de correo electrónico.
     * 
     * @param email la dirección de correo electrónico del participante a buscar
     * @return el participante encontrado o null si no se encuentra
     */
    private Participante buscarParticipantePorEmail(String email) {
        if (!StringUtils.hasText(email)) {
            return null;
        }

        Participante participante = null;

        try {
            datosPersonalesReader.open(new ExecutionContext());

            while ((participante = datosPersonalesReader.read()) != null) {
                if (participante.getEmail().toUpperCase().equals(email)) {
                    participante.setFechaNacimiento((participante.getFechaNacimiento()));
                    break;
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        } finally {
            datosPersonalesReader.close();
        }

        return participante;
    }

}
