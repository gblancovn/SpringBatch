package es.viewnext.tasklet;

import java.io.BufferedReader;
import java.io.FileReader;
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

    private static final Logger log = LoggerFactory.getLogger(MyTasklet.class);

    @Autowired
    private DataSource dataSource;

    public CreateTableTasklet(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("Tasklet database check");
        Connection connection = dataSource.getConnection();
        DatabaseMetaData metaData = connection.getMetaData();

        boolean tableExists = metaData.getTables(null, null, "PARTICIPANTES", null).next();

        if (!tableExists) {
            createTable(connection);
        }

        connection.close();
        return RepeatStatus.FINISHED;
    }

    private void createTable(Connection connection) throws SQLException, IOException {
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
