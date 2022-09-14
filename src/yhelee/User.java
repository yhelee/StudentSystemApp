package yhelee;

public class User {
    private String username;
    private String password;
    private String id;
    private String phoneNum;

    public User() {
    }

    public User(String username, String password, String id, String phoneNum) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.phoneNum = phoneNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
