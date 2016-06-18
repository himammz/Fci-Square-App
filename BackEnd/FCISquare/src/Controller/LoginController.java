package Controller;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.models.UserModel;

@Path("/")
public class LoginController {
	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	public String login(@FormParam("email") String email,
			@FormParam("pass") String pass) {
		UserModel user = UserModel.login(email, pass);
		JSONObject json = new JSONObject();
		json.put("id", user.getId());
		json.put("name", user.getName());
		json.put("userName", user.getUserName());
		json.put("email", user.getEmail());
		json.put("pass", user.getPass());
		json.put("gender", user.getGender());
		json.put("age", String.valueOf(user.getAge()));
		//json.put("lat", user.getLat());
		//json.put("long", user.getLon());
		return json.toJSONString();
	}
}
