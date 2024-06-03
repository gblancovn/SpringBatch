package es.viewnext.tasklet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class WriteDuplicatesTasklet implements Tasklet {

    private File outputFile = new File("target/csv/participantesDuplicados.csv");

    private static final Logger LOG = LoggerFactory.getLogger(WriteDuplicatesTasklet.class);

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        LOG.info("Ruta del archivo output. " + outputFile);

        File file = outputFile.getParentFile();
        if (!file.exists()) {
            LOG.info("El archivo no existe, creando...");
            file.createNewFile();
        }

        // Verificar que se tenga permiso de escritura
        if (!file.canWrite()) {
            LOG.info("No se tiene permiso de escritura en el archivo");
            return null;
        } else {
            LOG.info("Tienes permiso de escritura, escribiendo ...");
        }
        List<String[]> duplicates = (List<String[]>) chunkContext.getStepContext().getJobExecutionContext()
                .get("duplicates");
        if (duplicates.size() == 0) {
            LOG.info("La lista de duplicados está vacía.");
            return null;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String[] row : duplicates) {
                LOG.info("Escribiendo fila: " + Arrays.toString(row));
                bw.write(String.format("%s,%s,%s,%s,%s,%s\n", row[0], row[1], row[2], row[3], row[4], row[5]));

            }
            bw.flush();
            LOG.info("Escritura en el archivo completa");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File targetFile = new File("src/main/resources/csv/participantesDuplicados.csv");
        if (targetFile.exists()) {
            targetFile.delete();
        }
        FileUtils.moveFile(file, targetFile);

        LOG.info("Archivo movido a la ruta deseada");

        return RepeatStatus.FINISHED;
    }
}