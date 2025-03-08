package financialproducts.products.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import financialproducts.products.Model.LikeList; 
import financialproducts.products.Service.LikeListService;
import java.util.Map;  // 添加這行引入 Map
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin(origins = "http://localhost:8081")  
@RequestMapping("/api/LikeList")  // 設定類別級別的路由
public class LikeListController {
    
    @Autowired
    private final LikeListService likeListService;

    public LikeListController(LikeListService likeListService) {
        this.likeListService = likeListService;
    }
    //新增商品
    @PostMapping("/addLikeList") 
    public ResponseEntity<Object> addLikeList(@RequestBody Map<String, Object> requestBody) {
        String productName = (String) requestBody.get("productName");
        Integer price = (Integer) requestBody.get("price");
        Double feerate = (Double) requestBody.get("feerate");
        String account = (String) requestBody.get("account");
        Integer ordername = (Integer) requestBody.get("ordername");
        likeListService.addLikeList(productName, price, feerate, account, ordername);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
    }

    @PostMapping("/editLikeList") 
    public ResponseEntity<Object> editLikeList(@RequestBody Map<String, Object> requestBody) {
        String LikelistNo = (String) requestBody.get("Likelist");
        String productName = (String) requestBody.get("ProductName");
        Integer price = (Integer) requestBody.get("Price");
        Double feerate = (Double) requestBody.get("FeeRate");
        String account = (String) requestBody.get("Account");
        Integer orderName = (Integer) requestBody.get("OrderName");
        likeListService.editLikeList(LikelistNo, productName, price, feerate, account, orderName);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
    }

    @PostMapping("/deleteLikeList") 
    public ResponseEntity<Object> deleteLikeList(@RequestBody Map<String, Object> requestBody) {
        String LikelistNo = (String) requestBody.get("LikelistNo");
        likeListService.deleteLikeList(LikelistNo);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
    }
    
}
