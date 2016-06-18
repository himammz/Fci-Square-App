package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.ConnectionDB.DBConnection;
import com.mysql.jdbc.Statement;

public class Comment implements Actions{
	private int ID;
	private String Text;
	private UserModel user;
	
	
	/**
	 * Constructor for initializing attributes
	 * @param id
	 */
	public Comment(int id) {
		ID = id;
	}
	/**
	 * Contructor for initializing attributes
	 * @param iD
	 * @param text
	 */
	public Comment(int iD, String text) {
		super();
		ID = iD;
		Text = text;
	}
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * get ID
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
	/**
	 * get text of comment
	 * @return
	 */
	public String getText() {
		return Text;
	}
	/**
	 * set text of comment
	 * @param text
	 */
	public void setText(String text) {
		Text = text;
	}
	
	public void exec()
	{
		
	}
	/**
	 * Responsible for getting all comments
	 * @param json
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public JSONObject getComments(JSONObject json, String id) throws SQLException
	{
		Connection conn = DBConnection.getActiveConnection();
		String sql = "Select * from `comment` where `comment`.checkID = ? ;";
		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		int conLikes=0;
		ArrayList<String>map=new ArrayList<String>();
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, Integer.parseInt(id));
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
		{
			
			sql="Select * from `users` where `users`.ID = ?";
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, rs.getInt("userID"));
			ResultSet rs1 = stmt.executeQuery();
			while(rs1.next()){
			map.add(rs.getString("commentText"));
			map.add( rs1.getString("name"));conLikes++;}
		}
		json.put("Comment",map);
		return json;
	}
	@Override
	public boolean add(Add_Place_Action order) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Responsible for add new comment 
	 * @param id
	 * @param uid
	 * @param text
	 */
	public void addNewComment(String id,String uid,String text){
		Connection conn = DBConnection.getActiveConnection();
		
		try {
			String sql = "insert into comment(`commentText`,`userID`,`checkID`) values (?,?,?);";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, text);
			stmt.setInt(2, Integer.parseInt(uid));
			stmt.setInt(3, Integer.parseInt(id));
			stmt.executeUpdate();
			
			
			sql = "insert into notification(`Type`,`IDType`,`UserID`) values (?,?,?);";
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "Comment");
			stmt.setInt(2, Integer.parseInt(id));
			stmt.setInt(3, Integer.parseInt(uid));
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	/**
	 * Responsible for removing comment
	 */
	@Override
	public boolean remove() {
		Connection conn = DBConnection.getActiveConnection();
		String sql = "delete from `Comment` where   commentID = ?";
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
}
