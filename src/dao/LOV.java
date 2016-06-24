package dao;

import java.util.ArrayList;
import java.util.List;

public class LOV {
	private String key;
	private List<String> value;
	public LOV(String key){
		this.key = key;
		this.value = new ArrayList<String>();
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<String> getValue() {
		return value;
	}
	public void setValue(List<String> value) {
		this.value = value;
	}
	public void addValue(String value){
		this.value.add(value);
	}
}
