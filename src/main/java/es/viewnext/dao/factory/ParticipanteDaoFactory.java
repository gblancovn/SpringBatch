package es.viewnext.dao.factory;

import javax.sql.DataSource;

import es.viewnext.dao.ParticipanteDao;
import es.viewnext.dao.impl.ParticipanteDaoImpl;

public class ParticipanteDaoFactory {

    public static ParticipanteDao getParticipanteDao(DataSource dataSource) {
        return new ParticipanteDaoImpl(dataSource);
    }
}
