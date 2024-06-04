package es.viewnext.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;

import es.viewnext.domain.Estadistica;
import es.viewnext.domain.Participante;

public class ParticipanteReaderListener implements ItemReadListener<Participante> {

    private static final Logger LOG = LoggerFactory.getLogger(ParticipanteReaderListener.class);

    private Estadistica estadistica;

    public ParticipanteReaderListener(Estadistica estadistica) {
        this.estadistica = estadistica;
    }

    @Override
    public void beforeRead() {
        // document why this method is empty
    }

    @Override
    public void afterRead(Participante participante) {
        estadistica.setLecturasCorrectas(estadistica.getLecturasCorrectas() + 1);
    }

    @Override
    public void onReadError(Exception ex) {
        LOG.error("Participante read error: " + ex.getMessage());
        estadistica.setErroresLectura(estadistica.getErroresLectura() + 1);
    }
}