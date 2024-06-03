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

import es.viewnext.dao.AtributoDao;
import es.viewnext.domain.Atributo;

public class AtributoDaoImpl implements AtributoDao {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger LOG = LoggerFactory.getLogger(AtributoDaoImpl.class);

    @Autowired
    public AtributoDaoImpl(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public AtributoDaoImpl() {

    }

    public void deleteDuplicates() {
        String sql = "DELETE FROM atributos a WHERE a.id_participante IN (SELECT id_participante FROM atributos "
                + "  GROUP BY id_participante, orden, valor HAVING COUNT(*) > 1) AND a.id IN ("
                + "  SELECT MIN(id) FROM atributos GROUP BY id_participante, orden, valor HAVING COUNT(*) > 1)";
        jdbcTemplate.update(sql);
    }

    public List<Atributo> select(final int idParticipante) {
        try {
            deleteDuplicates();
            String sql = "SELECT * FROM atributos WHERE (nombre = ? AND apellido1 = ? AND apellido2 = ? AND idioma = ? AND email = ?)";
            return jdbcTemplate.query(sql, new RowMapper<Atributo>() {
                @Override
                public Atributo mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                    Atributo atributo = new Atributo();
                    atributo.setIdParticipante(rs.getInt("id_participante"));
                    atributo.setOrden(rs.getInt("orden"));
                    atributo.setValor(rs.getString("valor"));
                    return atributo;
                }
            }, idParticipante);
        } catch (Exception e) {
            LOG.error("Error ejecutando el query", e);
            return Collections.emptyList();
        }
    }
}
