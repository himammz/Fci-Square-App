package com.models;

public interface Actions extends Add_Place_Action{
	void exec();
	boolean add(Add_Place_Action order);
	boolean remove ();

}
