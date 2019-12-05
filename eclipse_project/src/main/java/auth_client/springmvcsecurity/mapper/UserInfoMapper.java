package auth_client.springmvcsecurity.mapper;

// This class maps username and password to values in a struct, used by the login manager

import java.sql.ResultSet;
import java.sql.SQLException;
 
import auth_client.springmvcsecurity.model.UserInfo;
import org.springframework.jdbc.core.RowMapper;
 
public class UserInfoMapper implements RowMapper<UserInfo> {
 
    @Override
    public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
 
        String userName = rs.getString("username");
        String password = rs.getString("password");
 
        return new UserInfo(userName, password);
    }
 
}