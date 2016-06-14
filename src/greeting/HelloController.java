package greeting;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Date;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/hello")
public class HelloController{
//	@Autowired
//    private UserDAOImpl test;
	@Autowired
	private DataSource dataSource;
	
	@RequestMapping(method = RequestMethod.GET)
	public String printHello(String user, ModelMap model) {
		model.addAttribute("message", "Hello Spring MVC Framework!");
		model.addAttribute("Name", user);
		return "hello";
	}

	@RequestMapping(path = "/user/{user}/{sex}", method = RequestMethod.GET)
	public String pringHello(@ModelAttribute User client, BindingResult result, ModelMap model){
		if (result.hasErrors()) {
			return "error";
		}
		model.addAttribute("Name", client.getName());
		model.addAttribute("Sex",client.getSex());
		return "hello";
	}
	
	@RequestMapping(path="/user/{userId}")
	public String getUser(@PathVariable String userId, ModelMap model){
		UserDAOImpl test = new UserDAOImpl(dataSource);
		User user = test.get(userId);
		model.put("id", user.getUserId());
		model.put("Name", user.getName());
		return "user";
	}

	@RequestMapping(path = "/date/{day}", method = RequestMethod.GET)
	public String getForDay(@PathVariable @DateTimeFormat(iso=ISO.DATE) Date day, Model model) {
		return "hello";
	}

	@RequestMapping(path = "/pets/{petId}", params = "name")
	public String findPet(@PathVariable String petId, Model model) {
		System.out.println("find pet");
		return "hello";
	}

	@RequestMapping(path = "/pets")
	public String processSubmit(@RequestParam(name="id", required=true) int id, 
			@RequestParam(name="name", required=false) String name, Model model) {
		System.out.println("id=" + id);
		System.out.println("name="+name);
		return "hello";
	}

	@RequestMapping("/something")
	@ResponseBody
	public String handle(){
		return "something";
	}

	@RequestMapping("/entity")
	public ResponseEntity<String> handle(HttpEntity<byte[]> requestEntity) throws UnsupportedEncodingException {
		String requestHeader = requestEntity.getHeaders().getFirst("MyRequestHeader");
		System.out.println(requestHeader);
		byte[] requestBody = requestEntity.getBody();
		// do something with request header and body
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		return new ResponseEntity<String>("Hello World", responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(path = "/date/{month}/{day}")
	public String getMap(@PathVariable Map<String, String> pathVariables) {
		System.out.println(pathVariables);
		return "hello";
	}

//	@ModelAttribute
//	public User populateModel(@PathVariable String user) {
//		System.out.println("populateModel--"+user);
//		User client = new User(user);
//		return client;
//	}
}
