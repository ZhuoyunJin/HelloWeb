package common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CommonController {
	@Autowired
	private JdbcTemplate awsTemplate;
	
	@RequestMapping(path="allTech")
	public String login(ModelMap model) {
		TechDAOImpl techDAOImpl = new TechDAOImpl(awsTemplate);
		List<Technician> list = techDAOImpl.get();
		if(list.size()<1) return "index";
		model.addAttribute("list", list);
		return "allTech";
	}
	
	@RequestMapping(path="getTech/{UUID}")
	public String getTech(@PathVariable String UUID){
		UUID = UUID.trim();
		System.out.println(UUID);
		TechDAOImpl techDAOImpl = new TechDAOImpl(awsTemplate);
		Technician technician = techDAOImpl.get(UUID);
		System.out.println(technician.getFirstName());
		return "";
	}
}
