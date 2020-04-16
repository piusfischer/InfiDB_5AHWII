package main.java.model;

public class User {
    private String username;
    private String pwd;
    private int user_id;

    public static void main(String[] args) {}

    public User(int user_id, String username, String pwd) {
        this.user_id = user_id;
        this.username = username;
        this.pwd = pwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getUser_id(){return user_id;}

    public void setUser_id(int user_id){this.user_id=user_id;}
}
