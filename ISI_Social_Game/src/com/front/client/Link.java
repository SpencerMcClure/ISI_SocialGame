package com.front.client;

/**
 * The Link class
 * 
 * @author Aaron Harris
 * @version 0.1
 */

import java.io.*;
import java.util.*;

public class Link extends Freq
{
    private boolean blocked;
    private Node node1;
    private Node node2;

    public Link(int num, Node n1, Node n2)
    {
        super(num);
        if (n2.getid() < n1.getid()) {
            node1 = n2;
            node2 = n1;
        } else if (n1.getid() < n2.getid()) {
            node1 = n1;
            node2 = n2;
        } else {
            throw new IllegalArgumentException();
        }
        blocked = false;
    }

    public Link(Node n1, Node n2)
    {
        super();
        if (n2.getid() < n1.getid()) {
            node1 = n2;
            node2 = n1;
        } else if (n1.getid() < n2.getid()) {
            node1 = n1;
            node2 = n2;
        } else {
            throw new IllegalArgumentException();
        }
        blocked = false;
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }
    // @param: n must be one of the nodes of the link, either Node1 or Node2
    public Node getOtherNode(Node n) {
        if (n.getid() == getNode1().getid()) {
            return getNode2();
        } else if (n.getid() == getNode2().getid()) {
            return getNode1();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public boolean equals(Link l) {
        return (getNode1() == l.getNode1() || getNode1() == l.getNode2()) && (getNode2() == l.getNode2() || getNode2() == l.getNode1());
    }

    public String toString() {
        return "Link between " + getNode1() + " and " + getNode2() + " has frequency " + getFreq() + " and blocked=" + blocked;
    }

    public void block() { blocked = true; }

    public void unblock() { blocked = false; }

    public boolean isblocked() { return blocked; }
}
