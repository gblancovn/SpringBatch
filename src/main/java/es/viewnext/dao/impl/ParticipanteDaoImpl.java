package es.viewnext.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import es.viewnext.dao.ParticipanteDao;
import es.viewnext.domain.Participante;

public class ParticipanteDaoImpl implements ParticipanteDao {

    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    public ParticipanteDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public ParticipanteDaoImpl() {
    }

    @Override
    public List<Participante> select(final String nombre, final String apellido1, final String apellido2,
            final String idioma) {
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
    }
}
