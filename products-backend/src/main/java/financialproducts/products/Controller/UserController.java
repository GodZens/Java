package financialproducts.products.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import financialproducts.products.Model.User;
import financialproducts.products.Service.UserService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")  
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser") 
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.addUser(user.getUserId(), user.getUserName(), user.getEmail(), user.getAccount());
        return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully");
    }

    @GetMapping("/getAccounts")
    public List<String> getAccounts() {
        try {
            return userService.getAccounts();
        } catch (Exception e) {
            throw new RuntimeException("資料載入失敗", e);
        }
    }
}