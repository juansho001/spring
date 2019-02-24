package co.com.restapp.springboot.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import co.com.restapp.springboot.vo.VOUser;

@RestController
@RequestMapping(path = "/servicesREST/JR")
public class RestUser {

	@RequestMapping(method = RequestMethod.POST, 
					path = "/validateUser", 
					consumes = "application/json", 
					produces = "application/json")
	public @ResponseBody VOUser validateUser(@RequestBody VOUser user) throws Exception {
		VOUser response = new VOUser();
		response.setFind(false);
		System.out.println("User: " + user.getUser());
		System.out.println("Password: " + user.getPassword());
		try {
			if (user.getUser().equals("Java") && user.getPassword().equals("Revolutions")) {
				response.setUser(user.getUser());
				response.setFind(true);
			}
			return response;
		} catch (Exception ex) {
			return response;
		}
	}
}
