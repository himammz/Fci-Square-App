package Controller;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.models.Add_Place_Action;
import com.models.Place;
import com.models.UserModel;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

@Path("/")
public class PlaceController {
	
	@POST
	@Path("/NewPlace")
	@Produces(MediaType.TEXT_PLAIN)
	public String follow(@FormParam("id1") String id1,
			@FormParam("Name") String name,@FormParam("lat") String lat, @FormParam("lan")String lng,@FormParam("desc")String desc) {
		Add_Place_Action place= new Place (Integer.parseInt(id1),name,Double.parseDouble(lat),Double.parseDouble(lng),desc);
		boolean faks = place.add(place);
		JSONObject json = new JSONObject();

		if (!faks) {

			json.put("NOT follow", id1 );
			return json.toJSONString();

		}
		json.put("follow", id1 );
		return json.toJSONString();
	}
	
	@POST
	@Path("/AllPlaces")
	@Produces(MediaType.TEXT_PLAIN)
	public String AllPlaces(@FormParam("id")String id){
		
//		Add_Place_Action place = new Place();
//		JSONArray json = ((Place)place).getAllPlaces();
//		return json.toJSONString();
		Add_Place_Action place = new Place();
		JSONArray json = ((Place)place).getAllPlaces();
		((Place)place).getMyPlaces(json,id);
		return json.toJSONString();
	}
	@POST
	@Path("/FollowNewPlace")
	@Produces(MediaType.TEXT_PLAIN)
	public void followNewPlace(@FormParam("UserID")String uid, @FormParam("PlaceID")String pid){
		Add_Place_Action p = new Place();
		((Place)p).followPlace(Integer.parseInt(uid),Integer.parseInt(pid));
		
	}
	
	@POST
	@Path("/unFollowNewPlace")
	@Produces(MediaType.TEXT_PLAIN)
	public void unfollowNewPlace(@FormParam("UserID")String uid, @FormParam("PlaceID")String pid){
		Add_Place_Action p = new Place();
		((Place)p).unfollowPlace(Integer.parseInt(uid),Integer.parseInt(pid));
		
	}
	
	@POST
	@Path("/MyPlaces")
	@Produces(MediaType.TEXT_PLAIN)
	public String myPlaces(@FormParam("id")String Uid){
		Add_Place_Action place = new Place();
		//System.out.println("aaaaaaaaaaaaaaaaaaaa");
		JSONArray json = ((Place)place).MyPlaces(Uid);
		return json.toJSONString();
	}
}
