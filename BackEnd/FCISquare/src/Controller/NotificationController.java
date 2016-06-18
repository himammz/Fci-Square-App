
package Controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.models.ListNotification;

@Path("/")
public class NotificationController {

	@Context
	HttpServletRequest request;

	@POST
	@Path("/getNotification")
	@Produces(MediaType.TEXT_PLAIN)
	public String getNotification(@FormParam("id") String id) {
		System.out.println("IDDDDDDDDDDDDDDDDDDD" + id);
		ListNotification list = new ListNotification(Integer.parseInt(id));
		JSONArray notification = list.getNotification(Integer.parseInt(id));
		JSONObject json = new JSONObject();
		json.put("notificationList", notification);
		return json.toJSONString();
	}
}
