package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ConnectionDB.DBConnection;

public class ListNotification {
	private ArrayList<String> list;
	private int id;


	/**
	 * Constructor to initialize attributes
	 * @param ID
	 */
	public ListNotification(int ID) {

		id = ID;

		list = new ArrayList<String>();

	}
	/**
	 * get list of notification
	 * @return
	 */
	public ArrayList<String> getList() {
		return list;
	}
	/**
	 * set list of notifications
	 * @param list
	 */
	public void setList(ArrayList<String> list) {
		this.list = list;
	}
	/**
	 * Responsible for adding new notification
	 * @param iD
	 * @param notifi
	 */
	public void addNotification(int iD, Actions notifi) {
		// ad to DB
	}
	/**
	 * Responsible for returning notification
	 * @param id1
	 * @return
	 */
	public JSONArray getNotification(int id1) {
		try {
			Integer id = id1;
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Select `name` from users, follow where follow.Following = ? and users.id = follow.Follower";
			String sql2 = "Select users.name, checkin.checkID from  users, checkin, follow where follow.Follower = ? and checkin.userID = follow.Following and follow.Following = users.id";
			String sql3 = "Select name, checkin.checkID from users, checkin, `like` where checkin.userID = ? and `like`.checkID = checkin.checkID and `like`.userID = users.id";
			String sql4 = "Select name, checkin.checkID from users, checkin, `comment` where checkin.userID = ? and `comment`.checkID = checkin.checkID and `comment`.userID = users.id";
			PreparedStatement stmt, stmt2, stmt3, stmt4;
			stmt = conn.prepareStatement(sql);
			stmt2 = conn.prepareStatement(sql2);
			stmt3 = conn.prepareStatement(sql3);
			stmt4 = conn.prepareStatement(sql4);
			stmt.setInt(1, id);
			stmt2.setInt(1, id);
			stmt3.setInt(1, id);
			stmt4.setInt(1, id);
			JSONArray notification = new JSONArray();

			ResultSet rs, rs2, rs3, rs4;
			
			rs = stmt.executeQuery();
			rs2 = stmt2.executeQuery();
			rs3 = stmt3.executeQuery();
			rs4 = stmt4.executeQuery();
			// follow
			while (rs.next()) {

				JSONObject p = new JSONObject();
				p.put("name", rs.getString("users.name"));
				p.put("type", "follow");
//				p.put(rs.getString("name"), " follows you");
				notification.add(p);
				
			}
			// checkin
			while (rs2.next()) {
				JSONObject p = new JSONObject();
				p.put("name", rs2.getString("name"));
				p.put("checkinID", rs2.getInt("checkID"));
				p.put("type", "checkin");
				notification.add(p);

			}
			// like chekcin
			while (rs3.next()) {
				System.out.println(notification.size() + "kkkkjl");
				JSONObject p = new JSONObject();
				p.put("name", rs3.getString("name"));
				p.put("checkinID", rs3.getInt("checkID"));
				p.put("type", "like");
				notification.add(p);
			}
			// comment
			while (rs4.next()) {
				System.out.println(notification.size() + "4444kkkkjl");
				JSONObject p = new JSONObject();
				p.put("name", rs4.getString("name"));
				p.put("checkinID", rs4.getInt("checkID"));
				p.put("type", "comment");
				notification.add(p);
			}
			System.out.println("Notification Size" + notification.size());
			return notification;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
	/**
	 * Responsible for returning all notifications
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<String> getAllNotification() throws SQLException {
		Connection conn = DBConnection.getActiveConnection();
		String sql = "select  `Type`,  places.name ,  users.name as userName  from notification left join   `like`  on  (  notification.`Type`= 'Like' and notification.UserID=`like`.userID and notification.IDType=`like`.likeID) left join `comment` on  ( notification.`Type`= 'Comment' and notification.IDType=`comment`.commentID and  `comment`.userID = notification.UserID  ) left join checkin on (notification.IDType = `checkin`.checkID and notification.`Type`='Check-in' and notification.UserID=checkin.userID)or (`like`.checkID = `checkin`.checkID and notification.`Type`='Like' ) or( `comment`.checkID = `checkin`.checkID and notification.`Type`='Comment') left join follow on  ( notification.`Type`= 'Follow' and notification.IDType=follow.IDfollow and  follow.Follower = notification.UserID  ) left join places   on  checkin.placeID=places.id left join users on Following=users.id or checkin.userID= users.id where notification.UserID=?  order by notification.timeStamp desc";
		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String type = rs.getString("Type");
			String placeName = rs.getString("name");
			String userName = rs.getString("userName");

			if (type.equals("Like") || type.equals("Comment")) {
				list.add("you " + type + " " + userName + "'s check-in "
						+ placeName);

			} else if (type.equals("Follow")) {
				list.add("you follow " + userName);

			} else if (type.equals("Check-in")) {
				list.add("you check-in " + placeName);

			}
		}

		return list;
	}
}