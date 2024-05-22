package es.viewnext.processor;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import es.viewnext.domain.Participante;

public class ParticipanteProcessorWithoutDAO {

    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    private static final Logger log = LoggerFactory.getLogger(ParticipanteProcessorWithoutDAO.class);

    public ParticipanteProcessorWithoutDAO(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public List<Participante> select(final String nombre, final String apellido1, final String apellido2,
            final String idioma) {
        try {
            String sql = "SELECT * FROM participantes WHERE (nombre = ? AND apellido1 = ? AND apellido2 = ? AND idioma = ?)";
            return jdbcTemplate.query(sql, new RowMapper<Participante>() {
                @Override
                public Participante mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                    Participante participante = new Participante();
                    participante.setNombre(rs.getString("nombre"));
                    participante.setApellido1(rs.getString("apellido1"));
                    participante.setApellido2(rs.getString("apellido2"));
                    participante.setEmail(rs.getString("email"));
                    participante.setIdioma(rs.getString("idioma"));
                    participante.setFechaInicio(rs.getTimestamp("fecha_inicio"));
                    participante.setFechaFinalizacion(rs.getTimestamp("fecha_finalizacion"));
                    return participante;
                }
            }, nombre, apellido1, apellido2, idioma);
        } catch (Exception e) {
            log.error("Error ejecutando el query", e);
            return Collections.emptyList();
        }
    }

}
