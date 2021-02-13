package main.model.database.dao.mysqldb;

import main.model.database.dao.DBManager;
import main.model.database.dao.ProductDAO;
import main.model.database.dto.ProductDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MysqlProductDAO extends ProductDAO {

    MysqlProductDAO(DBManager dbManager) {
        super(dbManager);
    }

    @Override
    protected ProductDTO buildDTO(ResultSet result) throws SQLException {
        return new ProductDTO(
                result.getString("Nome_prodotto"),
                result.getFloat("Prezzo"),
                result.getString("Categoria")
        );
    }

    @Override
    public int save(ProductDTO product) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "inserisci_prodotto")) {
            statement.setString(1, product.getProductName());
            statement.setFloat(2, product.getPrice());
            statement.setString(3, product.getCategory());
            return executeUpdate(statement);
        }
    }

    @Override
    public List<ProductDTO> load(Object... primaryKey) throws SQLException {
        try(Connection connection = openConnection()) {
            PreparedStatement statement;
            if (primaryKey.length == 0)
                statement = prepareStatement(connection, "leggi_tutti_i_prodotti");
            else {
                statement = prepareStatement(connection, "leggi_prodotto");
                statement.setString(1, (String) primaryKey[0]);
            }
            return getFormattedResult(executeQuery(statement));
        }
    }

    @Override
    public int update(ProductDTO product) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "aggiorna_prodotto")) {
            statement.setString(1, product.getProductName());
            statement.setFloat(2,product.getPrice());
            statement.setString(3,product.getCategory());
            return executeUpdate(statement);
        }
    }

    @Override
    public int delete(Object... primaryKey) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "cancella_prodotto")) {
            statement.setString(1, (String) primaryKey[0]);
            return executeUpdate(statement);
        }
    }
}
