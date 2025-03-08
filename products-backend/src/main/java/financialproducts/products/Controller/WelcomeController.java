package financialproducts.products.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {  // 類別名稱也應該用有意義的名稱，如 WelcomeController

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Spring Boot!";
    }
}