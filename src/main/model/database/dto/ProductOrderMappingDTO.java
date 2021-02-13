package main.model.database.dto;

public class ProductOrderMappingDTO {
    private OrderDTO order;
    private ProductDTO product;
    private Integer quantity;

    public ProductOrderMappingDTO() {
        this(null, null);
    }

    public ProductOrderMappingDTO(OrderDTO order, ProductDTO product) {
        this(order, product, 0);
    }

    public ProductOrderMappingDTO(OrderDTO order, ProductDTO product, int quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public ProductOrderMappingDTO setOrder(OrderDTO order) {
        this.order = order;
        return this;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public ProductOrderMappingDTO setProduct(ProductDTO product) {
        this.product = product;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ProductOrderMappingDTO setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
}
