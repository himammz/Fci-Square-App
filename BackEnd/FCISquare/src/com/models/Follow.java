package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ConnectionDB.DBConnection;

public class Follow implements Actions{
		
	int ID;
	/**
	 * setting id
	 * @param id
	 */
	public Follow(int id) {
		ID = id;
	}

	public void exec() {

	}

	
	
	@Override
	public boolean add(Add_Place_Action order) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Responsible for removing follow relation between two users
	 */
	@Override
	public boolean remove() {
		Connection conn = DBConnection.getActiveConnection();

		String sql = "delete from follow where   IDfollow = ?";
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
