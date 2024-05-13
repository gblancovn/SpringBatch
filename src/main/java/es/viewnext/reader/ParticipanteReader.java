package es.viewnext.reader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import es.viewnext.domain.Participante;

public class ParticipanteReader implements FieldSetMapper<Participante> {

    @Override
    public Participante mapFieldSet(FieldSet fieldSet) throws BindException {
        Participante participante = new Participante();
        participante.setNombre(fieldSet.readString(0));

        String text = fieldSet.readString(1);
        String[] parts = text.split(" ");
        String part1 = parts[0];
        String part2 = parts[1];

        participante.setApellido1(part1);
        participante.setApellido2(part2);
        participante.setEmail(fieldSet.readString(2));
        participante.setIdioma(fieldSet.readString(3));
        participante.setFechaInicio(FechaConvert(fieldSet.readString(4)));
        participante.setFechaFinalizacion(FechaConvert(fieldSet.readString(5)));
        return participante;
    }

    public Date FechaConvert(String date) {
        String fechaString = date;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date fecha = null;
        try {
            fecha = sdf.parse(fechaString);
            return fecha;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
