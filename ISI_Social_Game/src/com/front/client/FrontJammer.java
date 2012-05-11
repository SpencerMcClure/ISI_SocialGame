package com.front.client;

import com.front.client.GameObject;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.ImageData;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Image;

public class FrontJammer extends GameObject{
	
	static int size = 40;
	static String src = "images/jammer.png";
	static ImageElement img;
	static int width, height;
	private int radius;
	private int frequency=20;
	
	final double PI = 3.14159265;
	
	
	
	
	//FrontJammer.JammerImage img = new JammerImage();
	
	
	public FrontJammer(Context2d c, int x, int y, EventLog l)
	{
		super(c,x,y,l);
		width = size;
		height = size;
		type = Class.JAMMER;
		radius=90;
		this.z = 85;
		//img.setSrc(src);
	}
	
	public FrontJammer(Context2d c, int x, int y,int radius, EventLog l)
	{
		super(c,x,y,l);
		width = size;
		height = size;
		type = Class.JAMMER;
		this.radius = radius;
		this.z = 85;
		//img.setSrc(src);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {
		c.setStrokeStyle("#A7A6A6");
		c.setFillStyle("#FEF3F3");
		c.setLineWidth(4);
		c.translate(loc.x, loc.y);
		c.beginPath();
		c.arc(0, 0, radius, 2*PI, 0);
		c.closePath();
		c.stroke();
		
		c.fill();
		c.drawImage(img, -FrontJammer.width/2, -FrontJammer.height/2);
		c.setFillStyle("#000000");
		c.setFont("bold 16px sans-serif");
		c.fillText(String.valueOf(frequency) , -8,5);
		c.translate(-loc.x, -loc.y);
		
	}
	
	static void addImage(Image img) {
		
		FrontJammer.img = ImageElement.as(img.getElement());
		FrontJammer.width = img.getWidth();
		FrontJammer.height = img.getHeight();
	}
	
	public boolean isClicked(int mousex, int mousey) {
		int half_width = FrontJammer.width/2;
		int half_height = FrontJammer.height/2;
		return (mousex>loc.x-half_width && mousex<(loc.x+half_width) && mousey > loc.y-half_height && mousey < (loc.y+half_height));
	}

}
