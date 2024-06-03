package es.viewnext.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

import es.viewnext.domain.Estadistica;
import es.viewnext.util.*;

public class MyJobExecutionListener implements JobExecutionListener {

    private static final Logger LOG = LoggerFactory.getLogger(MyJobExecutionListener.class);

    @Autowired
    private Estadistica estadistica;

    public MyJobExecutionListener(Estadistica estadistica) {
        this.estadistica = estadistica;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        Utils.printSeparator();
        LOG.info("Iniciando Job...");
        Utils.printSeparator();
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        Utils.printSeparator();
        LOG.info("Job terminado.");
        Utils.printSeparator();
        LOG.info("Job Status: " + jobExecution.getStatus());
        LOG.info("Job exitstatus: " + jobExecution.getExitStatus());
        Utils.printSeparator();
        estadistica.mostrarEstadisticas();
        Utils.printSeparator();
        jobExecution.setExitStatus(ExitStatus.COMPLETED);
    }
}
