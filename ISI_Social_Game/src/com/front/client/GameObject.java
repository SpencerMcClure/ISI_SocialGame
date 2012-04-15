package com.front.client;


import com.google.gwt.canvas.dom.client.Context2d;


public abstract class GameObject{

	protected Context2d c;
	Vector loc;
	protected int width;
	protected int height;
	protected int z;
	public enum Class {NODE,JAMMER,CONNECTION};
	Class type=null;
	public EventLog log;
	
	public GameObject(Context2d c) 
	{
		this.c = c;
		loc = new Vector(0,0);
		width = 1;
		height = 1;
		z=1;
	}
	
	public GameObject(Context2d c, int x, int y, EventLog log) 
	{
		this.c = c;
		loc = new Vector(x,y);
		this.log = log;
		//width = 1;
		//height = 1;
	}
	
	public GameObject(Context2d c, int x, int y, int width, int height, EventLog log) 
	{
		this.c = c;
		loc = new Vector(x,y);
		this.width = width;
		this.height = height;	
		this.log = log;
	}
	
	public boolean isClicked(int mousex, int mousey) {
		if(log!=null)
			log.addText("GameObject clicked!");
		return (mousex>loc.x && mousex<(loc.x+width) && mousey > loc.y && mousey < (loc.y+height));
	}
	
	final public Class getType() {
		return type;
	}
	
	final public void hereIsLog(EventLog l) {
		log =l;
	}
	
	final public int getZ() {
		return z;
	}
	
	public abstract void update();
	
	
	public abstract void draw();
}
