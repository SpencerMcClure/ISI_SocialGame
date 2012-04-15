package com.front.client;

import com.google.gwt.canvas.dom.client.Context2d;

public class FrontConnection extends GameObject{
	
	private FrontNode node1,node2;
	final double PI = 3.14159265;
	final double PI_4 = PI/4;
	private boolean blocked;
	private boolean selected;
	private int dx, dy;
	double x0_low, y0_low, x0_high, y0_high;
	
	public FrontConnection(Context2d c, FrontNode n1, FrontNode n2, EventLog l) {
		super(c);
		this.hereIsLog(l);
		if(n1.loc.x<n2.loc.x) {
			this.node1=n1;
			this.node2=n2;
		}
		else {
			this.node1=n2;
			this.node2=n1;
		}
		type = Class.CONNECTION;
		update();
		blocked=false;
		selected=false;
		this.z = 10;
		// TODO Auto-generated constructor stub
	}

	
	


	@Override
	public void update() {
		dx = (int)(node2.loc.x-node1.loc.x);
		dy = (int) (node2.loc.y-node1.loc.y);
		
		
		if(dy>=0) 
		{
			x0_low = this.node1.loc.x-(dy/20);
			x0_high = this.node1.loc.x+(dy/20);
		}
		else
		{
			x0_low = this.node1.loc.x-(dy/20);
			x0_high =this.node1.loc.x+(dy/20);
		}
		y0_low = this.node1.loc.y+(dx/10);
		y0_high = this.node1.loc.y-(dx/10);
		
		
	}

	@Override
	public void draw() {
		c.setLineWidth(3);
		if(!blocked && !selected) {
			c.setStrokeStyle("#000000");
		}
		else if(blocked && !selected) {
			c.setStrokeStyle("#CC3333");
		}
		else {
			c.setStrokeStyle("#CCFFFF");
		}
		
			c.beginPath();
			c.moveTo(node1.loc.x, node1.loc.y);
			c.lineTo(node2.loc.x, node2.loc.y);
			c.closePath();
			c.stroke();	
			
		if(blocked) {
			
			int x_mid = (int) ((node1.loc.x+node2.loc.x)/2);
			int y_mid = (int) ((node1.loc.y+node2.loc.y)/2);
			c.clearRect(x_mid-10, y_mid-10, 20, 20);
		}
		
	}
	
	public void drawFrequency() {
		
	}
	
	
	public void setSelected(boolean s) {
		selected=s;
	}
	
	public boolean isClicked(int mousex, int mousey) {
		
		
		boolean aboveLowerBound = (dy*(mousex-x0_low)-dx*(mousey-y0_low)>0);
		boolean belowUpperBound = (dy*(mousex-x0_high)-dx*(mousey-y0_high)<0);
		
		
		
		return (aboveLowerBound && belowUpperBound && mousex>=node1.loc.x && mousex<=node2.loc.x);
			
		
		
	}
	
}
