package es.viewnext.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

import es.viewnext.util.Utils;

public class ParticipanteStepExecutionListener implements StepExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(ParticipanteStepExecutionListener.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
        Utils.printSeparator();
        log.info("PASO 3: INSERTAR PARTICIPANTE EN LA BASE DE DATOS");
        Utils.printSeparator();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        Utils.printSeparator();
        log.info("PASO 3: FIN");
        Utils.printSeparator();
        return null;
    }
}