package admin;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import common.Technician;
import dao.LOV;
import dao.LovDAOImpl;

@Controller
public class AdminController {
	@Autowired
	LovDAOImpl lovDAOImpl;
	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is welcome page!");
		model.setViewName("index");
		return model;

	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is protected page!");
		model.setViewName("admin");

		return model;

	}

	//Spring Security see this :
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}
	
	@RequestMapping(value="/admin/{id}/addTech")
	public String addTech(@PathVariable String id, ModelMap modelMap){
		LOV lov = lovDAOImpl.getLOV("title");
		System.out.println(lov.getValue());
		List<String> lov_list = lov.getValue();
		Map<String,String> lov_map = new LinkedHashMap<String,String>();
		for(String lov_value: lov_list){
			lov_map.put(lov_value, lov_value);
		}
		modelMap.addAttribute("lov", lov_map);
		return "addTech";
	}
	
	@RequestMapping(value="/submitTech")  
    private ModelAndView submitTech(@ModelAttribute("xxx") Technician technician) {  
		System.out.println("submit tech");
		ModelAndView mav = new ModelAndView("xxx");
        return mav;  
    } 
}