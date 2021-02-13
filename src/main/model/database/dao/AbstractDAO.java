package main.model.database.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<T> {
    private final DBManager dbManager;

    protected AbstractDAO(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    protected Connection openConnection() throws SQLException {
        if (dbManager != null) {
            return dbManager.getConnection();
        }
        return null;
    }

    protected void closeConnection(Connection connection, PreparedStatement statement, ResultSet result) throws SQLException {
        dbManager.releaseResources(connection, statement, result);
    }

    protected void closeConnection(Connection connection, PreparedStatement statement) throws SQLException {
        dbManager.releaseResources(connection, statement);
    }

    protected PreparedStatement prepareStatement(Connection connection, String queryId) throws SQLException {
        return dbManager.prepareStatement(connection, queryId);
    }

    protected ResultSet executeQuery(PreparedStatement statement) throws SQLException {
        return dbManager.executeQuery(statement);
    }

    protected int executeUpdate(PreparedStatement statement) throws SQLException {
        return dbManager.executeUpdate(statement);
    }

    protected List<T> getFormattedResult(ResultSet result) throws SQLException {
        ArrayList<T> beans = new ArrayList<>();
        while(result.next())
            beans.add(buildDTO(result));
        return beans;
    }

    protected abstract T buildDTO(ResultSet result) throws SQLException;

    //operazioni CRUD

    public abstract int save(T dto) throws SQLException;

    public abstract List<T> load(Object... primaryKey) throws SQLException;

    public abstract int update(T dto) throws SQLException, IOException;

    public abstract int delete(Object... primaryKey) throws SQLException;
}