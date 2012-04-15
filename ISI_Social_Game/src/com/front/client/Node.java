
package com.front.client;
/**
 * The Node class
 * 
 * @author Aaron Harris
 * @version 0.1
 */

import java.util.*;

public class Node
{
    private static int nodeNum = 0;
    private ArrayList<Link> links;
    private int id;
    private int numLinks;

    public Node()
    {
        links = new ArrayList<Link>();
        id = nodeNum;
        nodeNum++;
        numLinks = 0;
    }

    public boolean addLink(Link l) {
        for (Link link : links) {
            if (link.equals(l)) {
                return false;
            }
        }
        links.add(l);
        numLinks++;
        return true;
    }


    public Link getLink(int n) {
        return links.get(n);
    }

    public int getid() { return id;}

    public ArrayList<Link> getLinks() {
        return links;
    }

    //    public boolean equals(Node n) {
    //        return links.containsAll(n.links);
    //    }

    /**
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public String toString()
    {
        return "node_id=" + id;
    }
}
