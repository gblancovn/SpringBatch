package es.viewnext.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import es.viewnext.util.Utils;

public class MyJobExecutionListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        Utils.printSeparator();
        System.out.println("Before job execution....");
        Utils.printSeparator();
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        Utils.printSeparator();
        System.out.println("After job execution...");
        System.out.println("Job Status: " + jobExecution.getStatus());
        System.out.println("Job exitstatus: " + jobExecution.getExitStatus());
        Utils.printSeparator();
        jobExecution.setExitStatus(ExitStatus.COMPLETED);
    }
}
