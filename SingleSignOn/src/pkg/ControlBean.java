package pkg;

import java.io.Serializable;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;


@Named("control") // or @ManagedBean
@SessionScoped
public class ControlBean implements Serializable {
	//hardcoded for the demo
	private static final String USERNAME = "Dave";
	private static final String PASSWORD = "111";
	private String username;
	private String password;
	private String requestedUrl;
	private boolean isLoggedIn = false;
	
	public String checkLogin() {
		FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params =
                fc.getExternalContext().getRequestParameterMap();
        requestedUrl = params.get("url");
		if(isLoggedIn) {
			return requestedUrl;
		} else {
			return "login";
	    }
	}
	
	public String doLogin() {
		FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params =
                fc.getExternalContext().getRequestParameterMap();
        if(requestedUrl == null) {
        	requestedUrl = (params.get("url"));
        }
		isLoggedIn =username.equals(USERNAME) && password.equals(PASSWORD);
		if(isLoggedIn) {
			return requestedUrl;
		} else {
			return "logInFail";
		}

	}
	
	public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        isLoggedIn = false;
        return "loggedOut";
    }

	
	public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    } 
    
    public boolean getIsLoggedIn() {
    	return isLoggedIn;
    }
    public void setIsLoggedIn(boolean b) {
    	isLoggedIn = b;
    }

}
