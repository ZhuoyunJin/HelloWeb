package info.joyindemo.entity;

public class MenuItem {
	private String url;
	private String caption;
	private String parentMenu;
	public MenuItem(){}
	public MenuItem(String caption, String url) {
		this.caption = caption;
		this.url = url;
	}
	public MenuItem(String caption, String url, String parent) {
		this.caption = caption;
		this.url = url;
		this.parentMenu = parent;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getParentMenu() {
		return parentMenu;
	}
	public void setParentMenu(String parentMenu) {
		this.parentMenu = parentMenu;
	}
}
