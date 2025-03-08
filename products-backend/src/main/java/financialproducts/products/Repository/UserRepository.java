package financialproducts.products.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addUser(String userId, String userName, String email, String account) {
        try {
            String storedProcedureCall = "{CALL add_user(?, ?, ?, ?)}";
            jdbcTemplate.update(storedProcedureCall, userId, userName, email, account);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add user: " + e.getMessage());
        }
    }

    public List<String> getAccounts() {
        String sql = "CALL get_account()"; 
        return jdbcTemplate.queryForList(sql, String.class);
    }
}
