package info.joyindemo.controller;

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

import info.joyindemo.dao.LovDAOImpl;
import info.joyindemo.dao.TechDAOImpl;
import info.joyindemo.entity.LOV;
import info.joyindemo.entity.Technician;

@Controller
public class AdminController {
	@Autowired
	LovDAOImpl lovDAOImpl;
	@Autowired
	TechDAOImpl techDAOImpl;
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
	public ModelAndView addTech(@PathVariable String id, ModelMap modelMap){
		ModelAndView mav = new ModelAndView("addTech");  
		LOV lov = lovDAOImpl.getLOV("title");
		System.out.println(lov.getValue());
		List<String> lov_list = lov.getValue();
		Map<String,String> lov_map = new LinkedHashMap<String,String>();
		for(String lov_value: lov_list){
			lov_map.put(lov_value, lov_value);
		}
		mav.addObject("lov", lov_map);  
		mav.addObject("xxx", new Technician());  
		return mav;
	}
	
	@RequestMapping(value="/admin/{id}/submitTech")  
    private ModelAndView submitTech(@ModelAttribute("xxx") Technician technician) {  
		System.out.println("submit tech");
		System.out.println(technician.getFirstName());
		techDAOImpl.saveOrUpdate(technician);
        return new ModelAndView("index","xxx",new Technician());  
    } 
}