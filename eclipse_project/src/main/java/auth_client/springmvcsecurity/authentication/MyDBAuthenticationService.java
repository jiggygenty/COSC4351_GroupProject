package auth_client.springmvcsecurity.authentication;

import java.util.ArrayList;
import java.util.List;

import auth_client.springmvcsecurity.dao.UserInfoDAO;
import auth_client.springmvcsecurity.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 
 * This class implements an authentication service using the mySQL database
 * through the UserInfoDAO. The details method is called to link roles
 * to a user whenever one logs in.
 *
 */
@Service
public class MyDBAuthenticationService implements UserDetailsService {

	@Autowired
	private UserInfoDAO userInfoDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo = userInfoDAO.findUserInfo(username);
		System.out.println("UserInfo= " + userInfo);

		if (userInfo == null) {
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}

		// [USER,ADMIN,..]
		List<String> roles = userInfoDAO.getUserRole(username);

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
			grantList.add(authority);
		}
		
		UserDetails userDetails = (UserDetails) new User(userInfo.getUserName(), //
				userInfo.getPassword(), grantList);

		return userDetails;
	}

}