package main.model.database.dao;

import main.model.database.dto.ProductOrderMappingDTO;

import java.sql.SQLException;
import java.util.List;

public abstract class ProductOrderMappingDAO extends AbstractDAO<ProductOrderMappingDTO> {

    protected ProductOrderMappingDAO(DBManager dbManager) {
        super(dbManager);
    }

    //query varie

    //carica tutti i prodotti di una ordinazione
    protected abstract List<ProductOrderMappingDTO> getOrderProducts(Integer idOrder) throws SQLException;

    //carica tutte le ordinazioni di un prodotto
    protected abstract List<ProductOrderMappingDTO> getProductOrders(String productName) throws SQLException;
}
