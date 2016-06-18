package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

import com.ConnectionDB.DBConnection;
import com.mysql.jdbc.Statement;

public class Like implements Actions {

	private int ID; //for user
	public Like()
	{}
	public void exec() {

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
	/**
	 * Responsible for initializing attributes
	 * @param iD
	 */
	public Like(int iD) {
		super();
		ID = iD;
	}
	/**
	 * Responsible for get likes
	 * @param json
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public JSONObject getLikes(JSONObject json, String id) throws SQLException
	{
		Connection conn = DBConnection.getActiveConnection();
		String sql = "Select * from `like` where `like`.checkID = ? ;";
		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		int conLikes=0;
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, Integer.parseInt(id));
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
			conLikes++;
		json.put("Likes", conLikes);
		Comment comm=new Comment();
		return comm.getComments(json, id);
	}
	/**
	 * Responsible for adding new like 
	 * @param id
	 * @param checkid
	 * @param type
	 * @return
	 * @throws SQLException
	 */
	public boolean addLike(String id ,String checkid,String type) throws SQLException
	{
		Connection conn = DBConnection.getActiveConnection();
		String sql = null;
		 int likeID=0;
		 ResultSet s1;
		PreparedStatement stmt = null;
		if(type.equals("Unlike"))
			sql = "Insert into `like` (`userID`, `checkID`) values (?,?);";
		else
			{
			sql="select likeID from `like` where userID=? and checkID=?";
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, Integer.parseInt(id));
			stmt.setInt(2, Integer.parseInt(checkid));
			s1=stmt.executeQuery();
			while(s1.next())
				likeID=s1.getInt(1);
			sql = "delete from `like` where `userID`=? and checkID=?;";}
		
		stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setInt(1, Integer.parseInt(id));
		stmt.setInt(2, Integer.parseInt(checkid));
		 stmt.executeUpdate();
		 s1=stmt.getGeneratedKeys();
		
		 while(s1.next())
			 likeID=s1.getInt(1);
		 
		if(type.equals("Unlike"))	
			sql="insert into notification (Type,UserID,IDType) values('Like',?,?)";
		else
			sql="delete from notification where `UserID`=? and IDType=? and Type='Like'";
					
		stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setInt(1, Integer.parseInt(id));
		stmt.setInt(2, likeID);
		
		stmt.executeUpdate();
		return true;
	}
	
	@Override
	public boolean add(Add_Place_Action order) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Responsible for removing like
	 */
	@Override
	public boolean remove() {

		Connection conn = DBConnection.getActiveConnection();
		String sql = "delete from `Like` where  likeID = ?";
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
