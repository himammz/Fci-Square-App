package com.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class check_ins implements Comparator<CheckIn> {
	private ArrayList<CheckIn> list;
	private double Lat;
	private double Long;
	
	/**
	 * Constructor for initializing attributes
	 */
	public check_ins() {
		// TODO Auto-generated constructor stub
		list = new ArrayList<CheckIn>();
	}
	/**
	 * Constructor for initializing attribute
	 * @param cis
	 */
	public check_ins(check_ins cis){
		this.Lat = cis.Lat;
		this.Long = cis.Long;
		setList(cis.getList());
	}
	
	/**
	 * Responsible for comparing two checking using their latitude and longitude
	 */
	@Override
	public int compare(CheckIn o1, CheckIn o2) {
		// TODO Auto-generated method stub
		//check_ins cis = new check_ins();
		double x = Math.sqrt(Math.pow((o1.getLong() - getLong()), 2) + Math.pow((o1.getLat() - getLat()), 2));
		double y = Math.sqrt(Math.pow((o2.getLong() - getLong()), 2) + Math.pow((o2.getLat() - getLat()), 2));
		if(x == y)
			return 0;
		else if(x > y)
			return 1;
		else
			return -1;
		
	}
	/**
	 * Sorting  checkins
	 */
	public void sort(){
		Collections.sort(list, new check_ins());
	}
	/**
	 * get list of checkins
	 * @return
	 */
	public ArrayList<CheckIn> getList() {
		return list;
	}
	/**
	 * set the list of checkins
	 */
	public void setList(ArrayList<CheckIn> list) {
		this.list = list;
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
	 * set latitude
	 * @param l
	 */
	public void setLong(double l) {
		Long = l;
	}

}
