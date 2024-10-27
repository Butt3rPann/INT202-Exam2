package int202.exam2.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
    @RequestMapping("/home")
    public String main() {
        return "home";
    }
}
