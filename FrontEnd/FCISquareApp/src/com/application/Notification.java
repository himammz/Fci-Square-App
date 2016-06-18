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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Path("/")
public class Notification {
	@Context
	HttpServletRequest request;

	@GET
	@Path("/getNotification")
	@Produces(MediaType.TEXT_HTML)
	public Response getNotification() {

		String serviceUrl = Connection.getURL() + "getNotification";

		HttpSession session = request.getSession();
		Long id = (Long) session.getAttribute("id");
		String urlParameters = "id=" + id;

		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		try {
			if(retJson==null){
				return Response.ok(new Viewable("/Error.jsp")).build();
			}
			obj = (JSONObject) parser.parse(retJson);
			JSONArray obj1 = (JSONArray) obj.get("notificationList");
			Map<String, JSONObject> map = new HashMap<String, JSONObject>();
			System.out.println(obj1.size());
			for (int i = 0; i < obj1.size(); i++) {

				map.put(String.valueOf(i), (JSONObject) obj1.get(i));
			}

			return Response.ok(new Viewable("/getNotification.jsp", map))
					.build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Path("/activeLog")
	@Produces(MediaType.TEXT_HTML)
	public Response activeLog() {
		return Response.ok(new Viewable("/activeLog.jsp")).build();
	}

	@GET
	@Path("/ActiveLog")
	@Produces(MediaType.TEXT_HTML)
	public Response getActivLog() {
		String serviceUrl = Connection.getURL() + "activeLog";

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

			return Response.ok(new Viewable("/activeLog2.jsp", list)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	@POST
	@Path("/undoService")
	@Produces(MediaType.TEXT_HTML)
	public Response undo(@FormParam("Type") String Type,
			@FormParam("IDType") String IDType) {
				String serviceUrl = Connection.getURL()+"undo";

		String urlParameters = "IDType=" + IDType + "&Type=" + Type;
		/* String retJson = */Connection.connect(serviceUrl, urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
			serviceUrl = Connection.getURL() + "activeLog";

		HttpSession session = request.getSession();
		Long igd = (Long) session.getAttribute("id");
		urlParameters = "myId=" + igd;
		String retJson = Connection.connect(serviceUrl, urlParameters, "GET",
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

			return Response.ok(new Viewable("/activeLog2.jsp", list)).build();
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}

