package com.application;

import java.util.ArrayList;
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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Path("/")
public class Follow {
	@Context
	HttpServletRequest request;

	@GET
	@Path("/FollowPerson")
	@Produces(MediaType.TEXT_HTML)
	public Response FollowPerson() {
		return Response.ok(new Viewable("/FollowPerson.jsp")).build();
	}

	@GET
	@Path("/UnFollowPerson")
	@Produces(MediaType.TEXT_HTML)
	public Response UnFollowPerson() {
		return Response.ok(new Viewable("/UnFollow.jsp")).build();
	}

	@POST
	@Path("/submitUnFollow")
	@Produces(MediaType.TEXT_HTML)
	public Response submitUnFollowPerson(@FormParam("userName") String userName) {
		String serviceUrl = Connection.getURL() + "unfollow";
		HttpSession session = request.getSession();
		Long id1 = (Long) session.getAttribute("id");
		String urlParameters = "id1=" + id1 + "&userName=" + userName;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");

		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		try {
			if(retJson.equals("null")){
				return Response.ok(new Viewable("/Error.jsp")).build();
			}
			obj = (JSONObject) parser.parse(retJson);
			session.setAttribute("Follower", obj.get("id1"));
			session.setAttribute("Following", obj.get("id2"));
			Map<String, String> map = new HashMap<String, String>();

			map.put("name", (String) session.getAttribute("name"));
			map.put("email", (String) session.getAttribute("email"));
			return Response.ok(new Viewable("/home.jsp", map)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Path("/followingList")
	@Produces(MediaType.TEXT_HTML)
	public Response showFollowers() {
		String serviceUrl = Connection.getURL() + "getfollow";

		HttpSession session = request.getSession();
		Long id = (Long) session.getAttribute("id");
		String urlParameters = "id=" + id;

		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		try {
			if(retJson.equals("null")){
				return Response.ok(new Viewable("/Error.jsp")).build();
			}
			obj = (JSONObject) parser.parse(retJson);

			ArrayList<String> names = (ArrayList<String>) obj.get("followList");

			return Response.ok(new Viewable("/followers.jsp", names)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@POST
	@Path("/submitFollow")
	@Produces(MediaType.TEXT_HTML)
	public Response submitFollowPerson(@FormParam("userName") String userName) {
		String serviceUrl = Connection.getURL() + "follow";
		HttpSession session = request.getSession();
		Long id1 = (Long) session.getAttribute("id");
		String urlParameters = "id1=" + id1 + "&userName=" + userName;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		System.out.println(retJson+"ddd");
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		try {
			if(retJson.equals("null")){
				return Response.ok(new Viewable("/Error.jsp")).build();
			}
			obj = (JSONObject) parser.parse(retJson);
			session.setAttribute("Follower", obj.get("id1"));
			session.setAttribute("Following", obj.get("id2"));
			Map<String, String> map = new HashMap<String, String>();

			map.put("name", (String) session.getAttribute("name"));
			map.put("email", (String) session.getAttribute("email"));
			return Response.ok(new Viewable("/home.jsp", map)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
