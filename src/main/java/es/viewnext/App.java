package es.viewnext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.viewnext.util.Utils;

public class App {

    public static void main(String[] args) throws Exception {

        final Logger log = LoggerFactory.getLogger(App.class);

        String[] springConfig = { "spring/batch/jobs/job-context.xml" };

        ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

        JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");

        Job job = (Job) context.getBean("job");

        JobParameters jobParameters = getJobParameters();

        JobExecution execution = jobLauncher.run(job, jobParameters);
        log.info("Exit Status : " + execution.getStatus());
    }

    public static JobParameters getJobParameters() {
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addString("date", Utils.getCurrentDate());
        jobParametersBuilder.addString("hour", Utils.getCurrentHour());
        return jobParametersBuilder.toJobParameters();
    }
}
