package es.viewnext.processor;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import es.viewnext.dao.ParticipanteDao;
import es.viewnext.dao.factory.ParticipanteDaoFactory;
import es.viewnext.domain.Participante;

public class ParticipanteProcessor implements ItemProcessor<Participante, Participante> {

    private static final Logger log = LoggerFactory.getLogger(ParticipanteProcessor.class);

    @Autowired
    private static DataSource dataSource;

    public ParticipanteProcessor(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ParticipanteProcessor() {
    }

    // TODO descomentar y probar con participantedaofactory el insert de
    // participante
    // private static final ParticipanteDao participanteDao =
    // ParticipanteDaoFactory.getParticipanteDao(dataSource);

    @Override
    public Participante process(Participante participante) throws Exception {
        if (participante == null) {
            log.info("Participante vacío en ParticipanteProcessor");
            return null;
        }

        // TODO: añadir el resto de campos del participante al fichero
        String nombre = participante.getNombre();
        String apellido1 = participante.getApellido1();
        String apellido2 = participante.getApellido2();
        String email = participante.getEmail();
        String idioma = participante.getIdioma();

        // TODO cambiar el participanteProcessorWithoutDAO por el participantedao.select
        // List<Participante> participantes = participanteDao.select(nombre,
        // apellido1,apellido2, idioma);
        ParticipanteProcessorWithoutDAO participanteProcessorWithoutDAO = new ParticipanteProcessorWithoutDAO(
                dataSource);
        List<Participante> participantes;
        try {
            participantes = participanteProcessorWithoutDAO.select(nombre, apellido1, apellido2, idioma);
        } catch (Exception e) {
            log.error("Error con la consulta de participante, mensaje error: ", e);
            participantes = new ArrayList<>();
        }

        participante.setNombre(nombre.toUpperCase());
        participante.setApellido1(apellido1.toUpperCase());
        participante.setApellido2(apellido2.toUpperCase());
        participante.setEmail(email.toUpperCase());
        participante.setIdioma(idioma.toUpperCase());

        log.info("Procesando el participante " + participante.getNombre() + " " + participante.getApellido1() + " "
                + participante.getApellido2());

        if (participantes.size() > 0) {
            return null;
        }

        return participante;
    }
}
