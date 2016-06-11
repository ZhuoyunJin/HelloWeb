package greeting;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/hello")
public class HelloController{
 
   @RequestMapping(method = RequestMethod.GET)
   public String printHello(String user, ModelMap model) {
      model.addAttribute("message", "Hello Spring MVC Framework!");
      model.addAttribute("Name", user);
      return "hello";
   }
   
   @RequestMapping(path = "/{user}", method = RequestMethod.GET)
   public String pringHello(@PathVariable String user, ModelMap model){
	   model.addAttribute("Name", user);
	   return "hello";
   }
   
   @RequestMapping(path = "/date/{day}", method = RequestMethod.GET)
   public String getForDay(@PathVariable @DateTimeFormat(iso=ISO.DATE) Date day, Model model) {
	   return "hello";
   }

}
