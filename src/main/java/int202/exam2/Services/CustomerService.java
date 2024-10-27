package int202.exam2.Services;

import int202.exam2.Entities.Customer;
import int202.exam2.Repositories.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CustomerService {
    public final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Page<Customer> getAllCustomer(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Customer getCustomerById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Customer addCustomer(Customer customer) {
        if(customer == null || repository.existsById(customer.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Can't add, customer id %s already exists", customer.getId()));
        return repository.save(customer);
    }

    public Customer updateCustomer(Customer customer) {
        if(customer == null || !repository.existsById(customer.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't update");
        return repository.save(customer);
    }

    public Customer deleteCustomer(int id) {
        Customer customer = getCustomerById(id);
        if(customer == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't delete");
        repository.deleteById(id);
        return customer;
    }

    public Page<Customer> findByNameAndCreditLimit(String searchParam, BigDecimal lower, BigDecimal upper, Pageable pageable) {
        if(lower.compareTo(upper) > 0) { // lower > upper
            BigDecimal tmp = upper;
            upper = lower;
            lower = tmp;
        }
        return repository.findCustomerByCustomerNameContainingAndCreditLimitBetween(searchParam, lower, upper, pageable);
    }
}
