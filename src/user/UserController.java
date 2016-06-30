package user;

import javax.servlet.http.HttpSession;

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

	@Autowired
	private JdbcTemplate awsTemplate;
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
		pwd = TokenUtil.MD5(pwd);
		UserDAOImpl test = new UserDAOImpl(awsTemplate);
		User user = test.get(name,pwd);
		if(user==null){
			model.put("ErrorMessage", "User not found.");
			return "error";
		}
		String token = TokenUtil.getToken(user.getUserId(),awsTemplate);
		System.out.println("token: "+ token);
		model.put("id", user.getUserId());
		model.put("Name", user.getName());
		session.setAttribute(TOKEN_SESSION, token);
		return "user";
	}
	
	@RequestMapping(path="getMap")
	public String test(){
		
		System.out.println(mapInstance);
		return "hello";
	}
}

