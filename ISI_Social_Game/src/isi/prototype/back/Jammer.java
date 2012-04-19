 
/**

 * The Jammer class
 * 
 * @author Aaron Harris
 * @version 0.1
 */

import java.io.*;
import java.util.*;

public class Jammer extends Freq
{
    private double radius;
    private double bandwidth;
    private ArrayList<Node> nodes;
    private Location l;

    public Jammer(Node n)
    {
        this((int) (Math.random() * World.MAX_FREQ), n, new Location(0,0));
    }

    public Jammer(int num, Node n) {
        this(num, n, new Location(0,0));
    }
    
    public Jammer(int num, Node n, Location loc) {
        super(num);
        nodes = new ArrayList<Node>();
        bandwidth = 1;
        addNode(n);
        l = loc;
    }

    public boolean addNode(Node n) {
        for (Node node : nodes) {
            if (node.equals(n)) {
                return false;
            }
        }
        nodes.add(n);
        return true;
    }
    
    public Node getNode() {
        return nodes.get(0);
    }
    
    public double getBandwidth() { return bandwidth; }

    /**
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public String toString()
    {
        return "The jammer of " + getNode() + " has a frequency of " + getFreq();
    }
}
