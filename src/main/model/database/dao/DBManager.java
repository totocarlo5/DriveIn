package main.model.database.dao;

import java.sql.*;
import java.util.Properties;

public class DBManager {
    private Properties queryProperties;
    private Properties dbProperties;

    public DBManager(Properties dbProperties, Properties queryProperties) {
        try {
            this.dbProperties = new Properties(dbProperties);
            this.queryProperties = new Properties(queryProperties);
            Class.forName(dbProperties.getProperty("driver"));
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    Connection getConnection() throws SQLException {
        String mySQLUrl = "jdbc:" + dbProperties.getProperty("dbms") + "://"
                + dbProperties.getProperty("host") + ":" + dbProperties.getProperty("port") + "/"
                + dbProperties.getProperty("dbName") + "?serverTimezone = " + dbProperties.getProperty("serverTimezone");
        String user = dbProperties.getProperty("user");
        String password = dbProperties.getProperty("password");
        return DriverManager.getConnection(mySQLUrl, user, password);
    }

    void releaseResources(Connection connection, PreparedStatement statement, ResultSet result) throws SQLException {
        result.close();
        statement.close();
        connection.close();
    }

    void releaseResources(Connection connection, PreparedStatement statement) throws SQLException {
        statement.close();
        connection.close();
    }

    PreparedStatement prepareStatement(Connection connection, String queryId) throws SQLException {
        return connection.prepareStatement(getQuery(queryId));
    }

    ResultSet executeQuery(PreparedStatement statement) throws SQLException {
        return statement.executeQuery();
    }

    int executeUpdate(PreparedStatement statement) throws SQLException {
        return statement.executeUpdate();
    }

    private String getQuery(String queryId) {
        return queryProperties.getProperty(queryId);
    }
}