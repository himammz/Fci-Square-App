package Controller;

import java.sql.SQLException;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;

import com.models.CheckIn;
import com.models.Home;
import com.models.ShowHome;
import com.models.SortByCount;
import com.models.SortByPlace;
import com.models.SortByRate;
import com.models.SortByTime;

@Path("/")
public class HomeController {
	@POST
	@Path("/showCheckin")
	@Produces(MediaType.TEXT_PLAIN)
	public String show(@FormParam("myId") String myId) throws SQLException {
		int id = Integer.parseInt(myId);
		ShowHome h = new Home();
		JSONArray json = h.show(id);
		return json.toJSONString();
	}
	
	@POST
	@Path("/showbyRate")
	@Produces(MediaType.TEXT_PLAIN)
	public String showRate(@FormParam("myId") String myId) throws SQLException {
		int id = Integer.parseInt(myId);
		ShowHome h = new SortByRate();
		JSONArray json = h.show(id);
		return json.toJSONString();
	}
	@POST
	@Path("/showbyTime")
	@Produces(MediaType.TEXT_PLAIN)
	public String showTime(@FormParam("myId") String myId) throws SQLException {
		int id = Integer.parseInt(myId);
		ShowHome h = new SortByTime();
		JSONArray json = h.show(id);
		return json.toJSONString();
	}
	@POST
	@Path("/showbycount")
	@Produces(MediaType.TEXT_PLAIN)
	public String showCount(@FormParam("myId") String myId) throws SQLException {
		int id = Integer.parseInt(myId);
		ShowHome h = new SortByCount();
		JSONArray json = h.show(id);
		return json.toJSONString();
	}
	
	@POST
	@Path("/showbyplace")
	@Produces(MediaType.TEXT_PLAIN)
	public String showPlace(@FormParam("myId") String myId) throws SQLException {
		int id = Integer.parseInt(myId);
		ShowHome h = new SortByPlace();
		JSONArray json = h.show(id);
		return json.toJSONString();
	}
	
	
}
