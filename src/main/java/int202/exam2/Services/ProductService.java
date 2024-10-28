package int202.exam2.Services;

import int202.exam2.Entities.Product;
import int202.exam2.Repositories.ProductRepository;
import int202.exam2.Repositories.ProductlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    public ProductRepository repository;
    @Autowired
    public ProductlineRepository productlineRepository;

    public Page<Product> getProduct(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<String> getAllProductline() {
        return productlineRepository.findAllProductLine();
    }

    public Product getProductById(String productCode) {
        return repository.findById(productCode).orElse(null);
    }

    public Product addProduct(Product product) {
        if (product.getProductCode() == null || repository.existsById(product.getProductCode()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't create");
         return repository.save(product);
    }

    public Product deleteProduct(String productCode) {
        Product product = getProductById(productCode);
        if (product == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't delete");
        repository.deleteById(productCode);
        return product;
    }

    public Product updateProduct(Product product) {
        if (product == null || !repository.existsById(product.getProductCode()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't update");
        return repository.save(product);
    }

    public Page<Product> findByMix(String content, BigDecimal lower, BigDecimal upper, Pageable pageable) {
        if (lower.compareTo(upper) > 0) {
            BigDecimal tmp = lower;
            lower = upper;
            upper = tmp;
        }
        return repository.findByNameOrDescAndBuyPrice(content, lower, upper, pageable);
    }
}
