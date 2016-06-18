package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ConnectionDB.DBConnection;
import com.mysql.jdbc.Statement;

public class Place implements Add_Place_Action{
	private double lang,lat;
	private int id,rate;
	
	/**
	 * get rate
	 * @return
	 */
	public int getRate() {
		return rate;
	}
	/**
	 * set rate
	 * @param rate
	 */
	public void setRate(int rate) {
		this.rate = rate;
	}

	private String name,Description;
	
	/**
	 * constructor to initialize attributes
	 */
	public Place(){
		lang=lat=id=0;
		name=Description="";
	}
	/**
	 * get description
	 * @return
	 */
	public String getDescription() {
		return Description;
	}
	/**
	 * set description
	 * @param description
	 */
	public void setDescription(String description) {
		Description = description;
	}
	/**
	 * Constructor for initializing attributes
	 * @param id
	 * @param name
	 * @param lat
	 * @param lng
	 * @param desc
	 */
	public Place(int id, String name,double lat, double lng,String desc) {
		this.lang = lng;
		this.lat = lat;
		this.name = name;
		this.id=id;
		this.Description=desc;
		this.rate = rate;
	}
	/**
	 * get id
	 * @return
	 */
	public int getId() {
		return id;
	}
	/**
	 * set id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * get longitude
	 * @return
	 */
	public double getLang() {
		return lang;
	}
	/**
	 * set longitude
	 * @param lang
	 */
	public void setLang(double lang) {
		this.lang = lang;
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
	 * get Name
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * set name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Responsible for adding new place to the system
	 */
	@Override
	public boolean add(Add_Place_Action order) {
		
		try {
			
			Connection conn = DBConnection.getActiveConnection();

			String sql = "Insert into places (`name`,`lat`,`long`,`description`,`userID`) VALUES  (?,?,?,?,?)";
			System.out.println(sql);

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, ((Place)order).getName());
			stmt.setDouble(2, ((Place)order).getLat());
			stmt.setDouble(3, ((Place)order).getLang());
			stmt.setString(4, ((Place)order).getDescription());
			stmt.setInt(5, ((Place)order).getId());
			System.out.println(stmt);
			stmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	/**
	 * Responsible for get my saved places
	 * @param json
	 * @param id
	 */
	public void getMyPlaces(JSONArray json,String id){
		
		try {
			Connection conn= DBConnection.getActiveConnection();
			String sql = "Select PlaceID from saveplace where UserID=?;";
			
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, Integer.parseInt(id));
			
			ResultSet res=stmt.executeQuery();
			
			ArrayList<Integer> ids=new ArrayList<Integer>();
			
			while(res.next()){
				ids.add(res.getInt("PlaceID"));
			}
			
			for(int i=0 ; i<ids.size(); i++){
				for(int j=0; j<json.size(); j++){
					if(ids.get(i)==((JSONObject)json.get(j)).get("id")){
						((JSONObject)json.get(j)).put("isSave","1");
					}
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Respoinsible for getting all places
	 * @return
	 */
	public JSONArray getAllPlaces(){
		
		
		try {
			Connection conn = DBConnection.getActiveConnection();

			String sql = "Select * from places;";
			
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ResultSet res=stmt.executeQuery();
			
			JSONObject json = new JSONObject();
			//ArrayList<JSONObject> place = new ArrayList<JSONObject>();
			JSONArray place = new JSONArray(); 
			while(res.next()){
				JSONObject p = new JSONObject();
				p.put("id", res.getInt("id"));
				p.put("name", res.getString("name"));
				p.put("lat", res.getString("lat"));
				p.put("long", res.getString("long"));
				p.put("desc", res.getString("description"));
				place.add(p);
			}
			System.out.println(place);
			return place;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	/**
	 * Responsible following place
	 * @param uid
	 * @param pid
	 */
	public void followPlace(Integer uid, Integer pid){

		try {
			Connection conn = DBConnection.getActiveConnection();

			String sql = "Insert into  saveplace Values (?,?);";
			
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, uid);
			stmt.setInt(2, pid);
			System.out.println(stmt);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	/**
	 * Responsible for unfollow place
	 * @param uid
	 * @param pid
	 */
	public void unfollowPlace(Integer uid, Integer pid){

		try {
			Connection conn = DBConnection.getActiveConnection();
			PreparedStatement stmt;
			String sql="select UserID,PlaceID from saveplace where UserID=? and PlaceID=?;";

			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setInt(1, uid);
			stmt.setInt(2, pid);
			ResultSet res=stmt.executeQuery();
			
			if(res.next()){

			sql = "delete from  saveplace where UserID=? and PlaceID=?;";
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setInt(1, uid);
			stmt.setInt(2, pid);
			stmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	/**
	 * Responsible for getting my places
	 * @param uid
	 * @return
	 */
	public JSONArray MyPlaces(String uid){
		try {
			Connection conn = DBConnection.getActiveConnection();
			int UID=Integer.parseInt(uid);
			
			String sql = "Select PlaceID from saveplace where UserID=?;";
			
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, UID);
			ResultSet res=stmt.executeQuery();
			
			ArrayList<Integer>places=new ArrayList<Integer>();
			
			while(res.next()){
				places.add(res.getInt("PlaceID"));
			}
			
			JSONObject json = new JSONObject();
			JSONArray place = new JSONArray();
			
			for(int i=0 ; i< places.size(); i++){
				sql = "Select id,name,lat,`long`,description from places where id=?;";
				stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				stmt.setInt(1, places.get(i));
				res=stmt.executeQuery();
				while(res.next()){
					JSONObject p = new JSONObject();
					p.put("id", res.getInt("id"));
					p.put("name", res.getString("name"));
					p.put("lat", res.getString("lat"));
					p.put("lng", res.getString("long"));
					p.put("desc", res.getString("description"));
					place.add(p);
				}
			}

			return place;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
