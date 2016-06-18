package Controller;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;

import com.application.Connection;
@Path("/")
public class Undo {
	@POST
	@Path("/undoService")
	@Produces(MediaType.TEXT_HTML)
	public Response undo(@FormParam("Type") String Type,
			@FormParam("IDType") String IDType) {
		System.out.println(Type + " " + IDType);
//		String serviceUrl = "http://backend-fciapp.rhcloud.com/FCISquare/rest/undo";
//		String serviceUrl = "http://localhost:8080/FCISquare/rest/undo";
		String serviceUrl = Connection.getURL()+"undo";

		// HttpSession session = request.getSession();
		String urlParameters = "IDType=" + IDType + "&Type=" + Type;
		/* String retJson = */Connection.connect(serviceUrl, urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
		// JSONArray obj = new JSONArray();
		// JSONParser parser = new JSONParser();
		return Response.ok(new Viewable("/activeLog.jsp")).build();

	}

}
