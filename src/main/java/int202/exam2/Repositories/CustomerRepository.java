package int202.exam2.Repositories;

import int202.exam2.Entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@RequestMapping
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Page<Customer> findCustomerByCustomerNameContainingAndCreditLimitBetween(String customerName, BigDecimal lower, BigDecimal upper, Pageable pageable);
}
