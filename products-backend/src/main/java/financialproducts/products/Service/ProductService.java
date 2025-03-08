package financialproducts.products.Service;

import org.springframework.stereotype.Service;
import financialproducts.products.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Map;

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
}