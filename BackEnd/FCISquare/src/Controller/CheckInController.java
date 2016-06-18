package Controller;

import java.sql.SQLException;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.models.Add_Place_Action;
import com.models.CheckIn;
import com.models.Place;
@Path("/")
public class CheckInController {
	@POST
	@Path("/AddCheck")
	@Produces(MediaType.TEXT_PLAIN)
	public String follow(@FormParam("id1") String id1 ,
			@FormParam("Name") String name ,@FormParam("lat") String lat , @FormParam("lan")String lng,@FormParam("rate")String rate) {
		System.out.println(rate+"----------------------------");
		Add_Place_Action Check= new CheckIn (Integer.parseInt(id1),name,Double.parseDouble(lat),Double.parseDouble(lng),Integer.parseInt(rate));
		boolean faks = Check.add(Check);
		JSONObject json = new JSONObject();

		json.put("checkIn added", id1 );
		return json.toJSONString();
	}
	@POST
	@Path("/ShowCheckin")
	@Produces(MediaType.TEXT_PLAIN)
	public String show(@FormParam("checkinid")String id,@FormParam("uid")String uid){
		System.out.println(id + "you are in");
		Add_Place_Action Check = new CheckIn();
		JSONObject json = ((CheckIn) Check).show(id,uid);
		System.out.println(json);
		return json.toJSONString();
	}
	
}
