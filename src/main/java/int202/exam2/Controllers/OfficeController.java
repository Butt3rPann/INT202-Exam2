package int202.exam2.Controllers;

import int202.exam2.Entities.Office;
import int202.exam2.Services.OfficeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/offices")
public class OfficeController {
    public final OfficeService service;

    public OfficeController(OfficeService service) {
        this.service = service;
    }

    @GetMapping("/office-list")
    public String allOffices(Model model) {
        List<Office> offices = service.getAllOffices();
        model.addAttribute("office", offices);
        return "office-list";
    }

    @GetMapping("/create")
    public String createForm() {
        return "office-form";
    }

    @PostMapping("/create")
    public void createOffice(Office office, HttpServletResponse response) throws IOException {
        service.addOffice(office);
        response.sendRedirect("/offices/office-list");
    }

    @GetMapping("/delete")
    public String deleteOffice(@RequestParam String officeCode, Model model) {
        Office office = service.delateOfficeById(officeCode);
        model.addAttribute("office", office);
        model.addAttribute("message", "Office deleted successfully");
        return "office-detail";
    }

    @GetMapping("/update")
    public String updateForm(@RequestParam String officeCode, Model model) {
        Office office = service.getOffice(officeCode);
        model.addAttribute("office", office);
        return "office-update";
    }

    @PostMapping("/update")
    public void updateOffice(Office office, HttpServletResponse response) throws IOException {
        service.updateOffice(office);
        response.sendRedirect("/offices/office-list");
    }

}

