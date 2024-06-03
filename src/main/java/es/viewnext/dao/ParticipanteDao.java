package es.viewnext.dao;

import java.util.List;

import es.viewnext.domain.Participante;

public interface ParticipanteDao {

    public List<Participante> select(final String nombre, final String apellido1, final String apellido2,
            final String idioma, final String email);

    public Long nextId();
}
