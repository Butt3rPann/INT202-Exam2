package int202.exam2.Repositories;

import int202.exam2.Entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("select p from Product p where (p.productName like %?1% or p.productDescription like %?1%) and p.buyPrice between ?2 and ?3")
    Page<Product> findByNameOrDescAndBuyPrice(String content, BigDecimal lower, BigDecimal upper, Pageable pageable);
}