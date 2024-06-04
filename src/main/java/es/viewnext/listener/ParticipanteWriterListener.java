package es.viewnext.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.item.Chunk;

import es.viewnext.dao.AtributoDao;
import es.viewnext.domain.Atributo;
import es.viewnext.domain.Estadistica;
import es.viewnext.domain.Participante;

public class ParticipanteWriterListener implements ItemWriteListener<Participante> {

    private static final Logger LOG = LoggerFactory.getLogger(ParticipanteWriterListener.class);

    private Estadistica estadistica;

    private AtributoDao atributoDao;

    public ParticipanteWriterListener(Estadistica estadistica, AtributoDao atributoDao) {
        this.estadistica = estadistica;
        this.atributoDao = atributoDao;
    }

    @Override
    public void beforeWrite(Chunk<? extends Participante> participantes) {
      // document why this method is empty
    }

    @Override
    public void afterWrite(Chunk<? extends Participante> participantes) {
        for (Participante participante : participantes) {
            LOG.info("Procesando el participante " + participante.getNombre() + " " + participante.getApellido1() + " "
                    + participante.getApellido2() + " se ha ingresado a la base de datos correctamente.");
            estadistica.setEscriturasCorrectas(estadistica.getEscriturasCorrectas() + 1);
            for (Atributo atributo : participante.getAtributos()) {
                atributo.setValor(atributo.getValor().toUpperCase());
                atributoDao.insert(atributo);
            }
        }
    }

    @Override
    public void onWriteError(Exception ex, Chunk<? extends Participante> participantes) {
        estadistica.setErroresEcritura(estadistica.getErroresEscritura() + 1);
        LOG.error(ex.getMessage());
    }

}