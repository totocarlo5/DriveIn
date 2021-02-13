package main.model.database.dao.mysqldb;

import main.model.database.dao.DBManager;
import main.model.database.dao.UserDAO;
import main.model.database.dto.UserDTO;

import java.sql.*;
import java.util.List;

public class MysqlUserDAO extends UserDAO {

    MysqlUserDAO(DBManager dbManager) { super(dbManager); }

    @Override
    protected UserDTO buildDTO(ResultSet result) throws SQLException {
        return new UserDTO(
                result.getString("utenti.username"),
                result.getString("utenti.password"),
                result.getString("utenti.nome"),
                result.getString("utenti.cognome"),
                result.getString("utenti.codice_fiscale"),
                result.getDate("utenti.data_di_nascita") == null ? null : result.getDate("utenti.data_di_nascita").toLocalDate(),
                result.getString("utenti.telefono")
        );
    }

    @Override
    public int save(UserDTO user) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "inserisci_utente")) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setDate(5, Date.valueOf(user.getBirthDate()));
            statement.setString(6, user.getFiscalCode());
            statement.setString(7, user.getPhone());
            return executeUpdate(statement);
        }
    }

    @Override
    public List<UserDTO> load(Object... primaryKey) throws SQLException {
        try (Connection connection = openConnection();
             PreparedStatement statement = prepareStatement(connection,"leggi_utente")) {
            statement.setString(1, (String) primaryKey[0]);
            return getFormattedResult(executeQuery(statement));
        }
    }

    @Override
    public int update(UserDTO user) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "aggiorna_utente")) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setDate(5, Date.valueOf(user.getBirthDate()));
            statement.setString(6, user.getFiscalCode());
            statement.setString(7, user.getPhone());
            return executeUpdate(statement);
        }
    }

    @Override
    public int delete(Object... primaryKey) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "cancella_utente")) {
            statement.setString(1, (String) primaryKey[0]);
            return executeUpdate(statement);
        }
    }
}



