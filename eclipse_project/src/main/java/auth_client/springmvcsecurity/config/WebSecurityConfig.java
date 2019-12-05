package auth_client.springmvcsecurity.config;

import auth_client.springmvcsecurity.authentication.MyDBAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 
/**
 * 
 * This class tells the application which user roles are allowed to access each page,
 * and also provides configuration information for the login form (where to go for a successful/failed
 * login etc.) and the logout page.
 *
 */

@Configuration
// @EnableWebSecurity = @EnableWebMVCSecurity + Extra features
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
    
    @Autowired
    MyDBAuthenticationService myDBAauthenticationService;
 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
 
        // User info stored in a MYSQL database.
        auth.userDetailsService(myDBAauthenticationService);
 
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
 
        http.csrf().disable();
 
        // The pages does not require login
        http.authorizeRequests().antMatchers("/", "/welcome", "/login", "/logout").permitAll();
 
        // /userInfo page requires login
        // If no login, it will redirect to /login page.
        http.authorizeRequests().antMatchers("/userInfo").access("hasRole('ROLE_BASE')");	
 
        // For SUPER only.
        http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_SUPER')");
        http.authorizeRequests().antMatchers("/add_user").access("hasRole('ROLE_SUPER')");
        http.authorizeRequests().antMatchers("/add_role_to_user").access("hasRole('ROLE_SUPER')");
        http.authorizeRequests().antMatchers("/delete_user").access("hasRole('ROLE_SUPER')");
        
        // for appropriate roles, or SUPER
        http.authorizeRequests().antMatchers("/finance").access("hasAnyRole('ROLE_SUPER', 'ROLE_FINANCE')");
        http.authorizeRequests().antMatchers("/sales").access("hasAnyRole('ROLE_SUPER', 'ROLE_SALES')");
        http.authorizeRequests().antMatchers("/hr").access("hasAnyRole('ROLE_SUPER', 'ROLE_HR')");
        http.authorizeRequests().antMatchers("/engi").access("hasAnyRole('ROLE_SUPER', 'ROLE_ENGI')");
 
        // When the user has logged in as XX.User_Roles
        // But access a page that requires role YY,
        // AccessDeniedException will throw.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
 
        // Config for Login Form
        http.authorizeRequests().and().formLogin()//
                // Submit URL of login page.
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/userInfo")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Config for Logout Page
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
 
    }
}