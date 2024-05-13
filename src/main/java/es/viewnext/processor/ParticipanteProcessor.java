package es.viewnext.processor;

import org.springframework.batch.item.ItemProcessor;

import es.viewnext.domain.Participante;

public class ParticipanteProcessor implements ItemProcessor<Participante, Participante> {

    @Override
    public Participante process(Participante item) throws Exception {
        String fname = item.getNombre();
        String lname1 = item.getApellido1();
        String lname2 = item.getApellido2();
        String email = item.getEmail();
        String lang = item.getIdioma();

        item.setNombre(fname.toUpperCase());
        item.setApellido1(lname1.toUpperCase());
        item.setApellido2(lname2.toUpperCase());
        item.setEmail(email.toUpperCase());
        item.setIdioma(lang.toUpperCase());

        return item;
    }
}
