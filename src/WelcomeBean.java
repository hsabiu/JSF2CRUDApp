import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
@SuppressWarnings("deprecation")
public class WelcomeBean {

	private String message = "Learning JSF is fun!";
	
	public WelcomeBean() {
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
