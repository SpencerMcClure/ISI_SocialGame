package com.front.client;

import com.google.gwt.canvas.dom.client.Context2d;

public class FrontNode extends GameObject{

	static int size = 35;
	public FrontNode(Context2d c) {
		super(c,0,0);
		width = size;
		height = size;
	}
	public FrontNode(Context2d c, int x, int y) {
		super(c,x,y);
		width = size;
		height = size;
	}

	@Override
	public void update() {
		
		
	}

	@Override
	public void draw() {
		c.setFillStyle("#CCFFFF");
		c.setStrokeStyle("#000000");
		c.rotate(3.14);
	    c.beginPath();
	    c.rect(this.loc.x-width/2, this.loc.y-height/2, width, height);
	    c.closePath();
	    c.fill();
	    c.stroke();
		
	}

}
