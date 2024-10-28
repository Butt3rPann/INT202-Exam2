package int202.exam2.Controllers;

import int202.exam2.Entities.Product;
import int202.exam2.Services.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    public ProductService service;

    @GetMapping("")
    public String getProduct(@RequestParam(defaultValue = "0") int pageNumber,
                             @RequestParam(defaultValue = "7") int pageSize,
                             Model model) {
        model.addAttribute("product", service.getProduct(PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "productCode"))));
        return "product-list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        List<String> productLines = service.getAllProductline();
        model.addAttribute("productLine", productLines);
        return "product-form";
    }

    @PostMapping("/create")
    public void createProduct(Product product, HttpServletResponse response) throws IOException {
        service.addProduct(product);
        response.sendRedirect("/products");
    }

//    @GetMapping("/create")
//    public String createForm(Model model) {
//        List<String> productLines = service.getAllProductline();
//        model.addAttribute("productLine", productLines);
//        return "product-form";
//    }
//
//    @PostMapping("/create")
//    public void createProduct(Product product, HttpServletResponse response) throws IOException {
//        service.addProduct(product);
//        response.sendRedirect("/products");
//    }
}
