package es.viewnext.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

import es.viewnext.util.Utils;

public class MyTaskletExecutionListener implements StepExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(MyTaskletExecutionListener.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
        Utils.printSeparator();
        log.info("Befores tasklet execution... ");
        Utils.printSeparator();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        Utils.printSeparator();
        log.info("After tasklet execution... ");
        Utils.printSeparator();
        return null;
    }
}
