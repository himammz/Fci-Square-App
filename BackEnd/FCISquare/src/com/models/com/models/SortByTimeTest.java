package com.models.com.models;

import org.json.simple.JSONArray;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.models.ShowHome;
import com.models.SortByTime;

public class SortByTimeTest {
	@DataProvider (name = "test1")
	public static Object[][] users() {
	   return new Object[][] { {"1"},{"2"},{"3"},{"6"}};
	   }
	
  @Test(dataProvider = "test1")
  public void show(String id) {
	  ShowHome h = new SortByTime();
		JSONArray json = h.show(Integer.parseInt(id));
  }
}
