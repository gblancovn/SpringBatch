package es.viewnext.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import es.viewnext.util.*;

public class MyJobExecutionListener implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(MyJobExecutionListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        Utils.printSeparator();
        log.info("Before job execution....");
        Utils.printSeparator();
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        // TODO: Añadir estadíticas job
        Utils.printSeparator();
        log.info("After job execution...");
        Utils.printSeparator();
        log.info("Job Status: " + jobExecution.getStatus());
        log.info("Job exitstatus: " + jobExecution.getExitStatus());
        Utils.printSeparator();
        jobExecution.setExitStatus(ExitStatus.COMPLETED);
    }
}
