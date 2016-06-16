package user;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import Util.TokenUtil;
import greeting.User;
import greeting.UserDAOImpl;

@Controller
public class UserController {
	private static final String TOKEN_SESSION = "xxx-token";
	@Autowired
	private DataSource dataSource;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(path="login")
	public String login(ModelMap model, HttpServletRequest request, HttpSession session) {
		String name=request.getParameter("username");
		String pwd=request.getParameter("password");
		UserDAOImpl test = new UserDAOImpl(dataSource);
		User user = test.get(name,pwd);
		if(user==null){
			model.put("ErrorMessage", "User not found.");
			return "error";
		}
		String token = TokenUtil.getToken(user.getUserId(),dataSource);
		model.put("id", user.getUserId());
		model.put("Name", user.getName());
//		model.addAttribute("xxx-token", token);
		session.setAttribute(TOKEN_SESSION, token);
		return "user";
	}
	
	@RequestMapping(path="balance")
	public String balance(ModelMap model, HttpServletRequest request, HttpSession session) {
		String token = (String) session.getAttribute(TOKEN_SESSION);
		boolean isLogin = CheckToken.check(token);
		return "";
	}
}

