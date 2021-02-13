package main.model.database.dto;

public class ProductDTO {
    private String productName;
    private Float price;
    private String category;

    public ProductDTO() {
        this(null);
    }

    public ProductDTO(String productName) {
        this(productName, 0, null);
    }

    public ProductDTO(String productName, float price, String category) {
        this.productName = productName;
        this.price = price;
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public ProductDTO setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public Float getPrice() {
        return price;
    }

    public ProductDTO setPrice(Float price) {
        this.price = price;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public ProductDTO setCategory(String category) {
        this.category = category;
        return this;
    }
}
