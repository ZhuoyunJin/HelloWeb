package info.joyindemo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;

import info.joyindemo.entity.MenuItem;

public class MenuItemDAO implements IMenuItem {
	private JdbcTemplate jdbcTemplate;
	
	public MenuItemDAO(JdbcTemplate awsTemplate) {
    	this.jdbcTemplate = awsTemplate;
    }
	@Override
	public void saveOrUpdate(MenuItem menuItem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MenuItem get(String caption) {
		return null;
	}
	
	@Override
	@Cacheable(value="menu")
	public List<MenuItem> getMenuList() {
		List<MenuItem> result = new ArrayList<MenuItem>();
		String sql = "SELECT * FROM Menu";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		for(Map<String, Object> map: list){
			System.err.println(map);
			MenuItem item = new MenuItem();
            item.setCaption(map.get("sub-menu").toString());
            item.setUrl(map.get("url").toString());
			result.add(item);
		}
		return result;
	}

}
