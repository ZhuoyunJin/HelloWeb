package Employment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/employee")
public class EmploymentController {
	@RequestMapping(method = RequestMethod.GET)
	   public String printEmployers(ModelMap model) {
	      model.addAttribute("message", "I am an employee.");
	      return "employee";
	   }
}
