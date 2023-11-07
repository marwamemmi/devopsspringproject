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

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ExtendWith(MockitoExtension.class)

class ProductServiceImplTest {
    @Autowired
   // @InjectMocks
    ProductServiceImpl productService;
    @Autowired
    //@Mock
    StockRepository stockRepository;
    @Autowired
    //@Mock
    ProductRepository productRepository;


    @BeforeEach
     void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    @Transactional
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
     void testAddProductM() {
        Stock stock = new Stock();
        stock.setIdStock(1L);
        Mockito.when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));

        Product savedProduct = new Product();
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(savedProduct);

        Product product = new Product();
        product.setStock(stock);
        Product produitAjoute = productService.addProduct(product, 1L);

         Mockito.verify(productRepository).save(Mockito.any(Product.class));

        // Verify the results of the test
        assertNotNull(produitAjoute);
        assertEquals(1L, produitAjoute.getStock().getIdStock());
    }
*/




    @Test
    @Transactional
     void testRetrieveProduct() {

        Product produitFictif = new Product();
        produitFictif.setTitle("Produit Test");
        produitFictif = productRepository.save(produitFictif);

        Product produitRecupere = productService.retrieveProduct(produitFictif.getIdProduct());

        assertNotNull(produitRecupere);
        assertEquals(produitFictif.getIdProduct(), produitRecupere.getIdProduct());
    }


   /* @Test
    @Transactional
    public void testRetreiveAllProductM() {
        Product product1 = new Product();
        product1.setIdProduct(1L);
        product1.setTitle("Product 1");

        Product product2 = new Product();
        product2.setIdProduct(2L);
        product2.setTitle("Product 2");

        List<Product> sampleProducts = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(sampleProducts);

        List<Product> retrievedProducts = productService.retreiveAllProduct();


        assertEquals(sampleProducts.size(), retrievedProducts.size());
        assertEquals(sampleProducts, retrievedProducts);


        verify(productRepository, times(1)).findAll();
    }*/
   @Test
   @Transactional
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
    @Transactional
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


   /* @Test
    @Transactional
     void testRetrieveProductStockM() {
        Stock stockFictif = new Stock();
        stockFictif.setTitle("Stock Test");
        stockFictif = stockRepository.save(stockFictif);

        Product produit1 = new Product();
        produit1.setStock(stockFictif);
        productRepository.save(produit1);


        Product produit2 = new Product();
        produit2.setStock(stockFictif);
        productRepository.save(produit2);

        Mockito.when(productRepository.findByStockIdStock(stockFictif.getIdStock())).thenReturn(Arrays.asList(produit1, produit2));

        List<Product> produitsRecuperes = productService.retreiveProductStock(stockFictif.getIdStock());

        assertNotNull(produitsRecuperes);
        assertEquals(2, produitsRecuperes.size());
    }*/
}