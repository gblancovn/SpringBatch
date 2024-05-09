package es.viewnext.tasklet;

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

    private void createTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE PARTICIPANTES (" + "ID_PARTICIPANTE INT NOT NULL, "
                + "NUMPER VARCHAR(13) DEFAULT NULL, " + "MATRICULA VARCHAR(12) DEFAULT NULL, "
                + "NIF VARCHAR(18) DEFAULT NULL, " + "NOMBRE VARCHAR(100) DEFAULT NULL, "
                + "APELLIDO1 VARCHAR(80) DEFAULT NULL, " + "APELLIDO2 VARCHAR(80) DEFAULT NULL, "
                + "EMAIL VARCHAR(255) DEFAULT NULL, " + "PREFIJO_TELEFONO_MOVIL VARCHAR(5) DEFAULT NULL, "
                + "TELEFONO_MOVIL VARCHAR(13) DEFAULT NULL, " + "FECHA_INICIO TIMESTAMP DEFAULT NULL, "
                + "FECHA_FINALIZACION TIMESTAMP DEFAULT NULL, " + "IDIOMA VARCHAR(2) DEFAULT NULL)");
        statement.close();
    }
}
