package info.joyindemo.dao;

import java.util.List;

import info.joyindemo.entity.MenuItem;

public interface IMenuItem {
	public void saveOrUpdate(MenuItem menuItem);

	public MenuItem get(String caption);
	
	public List<MenuItem> getMenuList();
}
