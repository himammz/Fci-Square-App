package com.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
public class Home {
	@Context
	HttpServletRequest request;

	@GET
	@Path("/homepage")
	@Produces(MediaType.TEXT_HTML)
	public Response homepage() {
		String serviceUrl = Connection.getURL() + "login";
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String pass = (String) session.getAttribute("pass");
		String urlParameters = "email=" + email + "&pass=" + pass;
		String retJson = Connection.connect(serviceUrl, urlParameters, "GET",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		System.out.println(retJson);
		try {
			if(retJson==null){
				return Response.ok(new Viewable("/Error.jsp")).build();
			}
			obj = (JSONObject) parser.parse(retJson);
			session.setAttribute("email", obj.get("email"));
			session.setAttribute("name", obj.get("name"));
			session.setAttribute("userName", obj.get("userName"));	
			session.setAttribute("id", obj.get("id"));
			session.setAttribute("lat", obj.get("lat"));
			session.setAttribute("long", obj.get("long"));
			session.setAttribute("pass", obj.get("pass"));
			session.setAttribute("gender", obj.get("gender"));
			session.setAttribute("age", obj.get("age"));
			
			Map<String, String> map = new HashMap<String, String>();

			map.put("name", (String) obj.get("name"));
			map.put("email", (String) obj.get("email"));
			map.put("userName", (String) obj.get("userName"));
			map.put("age", (String) obj.get("age"));

			return Response.ok(new Viewable("/home.jsp", map)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Path("/showHome")
	@Produces(MediaType.TEXT_HTML)
	public Response showHomePage() {
		String serviceUrl = Connection.getURL() + "showCheckin";

		HttpSession session = request.getSession();
		Long igd = (Long) session.getAttribute("id");
		String urlParameters = "myId=" + igd;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONArray obj = new JSONArray();
		JSONParser parser = new JSONParser();

		try {
			if(retJson==null){
				return Response.ok(new Viewable("/Error.jsp")).build();
			}
			
			obj = (JSONArray) parser.parse(retJson);

			ArrayList<JSONObject> list = new ArrayList<JSONObject>();

			for (int i = 0; i < obj.size(); ++i) {
				list.add((JSONObject) obj.get(i));
			}
			return Response.ok(new Viewable("/homePage.jsp", list)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@GET
	@Path("/showHomeByRate")
	@Produces(MediaType.TEXT_HTML)
	public Response showHomePageByRate() {
		String serviceUrl = Connection.getURL() + "showbyRate";

		HttpSession session = request.getSession();
		Long igd = (Long) session.getAttribute("id");
		String urlParameters = "myId=" + igd;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONArray obj = new JSONArray();
		JSONParser parser = new JSONParser();

		try {
			if(retJson==null){
				return Response.ok(new Viewable("/Error.jsp")).build();
			}
			
			obj = (JSONArray) parser.parse(retJson);

			ArrayList<JSONObject> list = new ArrayList<JSONObject>();

			for (int i = 0; i < obj.size(); ++i) {
				list.add((JSONObject) obj.get(i));
			}
			return Response.ok(new Viewable("/homePage.jsp", list)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@GET
	@Path("/showHomeByTime")
	@Produces(MediaType.TEXT_HTML)
	public Response showHomePageByTime() {

		String serviceUrl = Connection.getURL() + "showbyTime";

		HttpSession session = request.getSession();
		Long igd = (Long) session.getAttribute("id");
		String urlParameters = "myId=" + igd;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONArray obj = new JSONArray();
		JSONParser parser = new JSONParser();

		try {
			if(retJson==null){
				return Response.ok(new Viewable("/Error.jsp")).build();
			}
			
			obj = (JSONArray) parser.parse(retJson);

			ArrayList<JSONObject> list = new ArrayList<JSONObject>();

			for (int i = 0; i < obj.size(); ++i) {
				list.add((JSONObject) obj.get(i));
			}

			return Response.ok(new Viewable("/homePage.jsp", list)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@GET
	@Path("/showHomeByCount")
	@Produces(MediaType.TEXT_HTML)
	public Response showHomePageByCount() {
		String serviceUrl = Connection.getURL() + "showbycount";

		HttpSession session = request.getSession();
		Long igd = (Long) session.getAttribute("id");
		String urlParameters = "myId=" + igd;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONArray obj = new JSONArray();
		JSONParser parser = new JSONParser();

		try {
			if(retJson==null){
				return Response.ok(new Viewable("/Error.jsp")).build();
			}
			
			obj = (JSONArray) parser.parse(retJson);

			ArrayList<JSONObject> list = new ArrayList<JSONObject>();

			for (int i = 0; i < obj.size(); ++i) {
				list.add((JSONObject) obj.get(i));
			}

			return Response.ok(new Viewable("/homePage.jsp", list)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@GET
	@Path("/showHomeByPlace")
	@Produces(MediaType.TEXT_HTML)
	public Response showHomePageByPlacet() {
		String serviceUrl = Connection.getURL() + "showbyplace";

		HttpSession session = request.getSession();
		Long igd = (Long) session.getAttribute("id");
		String urlParameters = "myId=" + igd;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONArray obj = new JSONArray();
		JSONParser parser = new JSONParser();

		try {
			if(retJson==null){
				return Response.ok(new Viewable("/Error.jsp")).build();
			}
			obj = (JSONArray) parser.parse(retJson);

			ArrayList<JSONObject> list = new ArrayList<JSONObject>();

			for (int i = 0; i < obj.size(); ++i) {
				list.add((JSONObject) obj.get(i));
			}

			return Response.ok(new Viewable("/homePage.jsp", list)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
