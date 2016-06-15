package user;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import greeting.User;
import greeting.UserDAOImpl;

@Controller
public class UserController {
	
	@Autowired
	private DataSource dataSource;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(path="login")
	public String login(ModelMap model, HttpServletRequest request) {
		String name=request.getParameter("username");
		String pwd=request.getParameter("password");
		UserDAOImpl test = new UserDAOImpl(dataSource);
		User user = test.get(name,pwd);
		if(user==null){
			model.put("ErrorMessage", "User not found.");
			return "error";
		}
		model.put("id", user.getUserId());
		model.put("Name", user.getName());
		return "user";
	}
}

