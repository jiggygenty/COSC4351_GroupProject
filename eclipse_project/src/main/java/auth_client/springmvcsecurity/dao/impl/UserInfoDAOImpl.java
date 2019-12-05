package auth_client.springmvcsecurity.dao.impl;
 
import java.util.ArrayList;
import java.util.List;
 
import javax.sql.DataSource;
 
import auth_client.springmvcsecurity.dao.UserInfoDAO;
import auth_client.springmvcsecurity.mapper.UserInfoMapper;
import auth_client.springmvcsecurity.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
/**
 * 
 * This class implements the UserInfoDAO interface. Its role is to provide an interface between the
 * mySQL database and the rest of the code, mainly the DBAuthenticationService and the admin methods in the controller.
 * 
 * Note that dataSource is an injected dependency configured in ApplicationContextConfig, pointing to the mySQL database.
 */

@Service
@Transactional
public class UserInfoDAOImpl extends JdbcDaoSupport implements UserInfoDAO {
 
    @Autowired
    public UserInfoDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
  
 
    @Override
    public UserInfo findUserInfo(String userName) {
        String sql = "Select u.username,u.password"//
                + " from USERS u where u.username = ?";
 
        Object[] params = new Object[] { userName };
        UserInfoMapper mapper = new UserInfoMapper();
        try {
            UserInfo userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return userInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
 
    
 
    @Override
    public List<String> getUserRole(String userName) {
        String sql1 = "Select u.role_id "//
                + " from USER_ROLES u where u.username = ? ";
         
        Object[] params1 = new Object[] { userName };
         
        List<Integer> role_ids = this.getJdbcTemplate().queryForList(sql1,  Integer.class, params1);
        
        String sql2 = "Select r.role_name from ROLES r where r.role_id = ?";
        List<String> roles = new ArrayList<String>();
        
        for (int id : role_ids) {
        	Object[] params2 = new Object[] {id};
        	String role = this.getJdbcTemplate().queryForObject(sql2, String.class, params2);
        	roles.add(role);
        }
        
        return roles;
    }
    
    // Add a user and set them to have the default role only
    @Override
    public void addUser (String username, String password) {
    	String sql1 = "Insert into USERS (username, password) values (?, ?)";
    	String sql2 = "Insert into USER_ROLES (username, role_id) values (?, ?)";
    	
    	Object[] params1 = new Object[] {username, password};
    	Object[] params2 = new Object[] {username, 0};
    	
    	this.getJdbcTemplate().update(sql1, params1);
    	this.getJdbcTemplate().update(sql2, params2);
    }
    
    @Override
    public void addRoleToUser (String username, String role) {
    	String sql1 = "Select r.role_id from ROLES r where r.role_name = ?";
    	String sql2 = "Insert into USER_ROLES (username, role_id) values (?, ?)";
    	
    	Object[] params1 = new Object[] {role};
    	
		Integer role_id = this.getJdbcTemplate().queryForObject(sql1, Integer.class, params1);
		Object[] params2 = new Object[] {username, role_id};
		this.getJdbcTemplate().update(sql2, params2);
    }
    
    @Override
    public void removeUser (String username) {
    	String sql1 = "Delete from USER_ROLES where username = ?";
    	String sql2 = "Delete from USERS where username = ?";
    	
    	Object[] params = new Object[] {username};
    	
    	this.getJdbcTemplate().update(sql1, params);
    	this.getJdbcTemplate().update(sql2, params);
    }
    
    @Override
    public List<String> getUserList () {
    	String sql ="Select username from USERS";
    	
    	List<String> users = this.getJdbcTemplate().queryForList(sql, String.class);
    	
    	return users;
    }
    
    @Override
    public List<String> getCocatenatedRoleSet (List<String> users) {
    	List<String> roleSet = new ArrayList<String>();
    	for (String user : users) {
    		StringBuilder strbld = new StringBuilder(32);
    		List<String> roles = this.getUserRole(user);
    		for (String role : roles) {
    			strbld.append(role);
    			strbld.append(", ");
    		}
    		// remove last comma
    		strbld.delete(strbld.length()-2, strbld.length()-1);
    		roleSet.add(strbld.toString());
    	}
    	return roleSet;
    }
     
}