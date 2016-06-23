package user;

import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import Util.TokenUtil;
import greeting.User;
import greeting.UserDAOImpl;

@Controller
public class UserController {
	private static final String TOKEN_SESSION = "xxx-token";
//	@Autowired
//	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private JdbcTemplate bussTemplate;
	@Resource
	private Map<String, String> mapInstance;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(path="login")
	public String login(ModelMap model, HttpServletRequest request, HttpSession session) {
		String name=request.getParameter("username");
		String pwd=request.getParameter("password");
		UserDAOImpl test = new UserDAOImpl(jdbcTemplate);
		User user = test.get(name,pwd);
		if(user==null){
			model.put("ErrorMessage", "User not found.");
			return "error";
		}
		String token = TokenUtil.getToken(user.getUserId(),jdbcTemplate);
		System.out.println("token: "+ token);
		model.put("id", user.getUserId());
		model.put("Name", user.getName());
//		model.addAttribute("xxx-token", token);
		session.setAttribute(TOKEN_SESSION, token);
		return "user";
	}
	
	@RequestMapping(path="balance")
	public String balance(ModelMap model, HttpServletRequest request, HttpSession session) {
		String token = (String) session.getAttribute(TOKEN_SESSION);
		CheckToken checkToken = new CheckToken(jdbcTemplate);
		String user_id = checkToken.check(token);
		if(user_id.isEmpty())
			return "login";
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select * from m_user_security where user_id = '" + user_id + "'";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		if(list.size()<1) return "index";
		model.addAttribute("balance", list.get(0).get("money"));
		
//		sql = "select * from sms_tmpl";
//		list = bussTemplate.queryForList(sql);
//		if(list.size()<1) return "index";
//		model.addAttribute("sms_tmpl", list);
		return "balance";
	}
	
	@RequestMapping(path="getMap")
	public String test(){
		
		System.out.println(mapInstance);
		return "hello";
	}
}

