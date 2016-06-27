package dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class LovDAOImpl implements LovDAO{
	private JdbcTemplate awsTemplate;
	
	public LovDAOImpl(DataSource dataSource) {
    	awsTemplate = new JdbcTemplate(dataSource);
    }
	public LovDAOImpl(JdbcTemplate awsTemplate) {
    	this.awsTemplate = awsTemplate;
    }
	@Override
	public LOV getLOV(String key) {
		String sql = "SELECT value FROM LOV L WHERE L.key = '"+key+"'";
		List<Map<String, Object>> list = awsTemplate.queryForList(sql);
		LOV lov = new LOV(key);
		for(Map<String, Object> map: list){
			lov.addValue(map.get("value").toString());
		}
		return lov;
	}

}
