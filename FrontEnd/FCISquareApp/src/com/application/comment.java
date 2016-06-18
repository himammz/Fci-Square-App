package com.application;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
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

import com.application.*;

@Path("/")
public class comment {
	@Context
	HttpServletRequest request;

	@POST
	@Path("/newComment")
	@Produces(MediaType.TEXT_HTML)
	public Response newComment(@FormParam("comment") String comment,
			@FormParam("checkinID") String checkinid,
			@FormParam("uid") String uid) {
		String serviceUrl = Connection.getURL() + "NewComment";

		String urlParameters = "checkinid=" + checkinid + "&uid=" + uid
				+ "&comment=" + comment;
		Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");

		String serviceUrl1 = Connection.getURL() + "ShowCheckin";
		HttpSession session = request.getSession();

		String urlParameters1 = "checkinid=" + checkinid + "&uid=" + uid;

		String retJson = Connection.connect(serviceUrl1, urlParameters1,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
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

}
