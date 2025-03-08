package financialproducts.products.Service;

import org.springframework.stereotype.Service;
import financialproducts.products.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(String userId, String userName, String email, String account) {
        userRepository.addUser(userId, userName, email, account);
    }

    public List<String> getAccounts() {
        return userRepository.getAccounts();
    }
}