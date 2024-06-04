package es.viewnext.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemProcessListener;

import es.viewnext.domain.Estadistica;
import es.viewnext.domain.Participante;

public class ParticipanteProcessorListener implements ItemProcessListener<String, String> {

    private static final Logger LOG = LoggerFactory.getLogger(ParticipanteProcessorListener.class);

    private Estadistica estadistica;

    public ParticipanteProcessorListener(Estadistica estadistica) {
        this.estadistica = estadistica;
    }

    public void beforeProcess(Participante participante) {
        // document why this method is empty
    }

    public void afterProcess() {
        estadistica.setProcesadosCorrectamente(estadistica.getProcesadosCorrectamente() + 1);
    }

    public void onProcessError(Exception ex) {
        estadistica.setErroresProceso(estadistica.getErroresProceso() + 1);
        LOG.error(ex.getMessage());
    }
}