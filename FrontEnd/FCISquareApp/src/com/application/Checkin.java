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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Path("/")
public class Checkin {
	@Context
	HttpServletRequest request;

	@GET
	@Path("/Checkin")
	@Produces(MediaType.TEXT_HTML)
	public Response Checkin() {
		return Response.ok(new Viewable("/CurrentLocation.jsp")).build();
	}

	@POST
	@Path("/addCheck")
	@Produces(MediaType.TEXT_HTML)
	public Response addCheck(@FormParam("Current") String name,
			@FormParam("lat") String lat, @FormParam("long") String lng,
			@FormParam("rate") String rate) {
		String serviceUrl = Connection.getURL() + "AddCheck";
		HttpSession session = request.getSession();
		Long id1 = (Long) session.getAttribute("id");
		String urlParameters = "id1=" + id1 + "&Name=" + name + "&lat=" + lat
				+ "&lan=" + lng + "&rate=" + rate;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");

		return Response.ok(new Viewable("/CurrentLocation.jsp")).build();
	}

	@POST
	@Path("/showCheckin")
	@Produces(MediaType.TEXT_HTML)
	public Response ShowCheckin(@FormParam("type") String type,
			@FormParam("id") String checkinid) {
		String serviceUrl = Connection.getURL() + "ShowCheckin";
		HttpSession session = request.getSession();

		Long uid = (Long) session.getAttribute("id");

		String urlParameters = "checkinid=" + checkinid + "&uid=" + uid;
		
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		System.out.println(retJson);
		try {
			if(retJson==null){
				return Response.ok(new Viewable("/Error.jsp")).build();
			}
			obj = (JSONObject) parser.parse(retJson);

			Map<String, JSONObject> map = new HashMap<String, JSONObject>();
			map.put(String.valueOf(obj.get("id")), obj);
			
			return Response.ok(new Viewable("/showCheckin.jsp", map)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
		return null;

	}

	@POST
	@Path("/updateMyLocation")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateLocation(@FormParam("lat") String lat,
			@FormParam("long") String lon) {
		HttpSession session = request.getSession();
		Long id = (Long) session.getAttribute("id");
		String serviceUrl = "http://se2firstapp-softwareeng2.rhcloud.com/FCISquare/rest/updatePosition";
		String urlParameters = "id=" + id + "&lat=" + lat + "&long=" + lon;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		JSONObject obj;
		try {
			
			obj = (JSONObject) parser.parse(retJson);
			Long status = (Long) obj.get("status");
			if (status == 1)
				return "Your location is updated";
			else
				return "A problem occured";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "A problem occured";

	}

	@GET
	@Path("/showLocation")
	@Produces(MediaType.TEXT_HTML)
	public Response showLocationPage() {
		return Response.ok(new Viewable("/ShowLocation.jsp")).build();
	}
}
