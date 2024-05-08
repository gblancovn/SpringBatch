package es.viewnext;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import es.viewnext.utils.*;

public class MyTasklet implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
		Utils.printSeparator();
		System.out.println("This is a sample example of spring batch");
		Utils.printSeparator();
		return RepeatStatus.FINISHED;
	}
}