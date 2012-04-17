package com.front.client;



import com.front.client.GameObject.Class;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.ImageData;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
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
	static final int refreshRate = 1000;
	
	public ArrayList<FrontNode> nodes;
	public ArrayList<FrontJammer> jammers;
	public ArrayList<FrontConnection> connections;
	public ArrayList<GameObject> gameObjects;
	
	public final int width = 800;
	public final int height = 600;
	private Vector camera;
	
	public Context2d context;
	public Context2d contextBuff;
	public Canvas canvas;
	public Canvas canvasBuff;
	FrontNode test;
	public Label lastCoords;
	boolean imagesLoaded=false;
	EventLog log;
	
	World w;
	
	
	private static class ValueDialog extends DialogBox{
		
		FrontConnection c;
		FrontJammer j;
		TextBox t;
		public ValueDialog(GameObject gameobj) {
			if(gameobj.type==Class.CONNECTION)
			{
				c = (FrontConnection)gameobj;
			}
			else if(gameobj.type==Class.JAMMER) 
			{
				j = (FrontJammer)gameobj;
			}
			setText("New Value?");
			t = new TextBox();
			
			
			Timer timer = new Timer() {
				public void run() {
					setFocus();
				}
			};
			timer.schedule(50);
			t.addKeyPressHandler(new KeyPressHandler() {
				public void onKeyPress(KeyPressEvent e) {
					if(e.getCharCode()==13) {
						ValueDialog.this.hide();
						if(c!=null)
							c.setSelected(false);
						
					}
				}
			});
			
			setWidget(t);
			t.setFocus(true);
			
		}
		
		public void setFocus() {
			t.setFocus(true);
		}
	}

	public void onModuleLoad() {
		init();
		
	}
	
	public void init() {
		canvas = Canvas.createIfSupported();
		canvasBuff = Canvas.createIfSupported();
		if ( canvas == null) {
			RootPanel.get(null).add(new Label(upgradeMessage));
		}
		nodes = new ArrayList<FrontNode>();
		jammers = new ArrayList<FrontJammer>();
		connections = new ArrayList<FrontConnection>();
		gameObjects = new ArrayList<GameObject>();
		
		
		canvas.setWidth(width +"px");
		canvas.setHeight(height+"px");
		canvas.setCoordinateSpaceWidth(width);
		canvas.setCoordinateSpaceHeight(height);
		canvasBuff.setCoordinateSpaceWidth(width);
		canvasBuff.setCoordinateSpaceHeight(height);
		RootPanel.get(holderId).add(canvas);
		context = canvas.getContext2d();
		contextBuff = canvasBuff.getContext2d();
		loadImages();
		
		//w = new World();
		
		lastCoords = new Label("nothing");
		RootPanel.get("coords").add(lastCoords);
		log = new EventLog(RootPanel.get("output"));
		setUpNodes();
		
		final Timer timer = new Timer() {
			public void run() {
				if(imagesLoaded)
				doUpdate();
			}
		};
		
		timer.scheduleRepeating(50);
		canvas.addMouseDownHandler(this);
		
		
	}
	
	// This is important to use a handler!
	private void loadImages()  {
	    final Image jammerImg = new Image(FrontJammer.src);
	    RootPanel.get().add(jammerImg);
	    jammerImg.setVisible(false);
	    
	    jammerImg.addLoadHandler(new LoadHandler() {
	      public void onLoad(LoadEvent event) {
	        FrontJammer.addImage(jammerImg);
	        imagesLoaded=true;
	        //doUpdate();
	      }
	    });
	}
	
	
	public void setUpNodes()
	{
		FrontNode n1 = new FrontNode(contextBuff, 200,60,log);
		FrontNode n2 = new FrontNode(contextBuff, 600,200,log);
		FrontNode n3 = new FrontNode(contextBuff, 400,450,log);
		gameObjects.add(n1); gameObjects.add(n2); gameObjects.add(n3);
		nodes.add(n1); nodes.add(n2); nodes.add(n3);
		
		
		FrontConnection c1 = new FrontConnection(contextBuff, n1,n2,log);
		FrontConnection c2 = new FrontConnection(contextBuff, n2,n3,log);
		gameObjects.add(c1); gameObjects.add(c2);
		connections.add(c1); connections.add(c2);
		
		FrontJammer fj1 = new FrontJammer(contextBuff, 170,300,log);
		gameObjects.add(fj1);
		jammers.add(fj1);
		
	}

	protected void doUpdate() {
		contextBuff.setFillStyle("#ffffff");
		contextBuff.rect(0, 0, width, height);
		contextBuff.fill();
		this.drawObjects();
		
		context.putImageData(contextBuff.getImageData(0,0,width,height), 0, 0);
	}
	
	public void drawObjects() {
		for(FrontConnection c:connections) {
			c.draw();
		}
		
		for(FrontNode n:nodes) {
			n.draw();
		}
		
		for(FrontJammer j: jammers) {
			j.draw();
		}
		
		
	}

	public void onMouseDown(MouseDownEvent event) {
		Element canvasElement = (Element) RootPanel.get(holderId).getElement().getFirstChildElement();
		int relativex = event.getRelativeX(canvasElement);
		int relativey = event.getRelativeY(canvasElement);
		Vector mouseCoords = new Vector(relativex, relativey);
		Class t;
		GameObject hit;
		
		if((hit = objectUnderMouse(relativex,relativey))!=null) {
			t= hit.type;
			switch(t) {
				case CONNECTION:
					FrontConnection c = (FrontConnection)hit;
					c.setSelected(true);
					ValueDialog d =new ValueDialog(c);
					d.setPopupPosition(event.getClientX(), event.getClientY());
					d.show();
					canvas.setFocus(false);
					((TextBox)(d.getWidget())).setFocus(true);
					break;
					
				case JAMMER:
					
					break;
					
				default:
					
					break;
					
					
			}
		}
		else
			lastCoords.setText(relativex+", "+relativey);
		
		
	}
	
	public GameObject objectUnderMouse(int x, int y) {
		GameObject found =null;
		for(GameObject go : gameObjects) {
			if(go.isClicked(x, y))
				return go;
		}
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
