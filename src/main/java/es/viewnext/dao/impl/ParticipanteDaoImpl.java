package es.viewnext.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.viewnext.dao.ParticipanteDao;
import es.viewnext.domain.Participante;

@Repository
public class ParticipanteDaoImpl implements ParticipanteDao {

    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    private static final Logger LOG = LoggerFactory.getLogger(ParticipanteDaoImpl.class);

    @Autowired
    public ParticipanteDaoImpl(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public ParticipanteDaoImpl() {

    }

    /**
     * Obtiene el próximo ID de participante disponible.
     * 
     * @return el próximo ID de participante
     */
    public Long nextId() {
        String query = "SELECT MAX(id_participante) FROM participantes";
        return jdbcTemplate.queryForObject(query, Long.class);
    }

    /**
     * Busca participantes que coincidan con los criterios de búsqueda especificados.
     * 
     * @param nombre el nombre del participante
     * @param apellido1 el primer apellido del participante
     * @param apellido2 el segundo apellido del participante
     * @param idioma el idioma del participante
     * @param email el correo electrónico del participante
     * @return la lista de participantes que coinciden con los criterios de búsqueda
     */
    public List<Participante> select(final String nombre, final String apellido1, final String apellido2,
            final String idioma, final String email) {
        try {
            String sql = "SELECT * FROM participantes WHERE (nombre = ? AND apellido1 = ? AND apellido2 = ? AND idioma = ? AND email = ?)";
            return jdbcTemplate.query(sql, new RowMapper<Participante>() {
                @Override
                public Participante mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                    Participante participante = new Participante();
                    participante.setIdParticipante(rs.getInt("id_participante"));
                    participante.setNombre(rs.getString("nombre"));
                    participante.setApellido1(rs.getString("apellido1"));
                    participante.setApellido2(rs.getString("apellido2"));
                    participante.setEmail(rs.getString("email"));
                    participante.setIdioma(rs.getString("idioma"));
                    participante.setFechaInicio(rs.getTimestamp("fecha_inicio"));
                    participante.setFechaFinalizacion(rs.getTimestamp("fecha_finalizacion"));
                    return participante;
                }
            }, nombre, apellido1, apellido2, idioma, email);
        } catch (Exception e) {
            LOG.error("Error ejecutando el query", e);
            return Collections.emptyList();
        }
    }

}
