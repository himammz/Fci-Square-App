package com.application;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Path("/")
public class Place {
	@Context
	HttpServletRequest request;

	@GET
	@Path("/addPlace")
	@Produces(MediaType.TEXT_HTML)
	public Response AddPlace() {
		return Response.ok(new Viewable("/AddPlace.jsp")).build();
	}

	@GET
	@Path("/MySavedPlaces")
	@Produces(MediaType.TEXT_HTML)
	public Response showMyPlaces() {
		String serviceUrl = Connection.getURL() + "MyPlaces";

		HttpSession session = request.getSession();
		Long id1 = (Long) session.getAttribute("id");
		String urlParameters = "id=" + id1;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");

		JSONArray obj = new JSONArray();
		JSONParser parser = new JSONParser();
		try {
			if(retJson==null){
				return Response.ok(new Viewable("/Error.jsp")).build();
			}
			obj = (JSONArray) parser.parse(retJson);
			Map<String, JSONObject> map = new HashMap<String, JSONObject>();
			for (int i = 0; i < obj.size(); i++) {
				map.put(String.valueOf(((JSONObject) obj.get(i)).get("id")),
						(JSONObject) obj.get(i));
			}
			return Response.ok(new Viewable("/MyPlaces.jsp", map)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@POST
	@Path("/followPlace")
	@Produces(MediaType.TEXT_HTML)
	public Response followPlace(@FormParam("id") String placeid) {

		String serviceUrl = Connection.getURL() + "FollowNewPlace";

		HttpSession session = request.getSession();
		Long id1 = (Long) session.getAttribute("id");
		String urlParameters = "UserID=" + id1 + "&PlaceID=" + placeid;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");

		
		//new
		JSONArray obj = new JSONArray();
		JSONParser parser = new JSONParser();
		serviceUrl = Connection.getURL() + "AllPlaces";
		session = request.getSession();
		Long id = (Long) session.getAttribute("id");
		urlParameters = "id=" + id;
		retJson = Connection.connect(serviceUrl, urlParameters, "GET",
				"application/x-www-form-urlencoded;charset=UTF-8");
		
		try {
			if(retJson==null){
				return Response.ok(new Viewable("/Error.jsp")).build();
			}
			obj = (JSONArray) parser.parse(retJson);
			Map<String, JSONObject> map = new HashMap<String, JSONObject>();
			System.out.println(obj);
			for (int i = 0; i < obj.size(); i++) {
				map.put(String.valueOf(((JSONObject) obj.get(i)).get("id")),
						(JSONObject) obj.get(i));
			}

			return Response.ok(new Viewable("/SavePlace.jsp", map)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@POST
	@Path("/unfollowPlace")
	@Produces(MediaType.TEXT_HTML)
	public Response unfollowPlace(@FormParam("id") String placeid) {
		String serviceUrl = Connection.getURL() + "unFollowNewPlace";

		HttpSession session = request.getSession();
		Long id1 = (Long) session.getAttribute("id");
		String urlParameters = "UserID=" + id1 + "&PlaceID=" + placeid;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");

		JSONArray obj = new JSONArray();
		JSONParser parser = new JSONParser();
		serviceUrl = Connection.getURL() + "AllPlaces";
		session = request.getSession();
		Long id = (Long) session.getAttribute("id");
		urlParameters = "id=" + id;
		retJson = Connection.connect(serviceUrl, urlParameters, "GET",
				"application/x-www-form-urlencoded;charset=UTF-8");
		
		try {
			if(retJson==null){
				return Response.ok(new Viewable("/Error.jsp")).build();
			}
			obj = (JSONArray) parser.parse(retJson);
			Map<String, JSONObject> map = new HashMap<String, JSONObject>();
			System.out.println(obj);
			for (int i = 0; i < obj.size(); i++) {
				map.put(String.valueOf(((JSONObject) obj.get(i)).get("id")),
						(JSONObject) obj.get(i));
			}

			return Response.ok(new Viewable("/SavePlace.jsp", map)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	@POST
	@Path("/newPlace")
	@Produces(MediaType.TEXT_HTML)
	public Response submitNewPlace(@FormParam("Name") String name,
			@FormParam("lat") String lat, @FormParam("long") String lng,
			@FormParam("desc") String desc) {
		String serviceUrl = Connection.getURL() + "NewPlace";
		HttpSession session = request.getSession();
		Long id1 = (Long) session.getAttribute("id");
		String urlParameters = "id1=" + id1 + "&Name=" + name + "&lat=" + lat
				+ "&lan=" + lng + "&desc=" + desc;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");

		return Response.ok(new Viewable("/AddPlace.jsp")).build();
	}

	@GET
	@Path("/savePlace")
	@Produces(MediaType.TEXT_HTML)
	public Response SavePlace() {
		String serviceUrl = Connection.getURL() + "AllPlaces";
		HttpSession session = request.getSession();
		Long id = (Long) session.getAttribute("id");
		String urlParameters = "id=" + id;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONArray obj = new JSONArray();
		JSONParser parser = new JSONParser();
		try {
			if(retJson==null){
				return Response.ok(new Viewable("/Error.jsp")).build();
			}
			obj = (JSONArray) parser.parse(retJson);
			Map<String, JSONObject> map = new HashMap<String, JSONObject>();
			System.out.println(obj);
			for (int i = 0; i < obj.size(); i++) {
				map.put(String.valueOf(((JSONObject) obj.get(i)).get("id")),
						(JSONObject) obj.get(i));
			}

			return Response.ok(new Viewable("/SavePlace.jsp", map)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
