package financialproducts.products.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;


@Repository
public class LikeListRepository {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public LikeListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addLikeList(String productName, Integer price, Double feerate, String account, Integer ordername) {
        try {
            String storedProcedureCall = "{CALL add_likelist(?, ?, ?, ?, ?)}";
            jdbcTemplate.update(storedProcedureCall, productName, price, feerate, account, ordername);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add product: " + e.getMessage());
        }
    }

    public void editLikeList(String LikelistNo,String productName,Integer price,Double feerate,String account,Integer ordername) {
        try {
            String storedProcedureCall = "{CALL edit_product(?, ?, ?, ?, ?, ?)}";
            jdbcTemplate.update(storedProcedureCall, LikelistNo, productName, price, feerate, account, ordername);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add product: " + e.getMessage());
        }
    }

    public void deleteLikeList(String LikelistNo) {
        try {
            String storedProcedureCall = "{CALL delete_product(?)}";
            jdbcTemplate.update(storedProcedureCall, LikelistNo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add product: " + e.getMessage());
        }
    }
}
