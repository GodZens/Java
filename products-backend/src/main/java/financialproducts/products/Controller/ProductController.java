package financialproducts.products.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import financialproducts.products.Model.Product; 
import financialproducts.products.Service.ProductService;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:8081")  
@RequestMapping("/api/products")  // 設定類別級別的路由
public class ProductController {
    
    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/addProduct") 
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        productService.addProduct(product.getProduct(), product.getPrice(), product.getFeeRate());
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
    }

    @GetMapping("/list")
    public Map<String, Object> getProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return productService.getProducts(page, pageSize);
    }

    @GetMapping("/getProductInformation")
    public ResponseEntity<Map<String, Object>> getProductInformation(@RequestParam String LikelistNo) {
        if (LikelistNo == null || LikelistNo.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "產品編號不得為空"));
        }
        Map<String, Object> productData = productService.getProductInformation(LikelistNo);
        if (productData.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "產品未找到"));
        }
        return ResponseEntity.ok(productData);
    }
}
