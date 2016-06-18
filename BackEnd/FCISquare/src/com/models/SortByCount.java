package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ConnectionDB.DBConnection;

public class SortByCount implements ShowHome{
	
	/**
	 * Respoinsible of showing user actions sorting it by Count
	 */
	@Override
	public JSONArray show(int myId) {
		Connection conn = DBConnection.getActiveConnection();
		String sql = "select count(*) as count , abdo.name  , abdo.placeName ,abdo.description  ,abdo.timeStamp  from  (select  users.name  , places.name as placeName ,places.description  ,checkin.timeStamp from checkin inner join places on  checkin.userID=? and places.id= checkin.placeID inner join users on users.id= checkin.userID union select  users.name  , places.name as placeName ,places.description  ,checkin.timeStamp from checkin inner join follow on Follower=? and Following= checkin.userID inner join `like` on `like`.userID= Following inner join places on  places.id= checkin.placeID  inner join users on users.id= checkin.userID union select users.name  , places.name as placeName ,places.description  ,checkin.timeStamp from checkin inner join follow on Follower=? and Following= checkin.userID inner join `comment` on `comment`.userID= Following inner join places on   places.id= checkin.placeID inner join users on users.id= checkin.userID union select users.name  , places.name as placeName ,places.description  ,checkin.timeStamp from checkin inner join follow on Follower=? and Following= checkin.userID inner join places on   places.id= checkin.placeID inner join users on users.id = checkin.userID )as abdo group by abdo.placeName order by count desc;";		
		String sql2 = "select places.name as placeName , users.name , places.description from users , follow ,saveplace,places where Follower=? and Following= saveplace.UserID and saveplace.PlaceID = places.id   and users.id = Following order by rate desc ";
		PreparedStatement st;
		ResultSet rs;
		JSONArray json = new JSONArray();
		try {
				st = conn.prepareStatement(sql);
				st.setInt(1, myId);
				st.setInt(2, myId);
				st.setInt(3, myId);
				st.setInt(4, myId);

				rs = st.executeQuery();
				while (rs.next()) {
					JSONObject j = new JSONObject();
					j.put("UserName", rs.getString("name"));
					j.put("PlaceName", rs.getString("placeName"));
					j.put("Description", rs.getString("description"));
					j.put("Time", rs.getString("timeStamp"));
					json.add(j);
				}
				st = conn.prepareStatement(sql2);
				st.setInt(1, myId);
				rs = st.executeQuery();
				while (rs.next()) {
					JSONObject j = new JSONObject();
					j.put("UserName", rs.getString("name"));
					j.put("PlaceName", rs.getString("placeName"));
					j.put("Description", rs.getString("description"));
					//j.put("Time",);
					json.add(j);
				}
			
			return json;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
