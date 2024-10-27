package int202.exam2.Controllers;

import int202.exam2.Services.CustomerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.math.BigDecimal;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    public final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String getCustomer(Model model, @RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "7") int pageSize) {
        model.addAttribute("customer", service.getAllCustomer(PageRequest.of(pageNumber, pageSize)));
        return "customer-list";
    }

    @GetMapping("/search")
    public String searchByNameAndCredit(@RequestParam String searchParam,
                                        @RequestParam(defaultValue = "0.0") Double lower,
                                        @RequestParam(defaultValue = "228000.0") Double upper,
                                        @RequestParam(defaultValue = "0") int pageNumber,
                                        @RequestParam(defaultValue = "7") int pageSize,
                                        Model model) {
        model.addAttribute("customer", service.findByNameAndCreditLimit(searchParam, BigDecimal.valueOf(lower), BigDecimal.valueOf(upper), PageRequest.of(pageNumber, pageSize)));
        return "customer-list";
    }
}