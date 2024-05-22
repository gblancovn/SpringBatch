package es.viewnext.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.beans.factory.annotation.Autowired;

import es.viewnext.domain.Estadistica;
import es.viewnext.domain.Participante;

public class ParticipanteReaderListener implements ItemReadListener<Participante> {

    private static final Logger log = LoggerFactory.getLogger(ParticipanteReaderListener.class);

    @Autowired
    private Estadistica estadistica;

    public ParticipanteReaderListener(Estadistica estadistica) {
        this.estadistica = estadistica;
    }

    @Override
    public void beforeRead() {
    }

    public void afterRead() {
        estadistica.setCorrectoLectura(estadistica.getCorrectoLectura() + 1);
    }

    @Override
    public void onReadError(Exception ex) {
        log.error("Participante read error: " + ex.getMessage());
        estadistica.setErrorLectura(estadistica.getErrorLectura() + 1);
    }
}