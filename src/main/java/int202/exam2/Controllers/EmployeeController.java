package int202.exam2.Controllers;

import int202.exam2.Entities.Employee;
import int202.exam2.Entities.Office;
import int202.exam2.Services.EmployeeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.Banner;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    public final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/employee-list")
    public String allEmployee(Model model, @RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "7") int pageSize) {
        model.addAttribute("employee", service.getEmployee(PageRequest.of(pageNumber, pageSize)));
        return "employee-list";
    }

    @GetMapping("/create")
    public String createForm() {
        return "employee-form";
    }

    @PostMapping("/create")
    public void createEmployee(Employee employee, HttpServletResponse response) throws IOException {
        service.addEmployee(employee);
        response.sendRedirect("/employees/employee-list");
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam int id, Model model) {
        Employee employee = service.deleteEmployee(id);
        model.addAttribute("emp", employee);
        model.addAttribute("message", "Employee delete successfully");
        return "employee-detail";
    }

    @GetMapping("/update")
    public String updateForm(@RequestParam int id, Model model) {
        Employee employee = service.getEmployeeById(id);
        model.addAttribute("emp", employee);
        return "employee-update";
    }

    @PostMapping("/update")
    public void updateEmployee(Employee employee, HttpServletResponse response) throws IOException {
        service.updateEmployee(employee);
        response.sendRedirect("/employees/employee-list");
    }
}
