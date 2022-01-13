package User;

import java.io.Serializable;

public abstract class User implements Serializable {
    private int userID;
    private String userName;
    private String FName;
    private String LName;
    private String password;
    private String email;

    public User(int ID, String userName, String password) {
        this.userID = ID;
        this.userName = userName;
        this.password = password;
    }

    public User(String userName, String FName, String LName, String password, String email) {
        this.userName = userName;
        this.FName = FName;
        this.LName = LName;
        this.password = password;
        this.email = email;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getFName() {
        return FName;
    }

    public String getLName() {
        return LName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

