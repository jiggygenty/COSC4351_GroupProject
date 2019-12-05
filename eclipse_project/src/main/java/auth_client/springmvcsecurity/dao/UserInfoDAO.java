package auth_client.springmvcsecurity.dao;

import java.util.List;
 
import auth_client.springmvcsecurity.model.UserInfo;

//This defines the interface for the UserInfoDAO (data access object) implemented in UserInfoDAOImpl

public interface UserInfoDAO {
     
    public UserInfo findUserInfo(String userName);
     
    // [USER,AMIN,..]
    public List<String> getUserRole(String userName);
    
    public void addUser (String username, String password);
    
    public void addRoleToUser (String username, String role);
    
    public void removeUser(String username);
    
    public List<String> getUserList ();

	List<String> getCocatenatedRoleSet(List<String> users);
     
}