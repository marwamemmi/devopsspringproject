package tn.esprit.devops_project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import tn.esprit.devops_project.services.ProductServiceImpl;
import tn.esprit.devops_project.services.StockServiceImpl;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.repositories.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

class ProductServiceImplTest {
    @Autowired
     ProductServiceImpl productService;
    @Autowired
    StockRepository stockRepository;
    @Autowired
    ProductRepository productRepository;

    @Test
    public void testAddProduct() {

        Stock stock = new Stock();
        stock.setIdStock(1L);
        stockRepository.save(stock);

        Product product = new Product();
        product.setStock(stock);

        // Appelez la méthode à tester
        Product produitAjoute = productService.addProduct(product, 1L);

        // Récupérez le produit de la base de données
        Optional<Product> produitRecupere = productRepository.findById(produitAjoute.getIdProduct());

        // Vérifiez si le produit a été correctement ajouté à la base de données
        assertTrue(produitRecupere.isPresent());
        assertEquals(produitAjoute.getIdProduct(), produitRecupere.get().getIdProduct());
    }

    @Test
    public void testRetrieveProduct() {
        // Créez un produit réel et ajoutez-le à la base de données
        Product produitFictif = new Product();
        produitFictif.setTitle("Produit Test");
        produitFictif = productRepository.save(produitFictif);

        // Appelez la méthode à tester
        Product produitRecupere = productService.retrieveProduct(produitFictif.getIdProduct());

        // Vérifiez si le produit récupéré est correct
        assertNotNull(produitRecupere);
        assertEquals(produitFictif.getIdProduct(), produitRecupere.getIdProduct());
    }

    @Test
    public void testRetreiveAllProduct() {
        // Créez plusieurs produits réels et ajoutez-les à la base de données
        Product produit1 = new Product();
        produit1.setTitle("Produit 1");
        productRepository.save(produit1);

        Product produit2 = new Product();
        produit2.setTitle("Produit 2");
        productRepository.save(produit2);

        // Appelez la méthode à tester
        List<Product> produitsRécupérés = productService.retreiveAllProduct();

        // Vérifiez si la liste de produits récupérés est correcte
        assertNotNull(produitsRécupérés);
        assertEquals(2, produitsRécupérés.size());
    }

    @Test
    public void testRetrieveProductByCategory() {
        // Créez une catégorie fictive
        ProductCategory categorieFictive = ProductCategory.ELECTRONICS;

        // Créez plusieurs produits réels avec la même catégorie et ajoutez-les à la base de données
        Product produit1 = new Product();
        produit1.setCategory(categorieFictive);
        productRepository.save(produit1);

        Product produit2 = new Product();
        produit2.setCategory(categorieFictive);
        productRepository.save(produit2);

        // Appelez la méthode à tester
        List<Product> produitsRécupérés = productService.retrieveProductByCategory(categorieFictive);

        // Vérifiez si la liste de produits récupérés est correcte
        assertNotNull(produitsRécupérés);
        assertEquals(2, produitsRécupérés.size());
    }

    @Test
    public void testDeleteProduct() {
        // Créez un produit réel et ajoutez-le à la base de données
        Product produitFictif = new Product();
        produitFictif.setTitle("Produit à supprimer");
        produitFictif = productRepository.save(produitFictif);

        // Appelez la méthode à tester pour supprimer le produit
        Long productId = produitFictif.getIdProduct(); // Store the product ID in a final variable
        productService.deleteProduct(productId);

        // Vérifiez que le produit a été supprimé en essayant de le récupérer
        assertThrows(NoSuchElementException.class, () -> productRepository.findById(productId).get());
    }


    @Test
    public void testRetreiveProductStock() {
        // Créez un stock réel et ajoutez-le à la base de données
        Stock stockFictif = new Stock();
        stockFictif.setTitle("Stock Test");
        stockFictif = stockRepository.save(stockFictif);

        // Créez plusieurs produits réels associés au même stock et ajoutez-les à la base de données
        Product produit1 = new Product();
        produit1.setStock(stockFictif);
        productRepository.save(produit1);

        Product produit2 = new Product();
        produit2.setStock(stockFictif);
        productRepository.save(produit2);

        // Appelez la méthode à tester
        List<Product> produitsRécupérés = productService.retreiveProductStock(stockFictif.getIdStock());

        // Vérifiez si la liste de produits récupérés est correcte
        assertNotNull(produitsRécupérés);
        assertEquals(2, produitsRécupérés.size());
    }

}