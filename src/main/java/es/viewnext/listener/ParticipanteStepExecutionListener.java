package es.viewnext.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

import es.viewnext.util.Utils;

public class ParticipanteStepExecutionListener implements StepExecutionListener {

    private static final Logger LOG = LoggerFactory.getLogger(ParticipanteStepExecutionListener.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
        Utils.printSeparator();
        LOG.info("PASO 2: INICIO");
        Utils.printSeparator();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        Utils.printSeparator();
        LOG.info("PASO 2: FIN");
        Utils.printSeparator();
        return null;
    }
}
