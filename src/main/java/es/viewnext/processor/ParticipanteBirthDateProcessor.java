package es.viewnext.processor;

import org.springframework.batch.item.ItemProcessor;

import es.viewnext.domain.DatosPersonalesParticipante;

public class ParticipanteBirthDateProcessor implements ItemProcessor<DatosPersonalesParticipante, DatosPersonalesParticipante> {

    @Override
    public DatosPersonalesParticipante process(DatosPersonalesParticipante item) throws Exception {
        return item;
    }
}
