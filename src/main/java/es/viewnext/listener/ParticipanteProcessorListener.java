package es.viewnext.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.beans.factory.annotation.Autowired;

import es.viewnext.domain.Estadistica;
import es.viewnext.domain.Participante;

public class ParticipanteProcessorListener implements ItemProcessListener<String, String> {

    private static final Logger LOG = LoggerFactory.getLogger(ParticipanteProcessorListener.class);

    @Autowired
    private Estadistica estadistica;

    public ParticipanteProcessorListener(Estadistica estadistica) {
        this.estadistica = estadistica;
    }

    public void beforeProcess(Participante participante) {
    }

    public void afterProcess(Participante participante, Participante result) {
        estadistica.setProcesadosCorrectamente(estadistica.getProcesadosCorrectamente() + 1);
    }

    public void onProcessError(Participante participante, Exception ex) {
        estadistica.setErroresProceso(estadistica.getErroresProceso() + 1);
        LOG.error(ex.getMessage());
    }
}