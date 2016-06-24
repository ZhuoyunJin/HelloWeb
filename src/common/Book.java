package common;

import org.springframework.cache.annotation.Cacheable;

public class Book {
	@Cacheable("test")
	public String test(){
		System.out.println("inside");
		return "xyz";
	}
}
