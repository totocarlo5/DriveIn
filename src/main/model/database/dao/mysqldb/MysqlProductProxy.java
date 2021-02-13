package main.model.database.dao.mysqldb;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.database.Mapping;
import main.model.database.dto.ProductOrderMappingDTO;
import main.model.database.entity.Product;
import main.model.database.entity.ProductOrderMapping;

import java.sql.SQLException;
import java.util.List;

public class MysqlProductProxy extends Product {
    private Boolean isAlreadyLoaded;

    public MysqlProductProxy(String productName, Float price, String category) {
        super(productName, price, category);
        isAlreadyLoaded = false;
    }

    @Override
    public ObservableList<ProductOrderMapping> getOrders() throws SQLException {
        if (!isAlreadyLoaded) {
            MysqlDAOFactory mysqlDAOFactory = new MysqlDAOFactory();
            MysqlProductOrderMappingDAO mysqlProductOrderMappingDAO = (MysqlProductOrderMappingDAO) mysqlDAOFactory.getProductForOrderDAO();
            List<ProductOrderMappingDTO> productOrdersMappingsDTO = mysqlProductOrderMappingDAO.getProductOrders(getProductName());
            List<ProductOrderMapping> orders = Mapping.fromProductOrderMappingDTOToProductOrderMapping(productOrdersMappingsDTO, Mapping.DBName.MY_SQL);
            setOrders(FXCollections.observableList(orders));
            isAlreadyLoaded = true;
        }
        return super.getOrders();
    }
}
