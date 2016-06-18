package Controller;

import java.sql.SQLException;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.models.Add_Place_Action;
import com.models.Like;
import com.models.Add_Place_Action;


@Path("/")
public class LikeController {
	
	@POST
	@Path("/Like")
	@Produces(MediaType.TEXT_PLAIN)
	public void newComment(@FormParam("uid")String id,@FormParam("checkin")String uid,@FormParam("Like")String text) throws SQLException{
		Add_Place_Action Like =  new Like();
		((Like) Like).addLike(id,uid,text);	
	}
}