package tn.esprit.devops_project.dto;

import lombok.Builder;
import tn.esprit.devops_project.entities.ProductCategory;

import java.io.Serializable;
@Builder
public class ProductDTO implements Serializable {
    private Long idProduct;
    private String title;
    private float price;
    private int quantity;
    private ProductCategory category;
    // Vous pouvez ajouter d'autres champs nécessaires

    // Ajoutez les getters et setters
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
