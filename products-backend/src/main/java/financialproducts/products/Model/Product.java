package financialproducts.products.Model;

import java.math.BigDecimal;

public class Product {
    private String no;
    private String product;
    private int price;
    private BigDecimal feeRate;

    // 構造函數
    public Product(String no, String product, int price, BigDecimal feeRate) {
        this.no = no;
        this.product = product;
        this.price = price;
        this.feeRate = feeRate;
    }

    // Getter 和 Setter 方法
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }
}
