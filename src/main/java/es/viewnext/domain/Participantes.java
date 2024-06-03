package es.viewnext.domain;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Scope;

//TODO REVISAR si utilizar el hashset para la lista(no guarda duplicados)
@Scope("singleton")
public class Participantes {

    private Set<Participante> participantes = new HashSet<>();

    public void addParticipante(Participante participante) {
        participantes.add(participante);
    }

    public boolean containsParticipante(Participante participante) {
        return participantes.contains(participante);
    }

    public Set<Participante> getParticipantes() {
        return participantes;
    }

}
