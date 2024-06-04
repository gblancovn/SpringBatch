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

    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    private static final Logger LOG = LoggerFactory.getLogger(AtributoDaoImpl.class);

    @Autowired
    public AtributoDaoImpl(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public AtributoDaoImpl() {

    }

    /**
     * Elimina los atributos duplicados en la base de datos.
     */
    public void deleteDuplicates() {
        String sql = "DELETE FROM atributos a WHERE a.id_participante IN (SELECT id_participante FROM atributos "
                + "  GROUP BY id_participante, orden, valor HAVING COUNT(*) > 1) AND a.id IN ("
                + "  SELECT MIN(id) FROM atributos GROUP BY id_participante, orden, valor HAVING COUNT(*) > 1)";
        jdbcTemplate.update(sql);
    }

    /**
     * Obtiene la lista de atributos asociados a un participante.
     * 
     * @param idParticipante el ID del participante
     * @return la lista de atributos del participante
     */
    public List<Atributo> select(final int idParticipante) {
        try {
            deleteDuplicates();
            String sql = "SELECT * FROM atributos WHERE (id_participante = ?)";
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

    /**
     * Inserta un atributo en la base de datos si no existe ya.
     * 
     * @param atributo el atributo a insertar
     */
    public void insert(Atributo atributo) {
        try {
            String sql = "SELECT * FROM atributos WHERE (id_participante = ? AND orden = ? AND valor = ?)";
            List<Atributo> atributos = jdbcTemplate.query(sql, new RowMapper<Atributo>() {
                @Override
                public Atributo mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                    Atributo a = new Atributo();
                    a.setIdParticipante(rs.getInt("id_participante"));
                    a.setOrden(rs.getInt("orden"));
                    a.setValor(rs.getString("valor"));
                    return a;
                }
            }, atributo.getIdParticipante(), atributo.getOrden(), atributo.getValor());

            if (atributos.isEmpty()) {
                sql = "INSERT INTO atributos (id_participante, orden, valor) VALUES (?, ?, ?)";
                jdbcTemplate.update(sql, atributo.getIdParticipante(), atributo.getOrden(), atributo.getValor());
            }
        } catch (Exception e) {
            LOG.error("Error ejecutando el query", e);
        }
    }

}
