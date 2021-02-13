package main.model.database.dao.mysqldb;

import main.model.database.dao.*;

import java.io.IOException;
import java.util.Properties;

public class MysqlDAOFactory implements AbstractDAOFactory {
    private static DBManager dbManager;

    public MysqlDAOFactory() {
        try {
            Properties dbProperties = new Properties();
            Properties queryProperties = new Properties();
            dbProperties.load(getClass().getResourceAsStream("mysqlDB.properties"));
            queryProperties.load(getClass().getResourceAsStream("query.properties"));
            dbManager = new DBManager(dbProperties, queryProperties);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public UserDAO getUserDAO() {
        return new MysqlUserDAO(dbManager);
    }

    @Override
    public ShowDAO getShowDAO() {
        return new MysqlShowDAO(dbManager);
    }

    @Override
    public ReservationDAO getReservationDAO() {
        return new MysqlReservationDAO(dbManager);
    }

    @Override
    public OrderDAO getOrderDAO() {
        return new MysqlOrderDAO(dbManager);
    }

    @Override
    public ProductDAO getProductDAO() {
        return new MysqlProductDAO(dbManager);
    }

    @Override
    public ProductOrderMappingDAO getProductForOrderDAO() {
        return new MysqlProductOrderMappingDAO(dbManager);
    }

    @Override
    public MovieDAO getMovieDAO() {
        return new MysqlMovieDAO(dbManager);
    }
}