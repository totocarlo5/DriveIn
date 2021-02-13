package main.model.database.dao;

import main.model.database.dto.ShowDTO;

import java.sql.SQLException;
import java.util.List;

public abstract class ShowDAO extends AbstractDAO<ShowDTO> {

    protected ShowDAO(DBManager dbManager) {
        super(dbManager);
    }

    //query varie

    //carica tutti gli spettacoli in cui un determinato film viene proiettato
    protected abstract List<ShowDTO> getFilmShows(Integer idMovie) throws SQLException;
}
