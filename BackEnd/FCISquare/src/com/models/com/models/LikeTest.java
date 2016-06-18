package com.models.com.models;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.models.Add_Place_Action;
import com.models.Like;

public class LikeTest {

	@DataProvider (name = "test1")
	public static Object[][] users() {
	   return new Object[][] { {"1","43", "Like"} , {"2","43", "Like"},{"6","43", "Like"}};
	}
  @Test(dataProvider = "test1")
  public void addLike(String id,String uid,String text) throws SQLException{
		Add_Place_Action Like =  new Like();
		 Assert.assertEquals(true, ((Like) Like).addLike(id,uid,text));    
  }
}
