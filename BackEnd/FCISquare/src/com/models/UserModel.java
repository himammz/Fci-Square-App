package com.models;
import Controller.SignUpController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ConnectionDB.DBConnection;
import com.mysql.jdbc.Statement;

public class UserModel {

	private String name;
	private String email;
	private String pass;
	private Integer id;
	private Integer Age;
	private String Gender;
	private double Lat;
	private double Long;
	private String userName;
	
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
	 * get latitude
	 * @return
	 */
	public double getLat() {
		return Lat;
	}
	/**
	 * set latitude
	 * @param lat
	 */
	public void setLat(double lat) {
		Lat = lat;
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
	 * get password user
	 * @return
	 */
	public String getPass() {
		return pass;
	}
	/**
	 * set password user
	 * @param pass
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
	/**
	 * get name
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
	 * get email
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * set email of user
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * get user id
	 * @return
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * set user id
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * get age user
	 * @return
	 */
	public Integer getAge() {
		return Age;
	}
	
	/**
	 * set age user
	 * @param age
	 */
	public void setAge(Integer age) {
		Age = age;
	}
	/**
	 * get gender user
	 * @return
	 */
	public String getGender() {
		return Gender;
	}
	/**
	 * set gender user
	 * @param gender
	 */
	public void setGender(String gender) {
		Gender = gender;
	}
	
	/**
	 * Responsible for adding new user to the system
	 * @param name
	 * @param email
	 * @param pass
	 * @param userName
	 * @param gender
	 * @param age
	 * @return
	 */
	public static UserModel addNewUser(String name, String email, String pass, String userName, String gender, String age) {
		try {
			Connection conn = DBConnection.getActiveConnection();

			String sql = "Insert into users (`name`,`email`,`password`,`userName`,`gender`,`age`) VALUES  (?,?,?,?,?,?)";
			System.out.println(sql);

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setString(3, pass);
			stmt.setString(4, userName);
			stmt.setString(5, gender);
			stmt.setInt(6, Integer.parseInt(age));
			System.out.println(stmt);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				UserModel user = new UserModel();
				user.id = rs.getInt(1);
				user.email = email;
				user.pass = pass;
				user.name = name;
				user.Gender = gender;
				user.Age = Integer.parseInt(age);
				return user;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Responsible for logging in
	 * @param email
	 * @param pass
	 * @return
	 */
	public static UserModel login(String email, String pass) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Select * from users where `email` = ? and `password` = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, pass);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				UserModel user = new UserModel();
				user.id = rs.getInt(1);
				user.email = rs.getString("email");
				user.pass = rs.getString("password");
				user.name = rs.getString("name");
				user.userName=rs.getString("userName");
				user.Age=rs.getInt("age");
				
				return user;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Responsible for updating position
	 * @param id ID of the user
	 * @param lat
	 * @param lon
	 * @return
	 */
	public static boolean updateUserPosition(Integer id, Double lat, Double lon) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Update users set `lat` = ? , `long` = ? where `id` = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, lat);
			stmt.setDouble(2, lon);
			stmt.setInt(3, id);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Responsible for follow user
	 * @param id1
	 * @param un
	 * @return
	 */
	public static boolean follow(Integer id1, String un) {
		try {
			Connection conn = DBConnection.getActiveConnection();

			String sqlselect = "select id from users where users.userName = ? and users.id != ?";
			String sql = "Insert into follow (`Follower`,`Following`) VALUES  (?,?)";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sqlselect);
			stmt.setString(1, un);
			stmt.setInt(2, id1);
			ResultSet rrs = stmt.executeQuery();
			int id2;
			if (rrs.next()) {
				id2 = rrs.getInt(1);
			} else
				return false;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id1);
			stmt.setInt(2, id2);
			stmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Responsible for unfollow user
	 * @param id1
	 * @param userName
	 * @return
	 */
	public static boolean unfollow(Integer id1, String userName) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sqlSelect = "select id from users where users.userName = ? and users.id != ?";
			String sql = "Delete from follow where Follower=? and Following=?;";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sqlSelect);
			stmt.setString(1, userName);
			stmt.setInt(2, id1);

			ResultSet rs1 = stmt.executeQuery();
			int id2;
			if (rs1.next())
			{
				id2 = rs1.getInt(1);
				
			}
			else 
				return false;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id1);
			stmt.setInt(2, id2);
			stmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Responsible for getting followers
	 * @param id
	 * @return
	 */
	public static ArrayList<String> getfollow(Integer id) {
		Connection conn = DBConnection.getActiveConnection();
		String sql = "Select name from follow,users  where Follower=? and Following=id;";
		String sql1="select id from users where id="+id+";";
		
		PreparedStatement stmt;
		
		try {
		
			stmt = conn.prepareStatement(sql1);
			ResultSet rs1=stmt.executeQuery();
			ArrayList<String> name = new ArrayList<String>();
			
			if(!rs1.next())
			{
				name.add("Not Found");
				return name;
			}	
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				name.add(rs.getString("name"));
			}
			return name;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	/**
	 * Responsible for getting location
	 * @param id
	 * @return
	 */
	public String getLocation(Integer id) {
		Connection conn = DBConnection.getActiveConnection();
		String sql = "Select Lat,`Long` from users where id=?;";
		
		PreparedStatement stmt;		
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			String res = null;
			while (rs.next()) {
				this.setLat(rs.getDouble("Lat"));
				this.setLong(rs.getDouble("Long"));
				res = rs.getString("Lat") + " " + rs.getString("Long");
			}
			
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	void add (Add_Place_Action toAdd)
	{
	}
}
