package greeting;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

public class UserDAOImpl implements UserDAO{
	private JdbcTemplate jdbcTemplate;
	 
    public UserDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public UserDAOImpl(JdbcTemplate jdbcTemplate) {
    	this.jdbcTemplate = jdbcTemplate;
    }
 
    @Override
    public void saveOrUpdate(User contact) {
        // implementation details goes here...
    }
 
    @Override
    public void delete(int contactId) {
        // implementation details goes here...
    }
 
    @Override
    public List<User> list() {
        // implementation details goes here...
    	return null;
    }
 
    @Override
    public User get(String userId) {
    	String sql = "SELECT * FROM User WHERE id='" + userId+"'";
        return jdbcTemplate.query(sql, new ResultSetExtractor<User>() {
     
            @Override
            public User extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    User user = new User();
                    user.setUserId(rs.getString("user_id"));
                    user.setName(rs.getString("login_id"));
                    return user;
                }
                return null;
            }
     
        });
    }
    
    public User get(String username, String password){
    	String sql = "SELECT * FROM User WHERE username='" + username +"' AND password='"+password+"'"; 
    	return jdbcTemplate.query(sql, new ResultSetExtractor<User>() {
            @Override
            public User extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    User user = new User();
                    user.setUserId(rs.getString("id"));
                    user.setName(rs.getString("username"));
                    return user;
                }
                return null;
            }
     
        });
    }
}
