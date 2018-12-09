package beans;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@ManagedBean
@SessionScoped
public class User {
	@NotNull(message = "Please enter your first name. Required.")
    @Size(min=5, max=15)
	String firstName = "";
	
	@NotNull(message = "Please enter your last name. Required.")
	@Size(min=5, max=15)
	String lastName = "";
	
	@PostConstruct
	public void init() {
		// Get the logged in Principle
		Principal principle= FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
			if(principle == null) {
				setFirstName("Unknown");
				setLastName("");
			}
			else {
				setFirstName(principle.getName());
				setLastName("");
			}

	}
	
	public User(){
		firstName = "Roman";
		lastName = "Parkhomenko";
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


}
