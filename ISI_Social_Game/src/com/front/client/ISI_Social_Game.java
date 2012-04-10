package com.front.client;

import isi.prototype.back.Connection;
import isi.prototype.back.Jammer;
import isi.prototype.back.Node;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ISI_Social_Game implements EntryPoint, MouseDownHandler {
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
	public Label lastCoords;
	

	public void onModuleLoad() {
		init();
		
	}
	
	public void init() {
		canvas = Canvas.createIfSupported();
		
		if ( canvas == null) {
			RootPanel.get(null).add(new Label(upgradeMessage));
		}
		nodes = new ArrayList<FrontNode>();
		jammers = new ArrayList<FrontJammer>();
		connections = new ArrayList<FrontConnection>();
		
		canvas.setWidth(width +"px");
		canvas.setHeight(height+"px");
		canvas.setCoordinateSpaceWidth(width);
		canvas.setCoordinateSpaceHeight(height);
		RootPanel.get(holderId).add(canvas);
		context = canvas.getContext2d();
		
		
		setUpNodes();
		
		lastCoords = new Label("nothing");
		RootPanel.get("coords").add(lastCoords);
		final Timer timer = new Timer() {
			public void run() {
				doUpdate();
			}
		};
		//timer.scheduleRepeating(refreshRate);
		timer.run();
		canvas.addMouseDownHandler(this);
		
		
	}
	
	// This is important to use a handler!
	private void loadImages() {
	    final Image img = new Image("/images/jammer.png");
	    RootPanel.get().add(img);
	    img.setVisible(false);
	    
	    img.addLoadHandler(new LoadHandler() {
	      public void onLoad(LoadEvent event) {
	        
	      }
	    });
	}

	
	public void setUpNodes()
	{
		FrontNode n1 = new FrontNode(context, 200,60);
		FrontNode n2 = new FrontNode(context, 700,200);
		FrontNode n3 = new FrontNode(context, 400,450);
		nodes.add(n1); nodes.add(n2); nodes.add(n3);
		
		FrontJammer fj1 = new FrontJammer(context, 170,300);
		jammers.add(fj1);
	}

	protected void doUpdate() {
		context.setFillStyle("#ffffff");
		context.rect(0, 0, width, height);
		context.fill();
		this.drawObjects();
		jammers.get(0).draw();
		
	}
	
	public void drawObjects() {
		for(FrontNode n:nodes) {
			n.draw();
		}
	}

	public void onMouseDown(MouseDownEvent event) {
		Element canvasElement = (Element) RootPanel.get(holderId).getElement().getFirstChildElement();
		int relativex = event.getRelativeX(canvasElement);
		int relativey = event.getRelativeY(canvasElement);
		Vector mouseCoords = new Vector(relativex, relativey);
		lastCoords.setText(relativex+", "+relativey);
		
		
	}
	
	public GameObject objectUnderMouse(int x, int y) {
		return null;
	}
	
	
	public void msgGameOver() {
		// TODO Auto-generated method stub
		
	}

//	public void msgHereIsLayout(List<Node> nodes, List<Jammer> jammers,
//			List<Connection> connections) {
//		// TODO Auto-generated method stub
//		
//	}

	
}
