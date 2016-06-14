package user;

import java.io.Serializable;

public class EmployeeVO implements Serializable
{
    private static final long serialVersionUID = 1L;
 
    private Integer id;
    
    @Override
    public String toString() {
    	return "EmployeeVO [id=" + id + ", firstName=" + firstName
    			+ ", lastName=" + lastName + "]";
    }
    
    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	private String firstName;
    private String lastName;
 
}
