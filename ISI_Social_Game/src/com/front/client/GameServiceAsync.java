package com.front.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GameServiceAsync {
	void createGame(AsyncCallback<Boolean> callback);
}
