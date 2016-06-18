package Controller;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.models.Add_Place_Action;
import com.models.CheckIn;
import com.models.Comment;

@Path("/")
public class CommentController {
	
	@POST
	@Path("/NewComment")
	@Produces(MediaType.TEXT_PLAIN)
	public void newComment(@FormParam("checkinid")String id,@FormParam("uid")String uid,@FormParam("comment")String text){
		Add_Place_Action Comment = new Comment();
		System.out.println(id+ " "+ uid+" "+text+"vdvdvdvdvd");
//		System.out.println("this is pid2 =" + pid);
		((Comment) Comment).addNewComment(id,uid,text);
		
		//return json.toJSONString();
		
	}
}
