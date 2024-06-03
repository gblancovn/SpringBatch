package es.viewnext.writer;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import es.viewnext.domain.DatosPersonalesParticipante;

public class ParticipanteBirthDateListWriter implements ItemWriter<DatosPersonalesParticipante> {

    private static final Logger LOG = LoggerFactory.getLogger(ParticipanteBirthDateListWriter.class);
    private List<DatosPersonalesParticipante> participanteBirthDates = new ArrayList<>();

    @Override
    public void write(Chunk<? extends DatosPersonalesParticipante> chunk) throws Exception {
        participanteBirthDates.addAll(chunk.getItems());
        if (participanteBirthDates != null) {
            LOG.info("Fechas guardadas de manera temporal para comprobar la mayoría de edad de los participantes.");
        } else {
            LOG.info("No se ha podido guardar los datos de las fechas de los participantes.");
        }
    }

    public List<DatosPersonalesParticipante> getParticipanteBirthDates() {
        return participanteBirthDates;
    }
}
