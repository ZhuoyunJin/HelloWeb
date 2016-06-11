package Employment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/employment")
public class EmploymentController {
	
	@RequestMapping(path = "/{employee}", method = RequestMethod.GET)
	   public String printEmployers(@PathVariable String employee, ModelMap model) {
	      model.addAttribute("name", employee);
	      return "employee";
	   }
}
