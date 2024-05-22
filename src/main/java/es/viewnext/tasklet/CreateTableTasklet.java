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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateTableTasklet implements Tasklet {

    private static final Logger log = LoggerFactory.getLogger(CreateTableTasklet.class);

    @Autowired
    private DataSource dataSource;

    public CreateTableTasklet(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        Connection connection = null;

        if (dataSource == null) {
            log.info("No es posible obtener los datos del origen de tados.");
        }

        try {
            connection = dataSource.getConnection();

            if (connection == null) {
                log.info("No es posible establecer la conexion con base de datos.");
            }

            DatabaseMetaData metaData = connection.getMetaData();

            if (metaData == null) {
                log.info("No es posible obtener los datos de conexion.");
            }

            log.info("Se creará la tabla si no existe...");
            executeScript(connection);

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            connection.close();
        }

        return RepeatStatus.FINISHED;
    }

    private void executeScript(Connection connection) {
        Statement statement = null;
        BufferedReader bufferedReader = null;
        try {
            statement = connection.createStatement();
            InputStream inputStream = getClass().getResourceAsStream("/data.sql");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String query;
            while ((query = bufferedReader.readLine()) != null) {
                log.info("query " + query);
                statement.execute(query);
            }
        } catch (SQLException e) {
            log.error("Error executing SQL query", e);
        } catch (IOException e) {
            log.error("Error reading data.sql file", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    log.error("Error closing statement", e);
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    log.error("Error closing bufferedReader", e);
                }
            }
        }
    }

}
