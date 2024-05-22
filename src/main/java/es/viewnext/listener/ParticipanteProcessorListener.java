package es.viewnext.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.beans.factory.annotation.Autowired;

import es.viewnext.domain.Estadistica;
import es.viewnext.domain.Participante;

public class ParticipanteProcessorListener implements ItemProcessListener<String, String> {

    private static final Logger log = LoggerFactory.getLogger(ParticipanteProcessorListener.class);

    @Autowired
    private Estadistica estadistica;

    public ParticipanteProcessorListener(Estadistica estadistica) {
        this.estadistica = estadistica;
    }

    public void beforeProcess(Participante participante) {
    }

    public void afterProcess(Participante participante, Participante result) {
        estadistica.setCorrectoProcesado(estadistica.getCorrectoProcesado() + 1);
    }

    public void onProcessError(Participante participante, Exception ex) {
        estadistica.setErrorProcesado(estadistica.getErrorProcesado() + 1);
        log.error(ex.getMessage());
    }
}