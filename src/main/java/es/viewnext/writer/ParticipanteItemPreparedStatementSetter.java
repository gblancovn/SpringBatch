package es.viewnext.writer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import es.viewnext.domain.Participante;
import es.viewnext.util.Utils;

public class ParticipanteItemPreparedStatementSetter implements ItemPreparedStatementSetter<Participante> {

    @Override
    public void setValues(Participante participante, PreparedStatement ps) throws SQLException {
        // TODO: Comprobar los parametros de entrada

        ps.setString(1, participante.getNombre());
        ps.setString(2, participante.getApellido1());
        ps.setString(3, participante.getApellido2());
        ps.setString(4, participante.getEmail());
        ps.setString(5, participante.getIdioma());

        // TODO: Usar UTILS
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        
        String fechaFormateadaInicio = sdf.format(participante.getFechaInicio());
        //ps.setString(6, Utils.format(fechaFormateadaInicio));
        
        // TODO: Usar UTILS
        String fechaFormateadaFin = sdf.format(participante.getFechaFinalizacion());
        ps.setString(7, fechaFormateadaFin);
    }

}
