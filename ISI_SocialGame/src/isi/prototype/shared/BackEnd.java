package isi.prototype.shared;

import isi.prototype.back.Connection;
import isi.prototype.back.Jammer;

public interface BackEnd {
	
	//Will be told by FrontEnd when values change
	//This is really only of use to the backend for maintaining up-to-date states
	//And for collecting data with regards to how the game is played by users
	public void msgJammerValueChange(Jammer j, int i);
	public void msgConnectionValueChanged(Connection c, int i);
	
	public void msgHereIsFrontEnd(FrontEnd f);
	
	public void gameBeginning();
	
	

}
