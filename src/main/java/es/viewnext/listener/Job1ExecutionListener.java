package es.viewnext.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

import es.viewnext.domain.Estadistica;
import es.viewnext.util.*;

public class Job1ExecutionListener implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(Job1ExecutionListener.class);

    @Autowired
    private Estadistica estadistica;

    public Job1ExecutionListener(Estadistica estadistica) {
        this.estadistica = estadistica;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        Utils.printSeparator();
        log.info("Iniciando Job...");
        Utils.printSeparator();
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        Utils.printSeparator();
        log.info("Job terminado.");
        Utils.printSeparator();
        log.info("Job Status: " + jobExecution.getStatus());
        log.info("Job exitstatus: " + jobExecution.getExitStatus());
        Utils.printSeparator();
        log.info(estadistica.toString());
        Utils.printSeparator();
        jobExecution.setExitStatus(ExitStatus.COMPLETED);
    }
}
