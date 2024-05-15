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
        if (dataSource == null) {
            log.info("No es posible obtener los datos del origen de tados.");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            log.info("No es posible establecer la conexion con base de datos.");
        }

        DatabaseMetaData metaData = connection.getMetaData();

        if (metaData == null) {
            log.info("No es posible obtener los datos de conexion.");
        }

        //TODO: Ampliar para mas tablas

        boolean tableExists = metaData.getTables(null, null, "PARTICIPANTES", null).next();

        if (!tableExists) {
            log.info("No existe la tabla. Se crea.");
            executeScript(connection);
        } else {
            log.info("Ya existe la tabla.");
        }

        connection.close();
        return RepeatStatus.FINISHED;
    }

    private void executeScript(Connection connection) throws SQLException, IOException {
        Statement statement = connection.createStatement();
        InputStream inputStream = getClass().getResourceAsStream("/data.sql");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String query = bufferedReader.readLine();
        log.info("query " + query);
        if (query != null && !query.isEmpty()) {
            statement.execute(query);
        }
        statement.close();
    }

}
