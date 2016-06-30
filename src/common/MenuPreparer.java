package common;

import org.apache.tiles.preparer.PreparerException;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.Attribute;

import java.util.ArrayList;
 
public class MenuPreparer implements ViewPreparer {
 
	public void execute(Request request, AttributeContext attributeContext) 
			throws PreparerException {
		ArrayList<MenuPreparer.MenuItem>  menu = new ArrayList<MenuPreparer.MenuItem>();
		menu.add(new MenuItem("Home", "/index"));
		menu.add(new MenuItem("About","about.jsp"));
		attributeContext.putAttribute("menu", new Attribute(menu),true);
	}
 
  public static class MenuItem {
    private String url;
    private String caption;
    public MenuItem(String caption, String url) {
        this.caption = caption;
        this.url = url;
    }
    public String getUrl() {return url;}
    public String getCaption() {return caption;}
  }
}
