package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ConnectionDB.DBConnection;


public class SortByPlace implements ShowHome {
	
	/**
	 * Respoinsible of showing user actions sorting it by Place
	 */
	@Override
	public JSONArray show(int myId) {
		Connection conn = DBConnection.getActiveConnection();	
		String sql ="select places.`long`, places.lat, users.name  , places.name as placeName ,places.description  ,checkin.timeStamp from checkin inner join places on  checkin.userID=? and places.id= checkin.placeID inner join users on users.id= checkin.userID union select places.`long`, places.lat, users.name  , places.name as placeName ,places.description  ,checkin.timeStamp from checkin inner join follow on Follower=? and Following= checkin.userID inner join `like` on `like`.userID= Following inner join places on  places.id= checkin.placeID  inner join users on users.id= checkin.userID union select places.`long`, places.lat,users.name  , places.name as placeName ,places.description  ,checkin.timeStamp from checkin inner join follow on Follower=? and Following= checkin.userID inner join `comment` on `comment`.userID= Following inner join places on   places.id= checkin.placeID inner join users on users.id= checkin.userID union select places.`long`, places.lat,users.name  , places.name as placeName ,places.description  ,checkin.timeStamp from checkin inner join follow on Follower=? and Following= checkin.userID inner join places on   places.id= checkin.placeID inner join users on users.id= checkin.userID;";
		String sql2 = "select places.lat, places.`long`, places.name as placeName , users.name , places.description from users , follow ,saveplace,places where Follower=? and Following= saveplace.UserID and saveplace.PlaceID = places.id   and users.id = Following order by rate desc ";
		PreparedStatement st;
		ResultSet rs;
		JSONArray json = new JSONArray();
		ArrayList<CheckIn> c = new ArrayList<CheckIn>();
		try {
				st = conn.prepareStatement(sql);
				st.setInt(1, myId);
				st.setInt(2, myId);
				st.setInt(3, myId);
				st.setInt(4, myId);
				rs = st.executeQuery();
				while (rs.next()) {
					CheckIn ci = new CheckIn();
					//ci.setID(myId);
					ci.setDescription(rs.getString("description"));
					ci.setLat(rs.getDouble("lat"));
					ci.setLong(rs.getDouble("long"));
					ci.setUserName(rs.getString("name"));
					ci.setName(rs.getString("placeName"));
					c.add(ci);
					
				}
				st = conn.prepareStatement(sql2);
				st.setInt(1, myId);
				rs = st.executeQuery();
				while (rs.next()) {
					CheckIn ci = new CheckIn();
					//ci.setID(myId);
					ci.setDescription(rs.getString("description"));
					ci.setLat(rs.getDouble("lat"));
					ci.setLong(rs.getDouble("long"));
					ci.setUserName(rs.getString("name"));
					ci.setName(rs.getString("placeName"));
					c.add(ci);
				}
				UserModel u = new UserModel();
				u.getLocation(myId);
				check_ins cis = new check_ins();
				cis.setLat(u.getLat());
				cis.setLong(u.getLong());
				cis.setList(c);
				cis.sort();
				c = cis.getList();
				//Collections.sort(c);
				
				System.out.println(c);
				for(int i = 0; i < c.size(); ++i){
					JSONObject j = new JSONObject();
					j.put("UserName", c.get(i).getUserName());
					j.put("PlaceName", c.get(i).getName());
					j.put("Description", c.get(i).getDescription());
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
