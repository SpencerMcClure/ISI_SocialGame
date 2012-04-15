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
	
	
	
	//FrontJammer.JammerImage img = new JammerImage();
	
	
	public FrontJammer(Context2d c, int x, int y, EventLog l)
	{
		super(c,x,y,l);
		width = size;
		height = size;
		type = Class.JAMMER;
		
		this.z = 85;
		//img.setSrc(src);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {
		c.translate(loc.x, loc.y);
		c.drawImage(img, -FrontJammer.width/2, -FrontJammer.height/2);
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
