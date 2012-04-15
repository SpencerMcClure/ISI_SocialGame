package com.front.client;

import com.google.gwt.canvas.dom.client.Context2d;

public class FrontNode extends GameObject{

	static int size = 35;
	private double rotate=0;
	final double PI = 3.14159265;
	
	
	public FrontNode(Context2d c) {
		super(c,0,0, null);
		width = size;
		height = size;
		type = Class.NODE;
	}
	
	public FrontNode(Context2d c, int x, int y, EventLog log) {
		super(c,x,y,log);
		width = size;
		height = size;
		this.z = 100;
	}

	@Override
	public void update() {
		
		
	}

	@Override
	public void draw() {
		c.translate(loc.x, loc.y);
		c.setFillStyle("#CCFFFF");
		c.setStrokeStyle("#000000");
		c.setLineWidth(2);
		
	    c.beginPath();
	    c.rect(-width/2, -height/2, width, height);
	    c.closePath();
	    c.fill();
	    c.stroke();
	    
	    c.translate(-loc.x,-loc.y);
	    
		
	}

}
