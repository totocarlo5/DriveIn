package main.model.database.dao;

import main.model.database.dto.UserDTO;

public abstract class UserDAO extends AbstractDAO<UserDTO> {

    protected UserDAO(DBManager dbManager) {
        super(dbManager);
    }

    //query varie
}
