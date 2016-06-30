package info.joyindemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import info.joyindemo.dao.TechDAOImpl;
import info.joyindemo.entity.Technician;



@Controller
public class CommonController {
	
	@Autowired
	private TechDAOImpl techDAOImpl;
	
	@RequestMapping(path="allTech")
	public String login(ModelMap model) {
		List<Technician> list = techDAOImpl.get();
		if(list.size()<1) return "index";
		model.addAttribute("list", list);
		return "allTech";
	}
	
	@RequestMapping(path="getTech/{UUID}")
	public String getTech(@PathVariable String UUID, ModelMap modelMap){
		Technician technician = techDAOImpl.get(UUID);
		modelMap.put("tech", technician);
		return "tech";
	}
	
	@RequestMapping(path="cacheTest/{UUID}")
	public String getCatch(@PathVariable String UUID){
		Technician tech = techDAOImpl.test(UUID);
//		System.out.println(tString+","+tString2);
		System.out.println(tech.getFirstName());
		return "tech";
	}
}
