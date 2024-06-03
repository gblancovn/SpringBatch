package es.viewnext.reader.mapper;

import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import es.viewnext.domain.Participante;

public class DatosPersonalesMapper implements FieldSetMapper<Participante> {

    @Override
    public Participante mapFieldSet(FieldSet fieldSet) throws BindException {
        if (fieldSet == null) {
            return null;
        }

        Participante participante = new Participante();
        participante.setEmail(fieldSet.readString(0));
        participante.setFechaNacimiento(LocalDate.ofInstant(fieldSet.readDate(1).toInstant(), ZoneId.systemDefault()));
        return participante;
    }

}
