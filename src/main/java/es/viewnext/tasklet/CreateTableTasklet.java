package es.viewnext.tasklet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class CreateTableTasklet implements Tasklet {

    private static final Logger LOG = LoggerFactory.getLogger(CreateTableTasklet.class);

    private DataSource dataSource;

    public CreateTableTasklet(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        Connection connection = null;

        if (dataSource == null) {
            LOG.info("No es posible obtener los datos del origen de tados.");
        }

        try {
            if (dataSource != null) {
                connection = dataSource.getConnection();
            } else {
                throw new NullPointerException("dataSource es nulo");
            }

            if (connection == null) {
                LOG.info("No es posible establecer la conexion con base de datos.");
                throw new NullPointerException("connection es nulo");
            }

            DatabaseMetaData metaData = connection.getMetaData();

            if (metaData == null) {
                LOG.info("No es posible obtener los datos de conexion.");
                throw new NullPointerException("metaData es nulo");
            }

            LOG.info("Se creará la tabla si no existe...");
            executeScript(connection);

        } catch (Exception e) {
            LOG.error(e.getMessage());
        } finally {
            if (connection!=null) connection.close();
        }

        return RepeatStatus.FINISHED;
    }

    /**
     * Ejecuta un script SQL contenido en el archivo data.sql.
     * 
     * @param connection la conexión a la base de datos
     */
    private void executeScript(Connection connection) {
        try (Statement statement = connection.createStatement();
                InputStream inputStream = getClass().getResourceAsStream("/data.sql");
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            String query;
            while ((query = bufferedReader.readLine()) != null) {
                LOG.info("query " + query);
                statement.addBatch(query);
            }
            statement.executeBatch();

        } catch (SQLException e) {
            LOG.error("Error executing SQL query", e);
        } catch (IOException e) {
            LOG.error("Error reading data.sql file", e);
        }
    }

}
