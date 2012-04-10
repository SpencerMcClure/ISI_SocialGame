package com.front.client;


import com.google.gwt.canvas.dom.client.Context2d;


public abstract class GameObject {

	protected Context2d c;
	Vector loc;
	protected int width;
	protected int height;
	
	public GameObject(Context2d c) 
	{
		this.c = c;
		loc = new Vector(0,0);
		width = 1;
		height = 1;
	}
	
	public GameObject(Context2d c, int x, int y) 
	{
		this.c = c;
		loc = new Vector(x,y);
		width = 1;
		height = 1;
	}
	
	public GameObject(Context2d c, int x, int y, int width, int height) 
	{
		this.c = c;
		loc = new Vector(x,y);
		this.width = width;
		this.height = height;	
	}
	
	public abstract void update();
	
	
	public abstract void draw();
}
