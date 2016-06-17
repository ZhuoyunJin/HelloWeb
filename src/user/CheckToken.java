package user;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class CheckToken {
	JdbcTemplate jdbcTemplate;
	public CheckToken(DataSource dataSource){
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public CheckToken(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public String check(String token){
		String sql = "select * from m_user_author where session = '" + token + "'";
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		if(list.size()<1) return "";
		else
			return list.get(0).get("user_id").toString();
	}
}
