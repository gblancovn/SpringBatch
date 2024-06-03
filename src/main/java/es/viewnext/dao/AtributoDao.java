package es.viewnext.dao;

import java.util.List;

import es.viewnext.domain.Atributo;

public interface AtributoDao {

    public List<Atributo> select(final int idParticipante);
    
    public void insert(final Atributo atributo);
}
