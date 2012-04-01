package isi.prototype.shared;

import isi.prototype.back.Connection;
import isi.prototype.back.Jammer;

public interface BackEnd {
	
	public void msgJammerValueChange(Jammer j, int i);
	public void msgConnectionValueChanged(Connection c, int i);
	public void msgConnectionBlocked(Connection c);
	public void msgHereIsFrontEnd(FrontEnd f);
	
	public void gameBeginning();
	
	

}
