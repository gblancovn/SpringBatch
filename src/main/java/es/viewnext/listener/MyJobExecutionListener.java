package es.viewnext.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import es.viewnext.domain.Estadistica;
import es.viewnext.util.Utils;

public class MyJobExecutionListener implements JobExecutionListener {

    private static final Logger LOG = LoggerFactory.getLogger(MyJobExecutionListener.class);

    private Estadistica estadistica;

    public MyJobExecutionListener(Estadistica estadistica) {
        this.estadistica = estadistica;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        Utils.printSeparator();
        LOG.info("INICIANDO JOB...");
        Utils.printSeparator();
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        Utils.printSeparator();
        LOG.info("JOB TERMINADO");
        Utils.printSeparator();
        LOG.info("JOB STATUS: " + jobExecution.getStatus());
        LOG.info("JOB EXITSTATUS: " + jobExecution.getExitStatus());
        Utils.printSeparator();
        estadistica.mostrarEstadisticas();
        Utils.printSeparator();
        jobExecution.setExitStatus(ExitStatus.COMPLETED);
    }
}
