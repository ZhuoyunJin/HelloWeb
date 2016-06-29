package Util;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;


public class TokenUtil {
	@Autowired
	public static String getToken(String user_id, JdbcTemplate jdbcTemplate){
		String token = "";
		String nowTime = ""+System.currentTimeMillis();
		try {
//			String sql = "SELECT * FROM User WHERE user_id='" + user_id +"'";
//			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

			String random = UUID.randomUUID().toString();
			Map<String, String> treeMap = new TreeMap<String, String>();
			treeMap.put("random", random);

			token = tokenCreator(treeMap);
			String sql=String.format("update User set login_time='%s', token='%s' where id = '%s'",
					nowTime, token, user_id);
			int retVal = jdbcTemplate.update(sql);

			if (retVal<1) {
				throw new NoSuchAlgorithmException("321");
			}

		} catch (InvalidKeyException e) {
		} catch (NoSuchAlgorithmException e) {
		} catch (Exception e) {
		}
		return token;
	}

	private static String tokenCreator(Map<String, String> requestMap)
			throws InvalidKeyException, NoSuchAlgorithmException {
		String seString = "";
		String token = "";
		Iterator<String> it = requestMap.keySet().iterator();

		while (it.hasNext()) {
			String key = (String) it.next();
			String value = (String) requestMap.get(key);
			if (value == null || value.isEmpty()) {
				throw new NoSuchAlgorithmException(String.format("302[%s]", key));
			}
			if (seString.isEmpty()) {
				seString = String.format("%s:%s", key, value);
			} else {
				seString = String.format("%s|%s:%s", seString, key, value);
			}
		}

		token = MD5(seString);
		return token;
	}

	public static String MD5(String s) {
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
