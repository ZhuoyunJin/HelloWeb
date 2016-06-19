package Util;

import java.util.HashMap;
import java.util.Map;

public class MapFactory {
	private static Map<String, String> map = new HashMap<String,String>();
	private MapFactory() {
		map.put("one", "hello!");
	}
	public Map<String, String> getMap() {
		return map;
	}
}
