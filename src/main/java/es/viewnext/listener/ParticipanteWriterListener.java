package es.viewnext.listener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.beans.factory.annotation.Autowired;

import es.viewnext.domain.Estadistica;

public class ParticipanteWriterListener implements ItemWriteListener<String> {

    private static final Logger log = LoggerFactory.getLogger(ParticipanteWriterListener.class);

    @Autowired
    private Estadistica estadistica;

    public ParticipanteWriterListener(Estadistica estadistica) {
        this.estadistica = estadistica;
    }

    public void beforeWrite(List<? extends String> items) {
    }

    public void afterWrite(List<? extends String> items) {
        estadistica.setCorrectoEscritura(estadistica.getCorrectoEscritura() + 1);
    }

    public void onWriteError(Exception ex, List<? extends String> items) {
        estadistica.setErrorEcritura(estadistica.getErrorEcritura() + 1);
        log.error(ex.getMessage());
    }
}