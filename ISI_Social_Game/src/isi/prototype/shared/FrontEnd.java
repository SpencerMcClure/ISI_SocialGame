package isi.prototype.shared;

import isi.prototype.back.Connection;
import isi.prototype.back.Jammer;
import isi.prototype.back.Node;

import java.util.List;

public interface FrontEnd {

	
	//Told by back-end when end conditions have been met
	public void msgGameOver();
	
	//Informed of nodes, jammers and connections by back-end
	//Will choose pre-determined layout to display
	public void msgHereIsLayout(List<Node> nodes, List<Jammer> jammers, List<Connection> connections);

}
