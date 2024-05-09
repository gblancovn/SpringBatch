package es.viewnext.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

import es.viewnext.util.Utils;

public class MyStepExecutionListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        Utils.printSeparator();
        System.out.println("Befores step execution... ");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        Utils.printSeparator();
        System.out.println("After step execution... ");
        Utils.printSeparator();
        return null;
    }
}
