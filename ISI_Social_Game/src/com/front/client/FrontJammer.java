package com.front.client;

import com.front.client.GameObject;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.Element;

public class FrontJammer extends GameObject{
	
	static int size = 40;
	static String src = "images/jammer.png";
	
	static class JammerImage extends ImageElement{
		protected JammerImage() {
			super();
		}
	}
	
	//FrontJammer.JammerImage img = new JammerImage();
	
	
	public FrontJammer(Context2d c, int x, int y)
	{
		super(c,x,y);
		width = size;
		height = size;
		//img.setSrc(src);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {
		c.setFillStyle("#FF6666");
		c.setStrokeStyle("#660000");
		//c.rotate(45);
	    c.beginPath();
	    c.rect(this.loc.x-width/2, this.loc.y-height/2, width, height);
	    c.closePath();
	    c.fill();
	    c.stroke();
	    //c.rotate(-45);
		
	}

}
