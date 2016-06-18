package com.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;

import com.ConnectionDB.DBConnection;
import com.models.UserModel;

@Path("/")
public class Services {

	/*
	 * @GET
	 * 
	 * @Path("/signup")
	 * 
	 * @Produces(MediaType.TEXT_HTML) public Response signUp(){ return
	 * Response.ok(new Viewable("/Signup.jsp")).build(); }
	 */

//	@POST
//	@Path("/signup")
//	@Produces(MediaType.TEXT_PLAIN)
//	public String signUp(@FormParam("name") String name,
//			@FormParam("email") String email, @FormParam("pass") String pass) {
//		UserModel user = UserModel.addNewUser(name, email, pass);
//		JSONObject json = new JSONObject();
//		json.put("id", user.getId());
//		json.put("name", user.getName());
//		json.put("email", user.getEmail());
//		json.put("pass", user.getPass());
//		return json.toJSONString();
//	}
//
//	@POST
//	@Path("/follow")
//	@Produces(MediaType.TEXT_PLAIN)
//	public String follow(@FormParam("id1") String id1,
//			@FormParam("id2") String id2) {
//		System.out.println(id1 + " " + id2);
//		boolean faks = UserModel.follow(Integer.parseInt(id1),
//				Integer.parseInt(id2));
//		JSONObject json = new JSONObject();
//
//		if (!faks) {
//
//			json.put("NOT follow", id1 + ", " + id2);
//			return json.toJSONString();
//
//		}
//		json.put("follow", id1 + ", " + id2);
//		return json.toJSONString();
//	}

//	@POST
//	@Path("/unfollow")
//	@Produces(MediaType.TEXT_PLAIN)
//	public String unfollow(@FormParam("id1") String id1,
//			@FormParam("id2") String id2) {
//		System.out.println(id1 + " " + id2);
//		boolean faks = UserModel.unfollow(Integer.parseInt(id1),
//				Integer.parseInt(id2));
//		JSONObject json = new JSONObject();
//		
//		if(!faks)
//			json.put("Not found",id1+","+id2);
//		
//		else 
//			json.put("Not follow", id1 + ", " + id2);
//		return json.toJSONString();
//	}

//	@POST
//	@Path("/getfollow")
//	@Produces(MediaType.TEXT_PLAIN)
//	public String getfollow(@FormParam("id") String id) {
//		System.out.println(id);
//		ArrayList<String> name = UserModel.getfollow(Integer.parseInt(id));
//		JSONObject json = new JSONObject();
//		json.put("followList", name);
//		return json.toJSONString();
//	}

	@POST
	@Path("/getLocation")
	@Produces(MediaType.TEXT_PLAIN)
	public String getLocation(@FormParam("id") String id) {
		// System.out.println(lan+" "+lot);
		JSONObject json = new JSONObject();
		UserModel u = new UserModel();
		String res = u.getLocation(Integer.parseInt(id));
		json.put("Location is ", res);
		System.out.println(json);
		return json.toJSONString();
	}

//	@POST
//	@Path("/login")
//	@Produces(MediaType.TEXT_PLAIN)
//	public String login(@FormParam("email") String email,
//			@FormParam("pass") String pass) {
//		UserModel user = UserModel.login(email, pass);
//		JSONObject json = new JSONObject();
//		json.put("id", user.getId());
//		json.put("name", user.getName());
//		json.put("email", user.getEmail());
//		json.put("pass", user.getPass());
//		//json.put("lat", user.getLat());
//		//json.put("long", user.getLon());
//		return json.toJSONString();
//	}

	@POST
	@Path("/updatePosition")
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePosition(@FormParam("id") String id,
			@FormParam("lat") String lat, @FormParam("long") String lon) {
		Boolean status = UserModel.updateUserPosition(Integer.parseInt(id),
				Double.parseDouble(lat), Double.parseDouble(lon));
		JSONObject json = new JSONObject();
		json.put("status", status ? 1 : 0);
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
