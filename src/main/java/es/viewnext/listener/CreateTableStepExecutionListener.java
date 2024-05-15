package es.viewnext.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

import es.viewnext.util.Utils;

public class CreateTableStepExecutionListener implements StepExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(CreateTableStepExecutionListener.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
        Utils.printSeparator();
        log.info("PASO 1: INICIALIZANDO LA BASE DE DATOS");
        Utils.printSeparator();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        Utils.printSeparator();
        log.info("PASO 1: FIN ");
        Utils.printSeparator();
        return null;
    }
}
