package dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class LovDAOImpl implements LovDAO{
	private JdbcTemplate awsTemplate;
	@Override
	public LOV getLOV(String key) {
		String sql = "SELECT value FROM LOV WHERE key = '"+key+"'";
		List<Map<String, Object>> list = awsTemplate.queryForList(sql);
		LOV lov = new LOV(key);
		for(Map<String, Object> map: list){
			lov.addValue(map.get("value").toString());
		}
		return lov;
	}

}
