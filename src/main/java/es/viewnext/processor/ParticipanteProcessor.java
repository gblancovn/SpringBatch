package es.viewnext.processor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import es.viewnext.dao.ParticipanteDao;
import es.viewnext.dao.factory.ParticipanteDaoFactory;
import es.viewnext.domain.Participante;
import es.viewnext.tasklet.CreateTableTasklet;

public class ParticipanteProcessor implements ItemProcessor<Participante, Participante> {

    private static final Logger log = LoggerFactory.getLogger(ParticipanteProcessor.class);

    private static final ParticipanteDao participanteDao = ParticipanteDaoFactory.getParticipanteDao();

    @Override
    public Participante process(Participante participante) throws Exception {
        if (participante == null) {
            return null;
        }

        // TODO: añadir el resto de campos del participante al fichero

        String nombre = participante.getNombre();
        String apellido1 = participante.getApellido1();
        String apellido2 = participante.getApellido2();
        String email = participante.getEmail();
        String idioma = participante.getIdioma();

        participante.setNombre(nombre.toUpperCase());
        participante.setApellido1(apellido1.toUpperCase());
        participante.setApellido2(apellido2.toUpperCase());
        participante.setEmail(email.toUpperCase());
        participante.setIdioma(idioma.toUpperCase());

        log.info("Procesando el participante " + participante.getNombre() + " " + participante.getApellido1() + " "
                + participante.getApellido2());

        List<Participante> participantes = participanteDao.select(nombre, apellido1, apellido2, idioma);

        if (participantes == null || participantes.size() == 0) {
            return null;
        }

        // TODO: Si existe el participante con el mismo nombre y apellidos y la misma
        // fecha de inciio, no se inserta (return null)

        return participante;
    }
}
