package main.model.database.dao;

import main.model.database.dto.MovieDTO;

public abstract class MovieDAO extends AbstractDAO<MovieDTO> {

    protected MovieDAO(DBManager dbManager) {
        super(dbManager);
    }

    //ulteriori query
}
