package es.viewnext.writer;

import java.util.Set;

import org.springframework.batch.item.file.transform.LineAggregator;

import es.viewnext.domain.Participante;
import es.viewnext.domain.Participantes;

public class DescartadosLineAggregator implements LineAggregator<Participante> {

    private Set<Participante> participantes;

    public DescartadosLineAggregator(Participantes participantes) {
        this.participantes = participantes.getParticipantes();
    }

    @Override
    public String aggregate(Participante participante) {
        StringBuilder line = new StringBuilder();
        line.append(participante.getNombre()).append(",");
        line.append(participante.getApellido1()).append(",");
        line.append(participante.getApellido2()).append(",");
        line.append(participante.getEmail()).append(",");
        line.append(participante.getIdioma()).append(",");
        line.append(participante.getFechaInicio()).append(",");
        line.append(participante.getFechaFinalizacion()).append(",");
        line.append(participante.getFechaNacimiento()).append("\n");
        return line.toString();
    }

    // TODO Hacer writer a fichero descartados
}
