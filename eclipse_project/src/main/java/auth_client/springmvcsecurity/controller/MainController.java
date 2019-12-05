package auth_client.springmvcsecurity.controller;
 
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import auth_client.springmvcsecurity.dao.UserInfoDAO;

/**
 * 
 * This file contains the servlet controller, which maps URL requests and provides resources to JSP pages.
 * The RequestMapping annotation gives the URL which calls each method.
 * 
 * The methods pass data to the JSP pages by setting model attributes which
 * the pages can access through the request object.
 * 
 * The string returned by each method is the name of the JSP page which will be accessed.
 * 
 */
@Controller
public class MainController {
	
	@Autowired
	private UserInfoDAO userInfoDAO;
 
   @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
   public String welcomePage(Model model) {
       model.addAttribute("title", "Welcome");
       model.addAttribute("message", "This is welcome page!");
       return "welcomePage";
   }
 
   @RequestMapping(value = "/admin", method = {RequestMethod.GET, RequestMethod.POST})
   public String adminPage(Model model) {
	   List<String> users = userInfoDAO.getUserList();
	   List<String> correspondingRoles = userInfoDAO.getCocatenatedRoleSet(users);
	   model.addAttribute("userlist", users);
	   model.addAttribute("rolelist", correspondingRoles);
	   model.addAttribute("title", "Administration");
       return "adminPage";
   }
   
   @RequestMapping(value = "/finance", method = RequestMethod.GET)
   public String financePage(Model model) {
	   model.addAttribute("title", "Finance Page");
	   model.addAttribute("message", "This is the page for finance.");
       return "financePage";
   }
   
   @RequestMapping(value = "/hr", method = RequestMethod.GET)
   public String hrPage(Model model) {
	   model.addAttribute("title", "HR Page");
	   model.addAttribute("message", "This is the page for getting resources out of humans.");
       return "hrPage";
   }
   
   @RequestMapping(value = "/engi", method = RequestMethod.GET)
   public String engiPage(Model model) {
	   model.addAttribute("title", "Engineering Page");
	   model.addAttribute("message", "This is the page for engineers.");
       return "engiPage";
   }
   
   @RequestMapping(value = "/sales", method = RequestMethod.GET)
   public String salesPage(Model model) {
	   model.addAttribute("title", "Sales Page");
	   model.addAttribute("message", "This is the page for engineers with GPAs below 2.5.");
       return "salesPage";
   }
 
   @RequestMapping(value = "/login", method = RequestMethod.GET)
   public String loginPage(Model model ) {
       return "loginPage";
   }
 
   @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
   public String logoutSuccessfulPage(Model model) {
       model.addAttribute("title", "Logout");
       return "logoutSuccessfulPage";
   }
 
   @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
   public String userInfo(Model model, Principal principal) {
	   
	   model.addAttribute("title", "User Info");
	   
       // After user login successfully.
       String userName = principal.getName();
       List<String> userEncap = new ArrayList<String>();
       userEncap.add(userName);
       List<String> roleEncap = userInfoDAO.getCocatenatedRoleSet(userEncap);
       
       model.addAttribute("message", "You are user " + userName + " with roles " + roleEncap.get(0) );
 
       System.out.println("User Name: "+ userName);
       
 
       return "userInfoPage";
   }
 
   @RequestMapping(value = "/403", method = RequestMethod.GET)
   public String accessDenied(Model model, Principal principal) {
        
       if (principal != null) {
           model.addAttribute("message", "Hi " + principal.getName()
                   + "<br> You do not have permission to access this page!");
       } else {
           model.addAttribute("message",
                   "You do not have permission to access this page!");
       }
       return "403Page";
   }
   
   @RequestMapping(value="/add_user", method = RequestMethod.POST)
   public String addUser (HttpServletRequest request, HttpServletResponse response) {
	   
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		response.addHeader("lastOp", "Add user" + username);
		
		try {
			userInfoDAO.addUser(username, password);
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (DataAccessException d) {
			response.addHeader("failureReason", d.getMessage());
		}
		
		return "adminPageRedirect";
   }
   
   @RequestMapping(value="/add_role_to_user", method = RequestMethod.POST)
   public String addRoleToUser (HttpServletRequest request, HttpServletResponse response) {
	   	String username = request.getParameter("username");
		String role = request.getParameter("role");
		
		response.addHeader("lastOp", "Add role " + role + "to user " + username);
		
		try {
			userInfoDAO.addRoleToUser(username, role);
		} catch (DataAccessException d) {
			response.addHeader("failureReason", d.getMessage());
		}
		
		return "adminPageRedirect";
   }
   
   @RequestMapping(value="/delete_user", method = RequestMethod.POST)
   public String deleteUser (HttpServletRequest request, HttpServletResponse response, Principal principal) {
	   String username = request.getParameter("username");
	
	   response.addHeader("lastOp", "Delete user " + username);
	  
	   
	   if (username.equals(principal.getName())) {
		   response.addHeader("failureReason", "You cannot delete yourself.");
	   } else {
			try {
				userInfoDAO.removeUser(username);
			} catch (DataAccessException d) {
				response.addHeader("failureReason", d.getMessage());
			}
	   }
		
		return "adminPageRedirect";
   }
   
}