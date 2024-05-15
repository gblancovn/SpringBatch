package es.viewnext.reader;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import es.viewnext.domain.Participante;
import es.viewnext.util.Utils;

public class ParticipanteReader implements FieldSetMapper<Participante> {

    @Override
    public Participante mapFieldSet(FieldSet fieldSet) throws BindException {
        if (fieldSet == null) {
            return null;
        }

        String[] apellidos = fieldSet.readString(1).split(" ");
        String apellido1 = apellidos[0];
        String apellido2 = apellidos[1];

        Participante participante = new Participante();
        participante.setNombre(fieldSet.readString(0));
        participante.setApellido1(apellido1);
        participante.setApellido2(apellido2);
        participante.setEmail(fieldSet.readString(2));
        participante.setIdioma(fieldSet.readString(3));
        participante.setFechaInicio(Utils.format(fieldSet.readString(4)));
        participante.setFechaFinalizacion(Utils.format(fieldSet.readString(5)));
        
        return participante;
    }

}
