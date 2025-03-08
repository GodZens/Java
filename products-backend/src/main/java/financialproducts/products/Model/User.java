package financialproducts.products.Model;

public class User {
    private String userId;
    private String userName;
    private String email;
    private String account;

    // Constructor
    public User(String userId, String userName, String email, String account) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.account = account;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}