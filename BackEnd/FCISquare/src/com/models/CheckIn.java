package com.models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ConnectionDB.DBConnection;
import com.mysql.jdbc.Statement;

public class CheckIn implements Actions {
	private int ID,rate; // ID user who add checkin and check id auto increment
	private double Long, lat;
	private String Name; // name of the current place we use it when we add
	private String UserName; // place first in DB
	private String description;
	/**
	 * get Rate
	 * @return
	 */
	public int getRate() {
		return rate;
	}
	/**
	 * set Rate
	 * @param rate
	 */
	public void setRate(int rate) {
		this.rate = rate;
	}
	
	/**
	 * Constructor for initializing attribute
	 * @param iD
	 * @param Name
	 * @param lat
	 * @param l
	 * @param rate
	 */
	public CheckIn(int iD, String Name, double lat, double l,int rate) {

		this.Name = Name;
		ID = iD;
		Long = l;
		this.lat = lat;
		this.rate=rate;
	}

	
	public CheckIn() {

	}
	/**
	 * Constructor for initializing attribute
	 * @param iD
	 */
	public CheckIn(int iD) {

		ID = iD;
	}
	/**
	 * get name
	 * @return
	 */
	public String getName() {
		return Name;
	}
	/**
	 * set name
	 * @param name
	 */
	public void setName(String name) {
		Name = name;
	}
	/**
	 * get longitude
	 * @return
	 */
	public double getLong() {
		return Long;
	}
	
	/**
	 * set longitude
	 * @param l
	 */
	public void setLong(double l) {
		Long = l;
	}
	/**
	 * get latitude
	 * @return
	 */
	public double getLat() {
		return lat;
	}
	/**
	 * set latitude
	 * @param lat
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}
	/**
	 * get id
	 * @return
	 */
	public int getID() {
		return ID;
	}
	/**
	 * set id
	 * @param iD
	 */
	public void setID(int iD) {
		ID = iD;
	}

	public void exec() {

	}
	
	/**
	 * Responsible for add checking
	 */
	@Override
	public boolean add(Add_Place_Action order) {
		Connection conn = DBConnection.getActiveConnection();
		
		try {
			String sql = "Select * from places where `lat` = ? and `long` = ? and `name` = ?";
			PreparedStatement stmt;
			int IDCheck = 0,IDUser=0;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setDouble(1, ((CheckIn) order).getLat());
			stmt.setDouble(2, ((CheckIn) order).getLong());
			stmt.setString(3, ((CheckIn) order).getName());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				sql = "insert into checkin (userID,placeID) values(?,?)"; // TimeStamp feh moshkla not null ana shltha :V
				stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				
				stmt.setInt(1, ((CheckIn) order).getID());
				IDUser=rs.getInt("userID");
				stmt.setInt(2, rs.getInt("id"));
				int old_rate=rs.getInt("rate");
				old_rate+=rate;
				stmt.executeUpdate();
				
				/* update rate*/
				sql="select count(*) as count from checkin where placeID= ?";
				stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				stmt.setInt(1, rs.getInt("id"));
				ResultSet rs1 = stmt.executeQuery();
				int num=0;
				while(rs1.next())
					num=rs1.getInt(1);
				sql="update places  set rate=? where ID=?;";
				stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				stmt.setInt(1,old_rate/num);
				stmt.setInt(2,rs.getInt("id"));
				stmt.executeUpdate();
			} else {
				System.out.println("HIIII");
				 sql = "Insert into places (`name`,`lat`,`long`,`description`,`userID`,rate) VALUES  (?,?,?,?,?,?)";
				System.out.println(sql);
				stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				
				
				stmt.setString(1, ((CheckIn) order).getName());
				stmt.setDouble(2, ((CheckIn)order).getLat());
				stmt.setDouble(3, ((CheckIn)order).getLong());
				stmt.setString(4, "");
				stmt.setInt(5, ((CheckIn) order).getID());
				stmt.setInt(6, ((CheckIn) order).getRate());
				stmt.executeUpdate();
				
				 sql = "Select * from places where `lat` = ? and `long` = ? and `name` = ?";
				 stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

				stmt.setDouble(1, ((CheckIn) order).getLat());
				stmt.setDouble(2, ((CheckIn) order).getLong());
				stmt.setString(3, ((CheckIn) order).getName());
				 rs = stmt.executeQuery();
				if (rs.next()) {
					sql = "insert into checkin (userID,placeID) values(?,?)"; // TimeStamp feh moshkla not null ana shltha :V
					stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
					
					stmt.setInt(1, ((CheckIn) order).getID());
					IDUser=rs.getInt("userID");
					stmt.setInt(2, rs.getInt("id"));
					stmt.executeUpdate();
				}
			}
			
			ResultSet rs1=stmt.getGeneratedKeys();
			System.out.println(sql);
			if(rs1.next()){
				
				IDCheck=rs1.getInt(1);
				
			}
			
			sql = "insert into notification (`Type`,`IDType`,`UserID`) values(?,?,?);"; // TimeStamp feh moshkla not null ana shltha :V
			stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, "Check-in");
		
			stmt.setInt(2, IDCheck);
			stmt.setInt(3, IDUser);
			System.out.println(stmt);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public JSONObject show(String id,String uid){
		Connection conn = DBConnection.getActiveConnection();
		
		try {
			String sql = "select placeID from checkin where checkID=?;";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(id));
			ResultSet rs = stmt.executeQuery();
			
			int placeid=0;
			
			
			
			if(rs.next()){
				placeid=rs.getInt("placeID");
			}else{
				System.out.println("mfeeeesh");
			}
			
			
			sql = "select * from places where id=?;";
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, placeid);
			rs = stmt.executeQuery();
			
			JSONObject json = new JSONObject();
			while(rs.next()){
				
				json.put("uid", uid);
				json.put("checkinid",id);
				json.put("id", rs.getInt("id"));
				json.put("name", rs.getString("name"));
				json.put("desc", rs.getString("description"));
				json.put("lat", rs.getDouble("lat"));
				json.put("lng", rs.getDouble("long"));
			}
			Like like=new Like();
			json=like.getLikes(json,id);
			sql="select userID from `like` where userID=? and checkID=?;";
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, Integer.parseInt(uid));
			stmt.setInt(2, Integer.parseInt(id));
			 rs = stmt.executeQuery();
			if(rs.next())
			 json.put("Like",0);
			else
				json.put("Like",1);
			return json;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	

	@Override
	public boolean remove() {

		Connection conn = DBConnection.getActiveConnection();

		String sql = "delete from checkin where checkID = ?";
		PreparedStatement stmt;
		try {

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ID);
			stmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
