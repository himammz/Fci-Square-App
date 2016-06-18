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
public class Signup {
	@Context
	HttpServletRequest request;

	@GET
	@Path("/signUp")
	@Produces(MediaType.TEXT_HTML)
	public Response signUpPage() {
		return Response.ok(new Viewable("/Signup.jsp")).build();
	}

	@POST
	@Path("/doSignUp")
	@Produces(MediaType.TEXT_HTML)
	public Response showHomePage(@FormParam("name") String name,
			@FormParam("userName") String userName,
			@FormParam("email") String email, @FormParam("pass") String pass,
			@FormParam("gender") String gender, @FormParam("age") String age) {
		String serviceUrl = Connection.getURL() + "signup";

		String urlParameters = "name=" + name + "&email=" + email + "&pass="
				+ pass + "&userName=" + userName + "&gender=" + gender
				+ "&age=" + age;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		HttpSession session = request.getSession();
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		try {
			System.out.println(retJson);
			if(retJson==null){
				return Response.ok(new Viewable("/Error.jsp")).build();
			}
			obj = (JSONObject) parser.parse(retJson);
			System.out.println(190);
			session.setAttribute("email", obj.get("email"));
			session.setAttribute("name", obj.get("name"));
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
			map.put("gender", (String) obj.get("gender"));
			System.out.println((String) obj.get("age"));
			map.put("age", (String) obj.get("age"));
			return Response.ok(new Viewable("/home.jsp", map)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
