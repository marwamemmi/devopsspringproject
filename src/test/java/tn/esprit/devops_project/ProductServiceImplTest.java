package tn.esprit.devops_project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import tn.esprit.devops_project.services.ProductServiceImpl;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.repositories.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ExtendWith(MockitoExtension.class)

class ProductServiceImplTest {
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    StockRepository stockRepository;
    @Autowired
    ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productServiceM;

    @Mock
    private StockRepository stockRepositoryM;

    @Mock
    private ProductRepository productRepositoryM;

    @BeforeEach
     void setup() {
        //MockitoAnnotations.initMocks(this);
    }
    @Test
     void testAddProduct() {

        Stock stock = new Stock();
        stock.setIdStock(1L);
        stockRepository.save(stock);

        Product product = new Product();
        product.setStock(stock);


        Product produitAjoute = productService.addProduct(product, 1L);


        Optional<Product> produitRecupere = productRepository.findById(produitAjoute.getIdProduct());

        assertTrue(produitRecupere.isPresent());
        assertEquals(produitAjoute.getIdProduct(), produitRecupere.get().getIdProduct());
        assertNotNull(produitAjoute, "The added product should not be null");

    }

    /*
    @Test
    public void testAddProductM() {
        // Set up the behavior of the stockRepositoryM mock
        Stock stock = new Stock();
        stock.setIdStock(1L);
        Mockito.when(stockRepositoryM.findById(1L)).thenReturn(Optional.of(stock));

        // Set up the behavior of the productRepositoryM mock
        Product savedProduct = new Product();  // Define the expected saved product
        Mockito.when(productRepositoryM.save(Mockito.any(Product.class))).thenReturn(savedProduct);

        // Call the method to be tested
        Product product = new Product();
        product.setStock(stock);
        Product produitAjoute = productServiceM.addProduct(product, 1L);

        // Verify if the method save of the mock ProductRepository has been called
        Mockito.verify(productRepositoryM).save(Mockito.any(Product.class));

        // Verify the results of the test
        assertNotNull(produitAjoute);
        assertEquals(1L, produitAjoute.getStock().getIdStock());
    }

*/



    @Test
     void testRetrieveProduct() {

        Product produitFictif = new Product();
        produitFictif.setTitle("Produit Test");
        produitFictif = productRepository.save(produitFictif);

        Product produitRecupere = productService.retrieveProduct(produitFictif.getIdProduct());

        assertNotNull(produitRecupere);
        assertEquals(produitFictif.getIdProduct(), produitRecupere.getIdProduct());
    }

    @Test
     void testRetreiveAllProduct() {
        Product produit1 = new Product();
        produit1.setTitle("Produit 1");
        productRepository.save(produit1);

        Product produit2 = new Product();
        produit2.setTitle("Produit 2");
        productRepository.save(produit2);

        List<Product> produitsRécupérés = productService.retreiveAllProduct();

        assertNotNull(produitsRécupérés);
        assertEquals(2, produitsRécupérés.size());
    }

    @Test
     void testRetrieveProductByCategory() {
        ProductCategory categorieFictive = ProductCategory.ELECTRONICS;

          Product produit1 = new Product();
        produit1.setCategory(categorieFictive);
        productRepository.save(produit1);

        Product produit2 = new Product();
        produit2.setCategory(categorieFictive);
        productRepository.save(produit2);
     List<Product> produitsRécupérés = productService.retrieveProductByCategory(categorieFictive);
        assertNotNull(produitsRécupérés);
        assertEquals(2, produitsRécupérés.size());
    }

    @Test
     void testDeleteProduct() {
        Product produitFictif = new Product();
        produitFictif.setTitle("Produit à supprimer");
        produitFictif = productRepository.save(produitFictif);
        Long productId = produitFictif.getIdProduct();
        productService.deleteProduct(productId);
        assertThrows(NoSuchElementException.class, () -> productRepository.findById(productId).get());
    }


    @Test
     void testRetreiveProductStock() {
        Stock stockFictif = new Stock();
        stockFictif.setTitle("Stock Test");
        stockFictif = stockRepository.save(stockFictif);

        Product produit1 = new Product();
        produit1.setStock(stockFictif);
        productRepository.save(produit1);

        Product produit2 = new Product();
        produit2.setStock(stockFictif);
        productRepository.save(produit2);

        List<Product> produitsRécupérés = productService.retreiveProductStock(stockFictif.getIdStock());

        assertNotNull(produitsRécupérés);
        assertEquals(2, produitsRécupérés.size());
    }

    /*
    @Test
    public void testRetrieveProductStockM() {
        Stock stockFictif = new Stock();
        stockFictif.setTitle("Stock Test");
        stockFictif = stockRepository.save(stockFictif);

        Product produit1 = new Product();
        produit1.setStock(stockFictif);
        productRepository.save(produit1);


        Product produit2 = new Product();
        produit2.setStock(stockFictif);
        productRepository.save(produit2);

        Mockito.when(productRepositoryM.findByStockIdStock(stockFictif.getIdStock())).thenReturn(Arrays.asList(produit1, produit2));

        List<Product> produitsRecuperes = productServiceM.retreiveProductStock(stockFictif.getIdStock());

        assertNotNull(produitsRecuperes);
        assertEquals(2, produitsRecuperes.size());
    }*/
}