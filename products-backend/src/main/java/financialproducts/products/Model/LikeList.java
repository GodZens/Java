package financialproducts.products.Model;

import java.math.BigDecimal;

public class LikeList {
    private int orderName;
    private String account;
    private BigDecimal totalFee;
    private BigDecimal totalAmount;
    
    // 構造函數
    public LikeList(int orderName, String account, BigDecimal totalFee, BigDecimal totalAmount) {
        this.orderName = orderName;
        this.account = account;
        this.totalFee = totalFee;
        this.totalAmount = totalAmount;
    }

    // Getter 和 Setter 方法
    public int getOrderName() {
        return orderName;
    }

    public void setOrderName(int orderName) {
        this.orderName = orderName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
