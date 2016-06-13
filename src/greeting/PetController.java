package greeting;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/pet")
@SessionAttributes("test")
public class PetController {
	
	@RequestMapping(path = "/{name}/{owner}", method = RequestMethod.GET)
	public String pringHello(@ModelAttribute Pet pet, BindingResult result, Model model){
		if (result.hasErrors()) {
			return "error";
		}
		System.out.println(pet.name);
		System.out.println(pet.owner);
		model.addAttribute("test","123");
		return "hello";
	}
	
	@RequestMapping(path="/{name}")
	public String buildPet(@ModelAttribute Pet pet, BindingResult result){
		new PetValidator().validate(pet, result);
		if(result.hasErrors()){
			List<ObjectError> listOfErrors = result.getAllErrors();
			System.out.println(listOfErrors);
			return "error";
		}
		return "hello";
	}

	@RequestMapping(path="/")
	public String handle(@ModelAttribute("test") String test) {
		System.out.println(test);
		return "hello";
	}
	
	@RequestMapping(path="/cookie")
	public String cookie(@CookieValue("JSESSIONID") String cookie){
		System.out.println(cookie);
		return "hello";
	}
}
