package com.front.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class EventLog {
	
		RootPanel log;
		
		private class Msg extends HTML
		{
			public Msg(String msg) {
				super("<p>" + msg + "</p>");
			}
		}
		
	    public EventLog(RootPanel r) {
	      this.log = r;
	    }
	    
	    public void addText(String msg) {
	    	log.add(new Label(msg));
	    }

		
	  
	
}
