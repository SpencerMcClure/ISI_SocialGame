package isi.prototype.front;

import java.util.ArrayList;
import java.util.List;

import com.front.client.FrontConnection;
import com.front.client.FrontJammer;
import com.front.client.FrontNode;
import com.front.client.Vector;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;


import isi.prototype.back.Connection;
import isi.prototype.back.Jammer;
import isi.prototype.back.Node;
import isi.prototype.shared.FrontEnd;

public class MainFrame implements FrontEnd, EntryPoint, KeyPressHandler, MouseDownHandler{

	
	
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";
	
	static final String holderId = "canvasholder";

	static final String upgradeMessage = "Your browser does not support the HTML5 Canvas. Please upgrade your browser to view this demo.";
	//timer refresh rate, in milliseconds
	static final int refreshRate = 25;
	
	public ArrayList<FrontNode> nodes;
	public ArrayList<FrontJammer> jammers;
	public ArrayList<FrontConnection> connections;
	
	public final int width = 800;
	public final int height = 600;
	private Vector camera;
	
	public Context2d context;
	public Canvas canvas;
	FrontNode test;
	
	

	public void onModuleLoad() {
		init();
		
	}
	
	public void init() {
		canvas = Canvas.createIfSupported();
		
		if ( canvas == null) {
			RootPanel.get(null).add(new Label(upgradeMessage));
		}
		
		canvas.setWidth(width +"px");
		canvas.setHeight(height+"px");
		canvas.setCoordinateSpaceWidth(width);
		canvas.setCoordinateSpaceHeight(height);
		RootPanel.get(null).add(canvas);
		context = canvas.getContext2d();
		
		
		final Timer timer = new Timer() {
			public void run() {
				doUpdate();
			}
		};
		//timer.scheduleRepeating(refreshRate);
		canvas.addKeyPressHandler(this);
		canvas.addMouseDownHandler(this);
		
		test = new FrontNode(context, 50,50);
	}

	protected void doUpdate() {
		context.setFillStyle("#ffffff");
		context.rect(0, 0, width, height);
		context.fill();
		test.draw();
		context.beginPath();
		context.setLineWidth(2);
		context.setStrokeStyle("gray");
	    context.arc(200, 200, 45, 0, 6.287);
	    context.closePath();
	    context.stroke();
		
	}

	public void onMouseDown(MouseDownEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void msgGameOver() {
		// TODO Auto-generated method stub
		
	}

	public void msgHereIsLayout(List<Node> nodes, List<Jammer> jammers,
			List<Connection> connections) {
		// TODO Auto-generated method stub
		
	}

	public void onKeyPress(KeyPressEvent event) {
		// TODO Auto-generated method stub
		
	}

}
