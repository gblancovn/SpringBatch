package es.viewnext.writer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import es.viewnext.domain.Atributo;
import es.viewnext.domain.Participante;

public class AtributoItemPreparedStatementSetter implements ItemPreparedStatementSetter<Participante> {

    @Override
    public void setValues(Participante participante, PreparedStatement ps) throws SQLException {
        Atributo[] atributos = participante.getAtributos();
        for (Atributo atributo : atributos) {
            if (atributo != null) {
                ps.setInt(1, participante.getIdParticipante());
                ps.setInt(2, atributo.getOrden());
                ps.setString(3, atributo.getValor());
                ps.addBatch();
            }
        }
    }
}
