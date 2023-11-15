package tn.esprit.devops_project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;

import javax.persistence.ManyToOne;
import java.io.Serializable;
@Builder
public class ProductDTO implements Serializable {
    private Long idProduct;
    private String title;
    private float price;
    private int quantity;
    private ProductCategory category;
    @ManyToOne
    @JsonIgnore
    Stock stock;

    public ProductDTO(Long idProduct, String title, float price, int quantity, ProductCategory category, Stock stock) {
        this.idProduct = idProduct;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.stock = stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Stock getStock() {
        return stock;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
}
