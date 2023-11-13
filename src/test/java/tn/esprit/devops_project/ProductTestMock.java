package tn.esprit.devops_project;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.ProductServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ProductTest {


    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
     void AddProduct() {
        Product product = Product.builder().idProduct(1L).category(ProductCategory.ELECTRONICS).title("test").stock(null).quantity(2).price(20.20f).build();
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
        when(stockRepository.findById(1L)).thenReturn(Optional.ofNullable(Mockito.mock(Stock.class)));
        Product addedProduct = productService.addProduct(product, 1L);
        Assertions.assertThat(addedProduct).isNotNull();
        Assertions.assertThat(addedProduct.getIdProduct()).isEqualTo(1L);
        Assertions.assertThat(addedProduct.getCategory()).isEqualTo(ProductCategory.ELECTRONICS);
        Assertions.assertThat(addedProduct.getTitle()).isEqualTo("test");
        Assertions.assertThat(addedProduct.getQuantity()).isEqualTo(2);
        Assertions.assertThat(addedProduct.getPrice()).isEqualTo(20.20f);

    }

  

    @Test
     void RetrieveAllProducts() {
        Product product = Product.builder().idProduct(1L).category(ProductCategory.BOOKS).title("test").stock(null).quantity(2).price(20.20f).build();
        Product product1 = Product.builder().idProduct(2L).category(ProductCategory.BOOKS).title("booookkkk").stock(null).quantity(1).price(15.00f).build();
        List<Product> listProducts = Arrays.asList(product);
        when(productRepository.findAll()).thenReturn(listProducts);
        List<Product> actualProducts = productService.retreiveAllProduct();

        Assertions.assertThat(actualProducts).isEqualTo(listProducts)
                .containsExactlyInAnyOrderElementsOf(listProducts);

        for (Product actualProduct : actualProducts) {
            Product expectedProduct = listProducts.stream()
                    .filter(p -> p.getIdProduct().equals(actualProduct.getIdProduct()))
                    .findFirst()
                    .orElse(null);

            Assertions.assertThat(expectedProduct).isNotNull();
            Assertions.assertThat(actualProduct.getCategory()).isEqualTo(expectedProduct.getCategory());
            Assertions.assertThat(actualProduct.getTitle()).isEqualTo(expectedProduct.getTitle());
            // Ajoutez d'autres assertions selon vos besoins
        }

    }
    @Test
     void RetrieveProduct() {
        Long productId = 1L;
        Product expectedProduct = Product.builder().idProduct(productId).category(ProductCategory.BOOKS).title("test").stock(null).quantity(2).price(20.20f).build();
        when(productRepository.findById(productId)).thenReturn(Optional.of(expectedProduct));
        Product actualProduct = productService.retrieveProduct(productId);
        Assertions.assertThat(actualProduct).isEqualTo(expectedProduct);
    }
    @Test
     void DeleteProduct() {
        Long productId = 1L;
        productService.deleteProduct(productId);
        Assertions.assertThatCode(() -> productService.retrieveProduct(productId))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("Product not found");
    }

    @Test
     void RetrieveProductStock() {
        Long stockId = 1L;

        Product product = Product.builder().idProduct(1L).category(ProductCategory.BOOKS).title("test").stock(null).quantity(2).price(20.20f).build();
        List<Product> expectedProducts = Arrays.asList(product);
        when(productRepository.findByStockIdStock(stockId)).thenReturn(expectedProducts);
        List<Product> actualProducts = productService.retreiveProductStock(stockId);
        Assertions.assertThat(actualProducts).isEqualTo(expectedProducts);
    }

}