package main.model.database.dao;

import main.model.database.dto.ProductDTO;

public abstract class ProductDAO extends AbstractDAO<ProductDTO> {

    protected ProductDAO(DBManager dbManager) {
        super(dbManager);
    }

    //query varie
}
