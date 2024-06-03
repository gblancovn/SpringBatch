package es.viewnext.tasklet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import es.viewnext.domain.Estadistica;

public class ReadCsvTasklet implements Tasklet {

    @Autowired
    private Resource csvFile;

    @Autowired
    private Estadistica estadistica;

    private static final Logger LOG = LoggerFactory.getLogger(ReadCsvTasklet.class);

    public ReadCsvTasklet(Resource csvFile, Estadistica estadistica) {
        this.csvFile = csvFile;
        this.estadistica = estadistica;
    }

    public ReadCsvTasklet() {

    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        List<String[]> rows = new ArrayList<>();
        try (InputStream inputStream = csvFile.getInputStream();
                Reader reader = new InputStreamReader(inputStream);
                BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                rows.add(values);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String[]> duplicates = findDuplicates(rows);

        chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("duplicates",
                duplicates);

        return RepeatStatus.FINISHED;
    }

    private List<String[]> findDuplicates(List<String[]> rows) {
        List<String[]> duplicates = new ArrayList<>();
        for (String[] row : rows) {
            int count = 0;
            for (String[] otherRow : rows) {
                if (Arrays.equals(row, otherRow)) {
                    count++;
                }
            }
            if (count > 1) {
                duplicates.add(row);
                LOG.info("Duplicados: " + Arrays.toString(row));
            }
        }

        if (duplicates.size() > 0) {
            estadistica.setParticipantesDuplicados(duplicates.size()/2);
        }
        return duplicates;
    }
}