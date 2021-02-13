package main.model.database.dao.mysqldb;

import main.model.database.Mapping;
import main.model.database.dto.OrderDTO;
import main.model.database.dto.ProductDTO;
import main.model.database.entity.Order;
import main.model.database.entity.Product;
import main.model.database.entity.ProductOrderMapping;

import java.sql.SQLException;

public class MysqlProductOrderMappingProxy extends ProductOrderMapping {

    public MysqlProductOrderMappingProxy(Order order, Product product, Integer quantity) {
        super(order, product, quantity);
    }

    @Override
    public Order getOrder() throws SQLException {
        MysqlDAOFactory mysqlDAOFactory = new MysqlDAOFactory();
        MysqlOrderDAO mysqlOrderDAO = (MysqlOrderDAO) mysqlDAOFactory.getOrderDAO();
        OrderDTO orderDTO = mysqlOrderDAO.load(super.getOrder().getIdOrder(), super.getOrder().getReservation()).get(0);
        Order order = Mapping.fromOrderDTOToOrder(orderDTO, Mapping.DBName.MY_SQL);
        setOrder(order);
        return super.getOrder();
    }

    @Override
    public Product getProduct() throws SQLException {
        MysqlDAOFactory mysqlDAOFactory = new MysqlDAOFactory();
        MysqlProductDAO mysqlProductDAO = (MysqlProductDAO) mysqlDAOFactory.getProductDAO();
        ProductDTO  productDTO = mysqlProductDAO.load(super.getProduct().getProductName()).get(0);
        Product product = Mapping.fromProductDTOToProduct(productDTO, Mapping.DBName.MY_SQL);
        setProduct(product);
        return super.getProduct();
    }
}
