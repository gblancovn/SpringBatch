package es.viewnext.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class MyJobExecutionListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Before job execution....");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

        System.out.println("After job execution...");
        System.out.println("Job Status: " + jobExecution.getStatus());
        System.out.println("Job exitstatus: " + jobExecution.getExitStatus());
        jobExecution.setExitStatus(ExitStatus.COMPLETED);
    }
}
