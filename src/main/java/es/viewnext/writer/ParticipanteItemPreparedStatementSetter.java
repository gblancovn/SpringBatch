package es.viewnext.writer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import es.viewnext.domain.Participante;

public class ParticipanteItemPreparedStatementSetter implements ItemPreparedStatementSetter<Participante> {

    @Override
    public void setValues(Participante participante, PreparedStatement ps) throws SQLException {
        ps.setString(1, participante.getNombre());
        ps.setString(2, participante.getApellido1());
        ps.setString(3, participante.getApellido2());
        ps.setString(4, participante.getEmail());
        ps.setString(5, participante.getIdioma());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String fechaFormateadaInicio = sdf.format(participante.getFechaInicio());
        ps.setString(6, fechaFormateadaInicio);
        String fechaFormateadaFin = sdf.format(participante.getFechaFinalizacion());
        ps.setString(7, fechaFormateadaFin);
    }

}
