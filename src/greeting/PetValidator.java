package greeting;

import org.springframework.validation.Errors;  
import org.springframework.validation.ValidationUtils;  
import org.springframework.validation.Validator;  
  
public class PetValidator implements Validator{  
  
    public boolean supports(Class<?> clazz) {  
        return Pet.class.equals(clazz);  
    }  
  
    public void validate(Object obj, Errors e) {  
        ValidationUtils.rejectIfEmpty(e, "name", "name.empty"); 
        ValidationUtils.rejectIfEmpty(e, "owner", "name.empty"); 
    }  
}  