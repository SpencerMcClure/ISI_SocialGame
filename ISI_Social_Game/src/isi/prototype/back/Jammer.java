package isi.prototype.back;
 
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
        this(n, new Location(0,0));
    }
    
    public Jammer(Node[] narr, Location loc) {
        nodes = new ArrayList<Node>();
        for (Node n : narr) {
            nodes.add(n);
        }
        bandwidth = World.MAX_WIDTH;
        l = loc;
    }
    
    public Jammer(Node n, Location loc) {
        nodes = new ArrayList<Node>();
        bandwidth = World.MAX_WIDTH;
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
    
    public Node getNode(int n) { return nodes.get(n); }
    
    public ArrayList<Node> getNodes() { return nodes; }
    
    public double getBandwidth() { return bandwidth; }

    /**
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public String toString()
    {
        String out = "The jammer of ";
        for (Node n : nodes) {
            if (nodes.size() <= 1) {
                out += n;
            } else {
                out += n + ", ";
            }
        }
        out += "has a frequency of " + getFreq();
        return out;
    }
}
