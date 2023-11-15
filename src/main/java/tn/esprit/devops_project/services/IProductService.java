package tn.esprit.devops_project.services;

import tn.esprit.devops_project.dto.ProductDTO;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;

import java.util.List;

public interface IProductService {

    Product addProduct(ProductDTO product, Long idStock);
    Product retrieveProduct(Long id);
    List<Product> retreiveAllProduct();
    List<Product> retrieveProductByCategory(ProductCategory category);
    void deleteProduct(Long id);
    List<Product> retreiveProductStock(Long id);


}
