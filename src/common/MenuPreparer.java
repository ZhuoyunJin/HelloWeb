package common;

import org.apache.tiles.preparer.PreparerException;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import info.joyindemo.dao.MenuItemDAO;
import info.joyindemo.entity.MenuItem;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.Attribute;

import java.util.ArrayList;
import java.util.List;
 
public class MenuPreparer implements ViewPreparer {
	@Autowired
	MenuItemDAO menuItemDAO;
	
	public void execute(Request request, AttributeContext attributeContext) 
			throws PreparerException {
//		ArrayList<MenuItem>  menu = new ArrayList<MenuItem>();
//		menu.add(new MenuItem("Home", "/index"));
//		menu.add(new MenuItem("About","about.jsp"));
		List<MenuItem> menu = menuItemDAO.getMenuList();
		attributeContext.putAttribute("menu", new Attribute(menu),true);
	}
 
//  public static class MenuItem {
//    private String url;
//    private String caption;
//    public MenuItem(String caption, String url) {
//        this.caption = caption;
//        this.url = url;
//    }
//    public String getUrl() {return url;}
//    public String getCaption() {return caption;}
//  }
}
