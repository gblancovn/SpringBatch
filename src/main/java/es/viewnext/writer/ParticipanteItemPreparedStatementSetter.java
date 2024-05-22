package es.viewnext.writer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import es.viewnext.domain.Participante;

public class ParticipanteItemPreparedStatementSetter implements ItemPreparedStatementSetter<Participante> {

    private static final Logger log = LoggerFactory.getLogger(ParticipanteItemPreparedStatementSetter.class);

    @Override
    public void setValues(Participante participante, PreparedStatement ps) throws SQLException {
        if (participante == null) {
            log.info("Participante vacío en ParticipanteItemPreparedStatementSetter");
            throw new NullPointerException("Participante cannot be null");
        }

        try {
            ps.setString(1, participante.getNombre());
            ps.setString(2, participante.getApellido1());
            ps.setString(3, participante.getApellido2());
            ps.setString(4, participante.getEmail());
            ps.setString(5, participante.getIdioma());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String fechaInicioStr = sdf.format(participante.getFechaInicio());
            String fechaFinStr = sdf.format(participante.getFechaFinalizacion());

            ps.setString(6, fechaInicioStr);
            ps.setString(7, fechaFinStr);

        } catch (Exception e) {
            log.error("Error en preparedStatement: " + e.getMessage());
        }
        log.info(ps.toString());
    }

}
