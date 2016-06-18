package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.FormParam;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ConnectionDB.DBConnection;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

public class Notification {
	private int idUser;
	private String Type;
	private int TypeID;
	private String userName;
	private String placeName;
	
	/**
	 * Constructor to initialize attributes
	 */
	public Notification(){
		userName = "";
		placeName = "";
		Type = "";
		
	}
	/**
	 * get user id
	 * @return
	 */
	public int getIdUser() {
		return idUser;
	}
	/**
	 * set user id
	 * @param idUser
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	/**
	 * get type of notification
	 * @return
	 */
	public String getType() {
		return Type;
	}
	/**
	 * set type notification
	 * @param type
	 */
	public void setType(String type) {
		Type = type;
	}
	/**
	 * get type id
	 * @return
	 */
	public int getTypeID() {
		return TypeID;
	}
	/**
	 * set type id
	 * @param typeID
	 */
	public void setTypeID(int typeID) {
		TypeID = typeID;
	}
	/**
	 * get user name
	 * @return
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * set user name
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * get place name
	 * @return
	 */
	public String getPlaceName() {
		return placeName;
	}
	/**
	 * set place name
	 * @param placeName
	 */
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	/**
	 * Responsible for removing notification
	 * @param type	type of notification
	 * @param typeID	id of notification
	 * @return
	 */
	public boolean removeNotification(String type,int typeID){
		
		Connection conn = DBConnection.getActiveConnection();
		String sql = "delete from notification where `Type` = ? and IDType = ?";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, type);
			stmt.setInt(2, typeID);
			stmt.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	/**
	 * Responsible for getting all notification
	 * @return
	 * @throws SQLException
	 */
	public JSONArray getAllNotification() throws SQLException {
		//ArrayList<Notification> noti = new ArrayList<Notification>();
		Connection conn = DBConnection.getActiveConnection();
		String sql = "select IDType, `Type`,  places.name ,  users.name as userName  from notification left join   `like`  on  (  notification.`Type`= 'Like' and notification.UserID=`like`.userID and notification.IDType=`like`.likeID) left join `comment` on  ( notification.`Type`= 'Comment' and notification.IDType=`comment`.commentID and  `comment`.userID = notification.UserID  ) left join checkin on (notification.IDType = `checkin`.checkID and notification.`Type`='Check-in' and notification.UserID=checkin.userID)or (`like`.checkID = `checkin`.checkID and notification.`Type`='Like' ) or( `comment`.checkID = `checkin`.checkID and notification.`Type`='Comment') left join follow on  ( notification.`Type`= 'Follow' and notification.IDType=follow.IDfollow and  follow.Follower = notification.UserID  ) left join places   on  checkin.placeID=places.id left join users on Following=users.id or checkin.userID= users.id where notification.UserID=?  order by notification.timeStamp desc";
		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, idUser);
		ResultSet rs = stmt.executeQuery();
		JSONArray noti = new JSONArray();
		while (rs.next()) {
			JSONObject json = new JSONObject();
			json.put("placeName", rs.getString("name"));
			json.put("UserID", idUser);
			json.put("Type", rs.getString("Type"));
			json.put("UserName", rs.getString("userName"));
			json.put("IDType", rs.getInt("IDType"));
			noti.add(json);
		}
		return noti;
	}

	
}