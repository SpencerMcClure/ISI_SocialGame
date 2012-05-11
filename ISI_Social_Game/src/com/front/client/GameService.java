package com.front.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("gameServ")
public interface GameService extends RemoteService{
	
	boolean createGame();
}
