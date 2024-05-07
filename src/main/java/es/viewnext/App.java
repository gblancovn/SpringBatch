package es.viewnext;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.viewnext.utils.Utils;

public class App {
	public static void main(String[] args) throws Exception {

		String[] springConfig = { "spring/batch/jobs/job-context.xml" };

		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");

		Job job = (Job) context.getBean("job");

		JobParameters jobParameters = getJobParameters();

		JobExecution execution = jobLauncher.run(job, jobParameters);
		System.out.println("Exit Status : " + execution.getStatus());
	}

	public static JobParameters getJobParameters() {
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
		jobParametersBuilder.addString("date", Utils.getCurrentDate());
		jobParametersBuilder.addString("hour", Utils.getCurrentHour());
		return jobParametersBuilder.toJobParameters();
	}
}
