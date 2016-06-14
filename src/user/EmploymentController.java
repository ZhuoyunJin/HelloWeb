package user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class EmploymentController {
	
	@RequestMapping(path = "/{userId}", method = RequestMethod.GET)
	   public String printUsers(@PathVariable String userId, ModelMap model) {
	      model.addAttribute("name", "");
	      return "employee";
	   }
}
