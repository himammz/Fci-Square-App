package com.models.com.models;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.models.Add_Place_Action;
import com.models.CheckIn;

public class CheckInTest {
	@DataProvider (name = "test1")
	public static Object[][] users() {
	   return new Object[][] { {"1", "giza","30.030974299999997","31.209859500000004","2"} , {"2", "giza","30.030974299999997","31.209859500000004","2"}};
	   
	}
  @Test(dataProvider = "test1")
  public void add( String id1 ,String name ,String lat , String lng,String rate) {
		Add_Place_Action Check= new CheckIn (Integer.parseInt(id1),name,Double.parseDouble(lat),Double.parseDouble(lng),Integer.parseInt(rate));
		boolean ok=Check.add(Check);
		Assert.assertEquals(true, ok);   
  }
}
