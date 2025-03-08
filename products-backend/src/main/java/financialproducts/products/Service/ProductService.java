package financialproducts.products.Service;

import org.springframework.stereotype.Service;
import financialproducts.products.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

@Service
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(String product, int price, BigDecimal feeRate) {
        productRepository.addProduct(product, price, feeRate);
    }
    
    public Map<String, Object> getProducts(int page, int pageSize) {
        return productRepository.getProducts(page, pageSize);
    }

    public Map<String, Object> getProductInformation(String LikelistNo) {
        return productRepository.getProductInformation(LikelistNo);
    }

    public List<String> getProductList() {
        return productRepository.getProductList();
    }
    
    public Map<String, Object> searchProduct(int page, int pageSize,String productName) {
        return productRepository.searchProduct(page, pageSize, productName);
    }
}