package info.joyindemo.controller;

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
import info.joyindemo.dao.UserDAOImpl;
import info.joyindemo.entity.User;
import Util.Constants;
@Controller
public class UserController {
//	private static final String TOKEN_SESSION = "xxx-token";

	@Autowired
	private JdbcTemplate awsTemplate;
	@Resource
	private Map<String, String> mapInstance;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(path="user")
	public String login(ModelMap model, HttpServletRequest request, HttpSession session) {
		String name=request.getParameter("username");
		String pwd=request.getParameter("password");
		if(name!=null && pwd !=null){
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
			session.setAttribute(Constants.TOKEN_SESSION, token);
			return "user";
		}
		String token_session = session.getAttribute(Constants.TOKEN_SESSION).toString();
		if(token_session == null){
			return "index";
		}
		System.out.println(token_session);
		UserDAOImpl test = new UserDAOImpl(awsTemplate);
		User user = test.get(token_session);
		if(user==null){
			model.put("ErrorMessage", "User not found.");
			return "error";
		}
		String token = TokenUtil.getToken(user.getUserId(),awsTemplate);
		System.out.println("token: "+ token);
		model.put("id", user.getUserId());
		model.put("Name", user.getName());
		session.setAttribute(Constants.TOKEN_SESSION, token);
		return "user";
	}
}

