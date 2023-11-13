package tn.esprit.devops_project;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.ProductServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ProductTestJUNIT {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    StockRepository stockRepository;
    @Test
     void FindProductByCategory() {
        Product product = Product.builder().idProduct(1L).category(ProductCategory.BOOKS).title("test").stock(null).quantity(2).price(20.20f).build();
        Product savedProduct = productRepository.save(product);

        List<Product> ProdbyCategory = productRepository.findByCategory(ProductCategory.BOOKS);
        Assertions.assertThat(ProdbyCategory).hasSize(1).containsExactly(savedProduct);

    }

    @Test
     void Find2ProductByCategory() {
        Product product1 = Product.builder().category(ProductCategory.BOOKS).title("test1").stock(null).quantity(2).price(20.20f).build();
        Product product2 = Product.builder().category(ProductCategory.BOOKS).title("test2").stock(null).quantity(2).price(20.20f).build();

        Product savedProduct1 = productRepository.save(product1);
        Product savedProduct2 = productRepository.save(product2);
        List<Product> byCategory = productRepository.findByCategory(ProductCategory.BOOKS);
        Assertions.assertThat(byCategory).hasSize(2).contains(savedProduct1, savedProduct2);
        for (Product product : byCategory) {
            Assertions.assertThat(product.getCategory()).isEqualTo(ProductCategory.BOOKS);
            Assertions.assertThat(product.getQuantity()).isEqualTo(2);
            Assertions.assertThat(product.getPrice()).isEqualTo(20.20f);
        }
    }

    @Test
     void FindProductByStockId(){

        Stock stock = Stock.builder().idStock(1L).title("hello").products(new HashSet<>()).build();
        stockRepository.save(stock);

        Product product1 = Product.builder().category(ProductCategory.ELECTRONICS).title("test").stock(stock).quantity(2).price(20.20f).build();

        Product product2 = Product.builder().category(ProductCategory.BOOKS).title("testt2").stock(stock).quantity(2).price(20.20f).build();

        Product savedProduct1 = productRepository.save(product1);
        Product savedProduct2 = productRepository.save(product2);

        List<Product> byStockIdStock = productRepository.findByStockIdStock(stock.getIdStock());

        Assertions.assertThat(byStockIdStock).hasSize(2).contains(savedProduct1, savedProduct2);

        for (Product product : byStockIdStock) {
            Assertions.assertThat(product.getStock()).isEqualTo(stock);
        }
    }


}