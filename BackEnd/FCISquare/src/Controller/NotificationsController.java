package Controller;

import java.sql.SQLException;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.models.Actions;
import com.models.CheckIn;
import com.models.Comment;
import com.models.Follow;
import com.models.Like;
import com.models.Notification;

@Path("/")

public class NotificationsController {

	@POST
	@Path("/activeLog")
	@Produces(MediaType.TEXT_PLAIN)
	public String getNotificationList(@FormParam("myId") String myId) throws SQLException {
		int id = Integer.parseInt(myId);
		Notification list = new Notification();
		list.setIdUser(id);
		JSONArray json = list.getAllNotification();
		return json.toJSONString();
	}

	@POST
	@Path("/undo")
	@Produces(MediaType.TEXT_PLAIN)
	public String undo(@FormParam("IDType") String IDType, @FormParam("Type") String Type) throws SQLException {
		if (IDType.equals(""))
		{
			return"";
		}
		Notification notify = new Notification();
		boolean check1 = notify.removeNotification(Type, Integer.parseInt(IDType));
		boolean check2 = false;
		JSONObject json = new JSONObject();
		Actions ac;
		if (Type.equals("Like") && check1) {
			ac = new Like(Integer.parseInt(IDType));
			check2 = ac.remove();

		} else if (Type.equals("Comment") && check1) {
			ac = new Comment(Integer.parseInt(IDType));
			check2 = ac.remove();
		} else if (Type.equals("Follow") && check1) {
			ac = new Follow(Integer.parseInt(IDType));
			check2 = ac.remove();
		} else if (Type.equals("Check-in") && check1) {
			ac = new CheckIn(Integer.parseInt(IDType));
			check2 = ac.remove();
		}
		if (check1 && check2) {
			json.put(" Sucessful Undo", "You Un" + Type);
		} else {
			json.put("Failed Undo", "You Un" + Type);
		}
		return json.toJSONString();
	}

}
