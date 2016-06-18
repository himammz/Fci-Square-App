package Controller;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.models.UserModel;

@Path("/")
public class FollowController {

	@POST
	@Path("/follow")
	@Produces(MediaType.TEXT_PLAIN)
	public String follow(@FormParam("id1") String id1,
			@FormParam("userName") String userName) {
		System.out.println(userName+" "+id1);
		boolean check = UserModel.follow(Integer.parseInt(id1),
			userName);
		JSONObject json = new JSONObject();

		if (!check) {
			return "null";

		}
		json.put("follow", "you follow "  + userName);
		return json.toJSONString();
	}
	
	@POST
	@Path("/unfollow")
	@Produces(MediaType.TEXT_PLAIN)
	public String unfollow(@FormParam("id1") String id1,
			@FormParam("userName") String userName) {
		boolean check = UserModel.unfollow(Integer.parseInt(id1),
				userName);
		JSONObject json = new JSONObject();
		
		if(!check)
			return "null";
		
		else 
			json.put("Not follow", id1 + ", " + userName);
		return json.toJSONString();
	}
	
	@POST
	@Path("/getfollow")
	@Produces(MediaType.TEXT_PLAIN)
	public String getfollow(@FormParam("id") String id) {
		System.out.println(id);
		ArrayList<String> name = UserModel.getfollow(Integer.parseInt(id));
		JSONObject json = new JSONObject();
		json.put("followList", name);
		return json.toJSONString();
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String getJson() {
		return "Hello after editing";
		// Connection URL:
		// mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/
	}
}
