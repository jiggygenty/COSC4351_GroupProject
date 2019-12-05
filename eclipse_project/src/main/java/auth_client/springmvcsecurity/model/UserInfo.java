package auth_client.springmvcsecurity.model;
 
//This file defines a struct used by the login manager to get usernames and passwords.

public class UserInfo {
 
    private String userName;
    private String password;
     
    public UserInfo()  {
         
    }
 
    public UserInfo(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
 
    public String getUserName() {
        return userName;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
 
}