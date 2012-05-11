package isi.prototype.back;

import java.util.*;
import java.io.*;

public class Game
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("What number do you want to set node1 to: ");
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input == "") {
                sc.close();
                return;
            }
            System.out.println("You inputted " + input);
        }
    }
    
    public static World level1(int n) {
        return new World(n);
    }
    
    public static World level2() {
        World w = new World(new Location(500,500));
        Node n1 = new Node(new Location(150,150));
        Node n2 = new Node(new Location(150,350));
        Node n3 = new Node(new Location(300,250));
        Node n4 = new Node(new Location(400,100));
        Node n5 = new Node(new Location(400,400));
        w.addNode(n1);
        w.addNode(n2);
        w.addNode(n3);
        w.addNode(n4);
        w.addNode(n5);
        w.addLink(n1,n3);
        w.addLink(n2,n3);
        w.addLink(n4,n3);
        w.addLink(n5,n3);
        w.addLink(n1,n4);
        w.addLink(n2,n5);
        Node[] jnodes = {n1, n2};
        Jammer j1 = new Jammer(jnodes, new Location(150, 250));
        w.addJammer(j1);
        jnodes[0] = n3; jnodes[1] = n5;
        Jammer j2 = new Jammer(jnodes, new Location(350, 300));
        w.addJammer(j2);
        jnodes[0] = n3; jnodes[1] = n4;
        Jammer j3 = new Jammer(jnodes, new Location(350,200));
        w.addJammer(j3);
        w.adjustLinks();
        return w;
    }
}
