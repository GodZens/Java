package financialproducts.products.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import financialproducts.products.Model.Product;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

@Repository
public class ProductRepository {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addProduct(String product, int price, BigDecimal feeRate) {
        try {
            String storedProcedureCall = "{CALL add_product(?, ?, ?)}";
            jdbcTemplate.update(storedProcedureCall, product, price, feeRate);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add product: " + e.getMessage());
        }
    }

    // 獲得產品資訊列表
    public Map<String, Object> getProducts(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String storedProcedureCall = "{CALL get_product(?, ?)}";

        return jdbcTemplate.query(storedProcedureCall, new Object[] { pageSize, offset },
                new ResultSetExtractor<Map<String, Object>>() {
                    @Override
                    public Map<String, Object> extractData(ResultSet rs) throws SQLException {
                        List<Map<String, Object>> products = new ArrayList<>();
                        int total = 0;

                        // 解析第一個 ResultSet -> 產品資料
                        while (rs.next()) {
                            Map<String, Object> product = new HashMap<>();
                            product.put("LikelistNo", rs.getString("SN"));
                            product.put("ProductName", rs.getString("ProductName"));
                            product.put("PreAccount", rs.getString("PreAccount"));
                            product.put("TotalAmount", rs.getBigDecimal("TotalAmount"));
                            product.put("TotalFee", rs.getBigDecimal("TotalFee"));
                            product.put("Account", rs.getString("Account"));
                            product.put("Email", rs.getString("Email"));
                            products.add(product);
                        }

                        // 移動到下一個 ResultSet -> 總筆數
                        if (rs.getStatement().getMoreResults()) {
                            ResultSet rs2 = rs.getStatement().getResultSet();
                            if (rs2.next()) {
                                total = rs2.getInt("total");
                            }
                        }

                        // 回傳 JSON
                        Map<String, Object> response = new HashMap<>();
                        response.put("products", products);
                        response.put("total", total);
                        return response;
                    }
                });
    }

    // 獲得產品詳細資訊
    public Map<String, Object> getProductInformation(String LikelistNo) {
        return jdbcTemplate.query("{CALL get_product_information(?)}", new Object[] { LikelistNo }, rs -> {
            if (rs.next()) {
                Map<String, Object> product = new HashMap<>();
                product.put("LikelistNo", rs.getString("SN"));
                product.put("Account", rs.getString("Account"));
                product.put("ProductName", rs.getString("ProductName"));
                product.put("Price", rs.getInt("Price"));
                product.put("FeeRate", rs.getBigDecimal("FeeRate"));
                product.put("OrderName", rs.getBigDecimal("OrderName"));
                return product;
            }
            return Collections.emptyMap(); // 找不到回傳空 Map
        });
    }

    // 獲得產品清單
    public List<String> getProductList() {
        String sql = "CALL get_productList()";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    // 獲得產品資訊列表
    public Map<String, Object> searchProduct(int page, int pageSize, String productName) {
        int offset = (page - 1) * pageSize; // 計算分頁的 offset
        String storedProcedureCall = "{CALL search(?, ?, ?)}"; // 呼叫儲存過程，並傳入產品名稱、分頁大小、和 offset

        return jdbcTemplate.query(storedProcedureCall, new Object[] { pageSize, offset, productName },
                new ResultSetExtractor<Map<String, Object>>() {
                    @Override
                    public Map<String, Object> extractData(ResultSet rs) throws SQLException {
                        List<Map<String, Object>> products = new ArrayList<>();
                        int total = 0;

                        // 解析第一個 ResultSet -> 產品資料
                        while (rs.next()) {
                            Map<String, Object> product = new HashMap<>();
                            product.put("LikelistNo", rs.getString("SN"));
                            product.put("ProductName", rs.getString("ProductName"));
                            product.put("PreAccount", rs.getString("PreAccount"));
                            product.put("TotalAmount", rs.getBigDecimal("TotalAmount"));
                            product.put("TotalFee", rs.getBigDecimal("TotalFee"));
                            product.put("Account", rs.getString("Account"));
                            product.put("Email", rs.getString("Email"));
                            products.add(product);
                        }

                        // 移動到下一個 ResultSet -> 總筆數
                        if (rs.getStatement().getMoreResults()) {
                            ResultSet rs2 = rs.getStatement().getResultSet();
                            if (rs2.next()) {
                                total = rs2.getInt("total");
                            }
                        }

                        // 回傳結果
                        Map<String, Object> response = new HashMap<>();
                        response.put("products", products); // 產品資料
                        response.put("total", total); // 總筆數
                        return response;
                    }
                });
    }
}
