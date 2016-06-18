package Controller;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.models.UserModel;

@Path("/")
public class SignUpController {

	@POST
	@Path("/signup")
	@Produces(MediaType.TEXT_PLAIN)
	public String signUp(@FormParam("name") String name,
			@FormParam("email") String email, @FormParam("pass") String pass,
			@FormParam("userName") String userName,
			@FormParam("gender") String gender, @FormParam("age") String age) {
		UserModel user = UserModel.addNewUser(name, email, pass, userName, gender, age);
		JSONObject json = new JSONObject();
		json.put("id", user.getId());
		json.put("name", user.getName());
		json.put("userName", user.getUserName());	
		json.put("email", user.getEmail());
		json.put("pass", user.getPass());
		json.put("gender", user.getGender());
		json.put("age", String.valueOf(user.getAge()));
		System.out.println(json);
		return json.toJSONString();
	}
}
