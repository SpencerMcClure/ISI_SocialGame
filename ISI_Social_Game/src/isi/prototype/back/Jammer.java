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

    public Jammer(Node n)
    {
        super();
        bandwidth = 1;
        nodes = new ArrayList<Node>();
        addNode(n);
    }

    public Jammer(int num, Node n) {
        super(num);
        nodes = new ArrayList<Node>();
        bandwidth = 1;
        addNode(n);
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
