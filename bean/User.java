package 数据库12306.bean;

public class User {
    private String user_id;
    private String user_name;
    private String user_password;
    private String user_idcard;
    private String user_phone;
    private String user_type;

    public User() {
    }

    public User(String user_id, String user_name, String user_password, String user_idcard, String user_phone, String user_type) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_idcard = user_idcard;
        this.user_phone = user_phone;
        this.user_type = user_type;
    }

    public User(String user_name, String user_password) {
        this.user_name = user_name;
        this.user_password = user_password;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_idcard() {
        return user_idcard;
    }

    public void setUser_idcard(String user_idcard) {
        this.user_idcard = user_idcard;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    @Override
    public String toString() {
        return "user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_idcard='" + user_idcard + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", user_type='" + user_type + '\'';
    }
}
