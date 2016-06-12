package greeting;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Date;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jdk.internal.org.objectweb.asm.Handle;

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

	@RequestMapping(path = "/user/{user}", method = RequestMethod.GET)
	public String pringHello(@PathVariable String user, ModelMap model){
		model.addAttribute("Name", user);
		return "hello";
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
	    byte[] requestBody = requestEntity.getBody();
	    // do something with request header and body
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.set("MyResponseHeader", "MyValue");
	    return new ResponseEntity<String>("Hello World", responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(path = "/{month}/{day}")
	public String getMap(@PathVariable Map<String, String> pathVariables) {
		System.out.println(pathVariables);
		return "hello";
	}
}
