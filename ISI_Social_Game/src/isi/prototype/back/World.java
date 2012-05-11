package isi.prototype.back;
/**


 * The World class
 * 
 * @author Aaron Harris
 * @version 0.1
 */
import java.util.*;

public class World
{
    private int numNodes;
    private ArrayList<Node> nodes;
    private ArrayList<Link> links;
    private ArrayList<Jammer> jammers;
    private Location size;
    public static final double MAX_FREQ = 10;
    public static final double MAX_WIDTH = 1;

    public World(Location l)
    {
        nodes = new ArrayList<Node>();
        links = new ArrayList<Link>();
        jammers = new ArrayList<Jammer>();
        numNodes = 0;
        size = l;
    }

    public World(int num) {
        nodes = new ArrayList<Node>();
        links = new ArrayList<Link>();
        jammers = new ArrayList<Jammer>();
        numNodes = 0;
        addNodes(num);
        setUpLinks();
        addJammers();
        adjustLinks();
    }

    public void addNode() {
        nodes.add(new Node());
        numNodes++;
    }

    public void addNode(Node n) {
        nodes.add(n);
        numNodes++;
    }

    private void addNodes(int num) {
        for (int i = 0; i < num; i++) {
            addNode();
        }
    }

    public Node getNode(int n) { return nodes.get(n); }

    public void addJammer(Jammer j) {
        jammers.add(j);
    }

    public void addJammers() {
        for (Node n : nodes) {
            jammers.add(new Jammer(n));
        }
    }

    public boolean addLink(Node n1, Node n2) {
        Link l = new Link(n1, n2);
        if (addLink(l)) {
            n1.addLink(l);
            n2.addLink(l);
            return true;
        }
        return false;
    }

    public boolean addLink(Link l) {
        for (Link link : links) {
            if (link.equals(l)) {
                return false;
            }
        }
        links.add(l);
        return true;
    }

    public ArrayList<Jammer> getJammmers() { return jammers; }

    // Sets up a link between every single node
    private void setUpLinks() {
        for (Node n : nodes) {
            for (Node n2 : nodes) {
                if (!n.equals(n2)) {
                    //                     if (!(links.contains(new Link(n, nodes.get(i))))) {
                    Link newl = new Link(n, n2);
                    n.addLink(newl);
                    n2.addLink(newl);
                    addLink(newl);
                    //                     }
                }
            }
        }
    }

    /**
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public String toString()
    {
        String output = "";
        for (Link l : links) {
            output += l + "\n";
        }
        for (Jammer j : jammers) {
            output += j + "\n";
        }
        output += "The network " + isConnected() + "connected.";
        return output;
    }

    public void getData() {
        System.out.print(toString());
    }

    public void unblockAllLinks() {
        for (Link l : links) {
            l.unblock();
        }
    }

    // This method checks whether any of the jammers is blocking a link and adjusts the link's "blocked" setting, if necessary
    public void adjustLinks2() {
        unblockAllLinks();
        for (Jammer j : jammers) {
            for (Link l : j.getNode().getLinks()) {
                double diff = Math.abs(j.getFreq() - l.getFreq());
                if (diff <= j.getBandwidth()) {
                    l.block();
                }
            }
        }
    }

    public void adjustLinks() {
        unblockAllLinks();
        for (Jammer j : jammers) {
            for (Node n : j.getNodes()) {
                for (Link l : n.getLinks()) {
                    double diff = Math.abs(j.getFreq() - l.getFreq());
                    if (diff <= j.getBandwidth()) {
                        l.block();
                    }
                }
            }
        }
    }

    public void changeLink(int i, int freq) {
        links.get(i).setFreq(freq);
        adjustLinks2();
    }

    public void changeJammer(int i, int freq) {
        jammers.get(i).setFreq(freq);
        adjustLinks();
    }

    public String isConnected() {
        boolean[] nodeids = new boolean[numNodes];
        Node root = nodes.get(0);
        nodeids[root.getid()%numNodes] = true;
        for (Link l : root.getLinks()) {
            if (!l.isblocked()) {
                Node n = l.getOtherNode(root);
                if (!nodeids[n.getid()%numNodes]) {                    
                    nodeids[n.getid()%numNodes] = true;
                    nodeids = followNode(n, nodeids);
                }
            }
        }
        String out = "is ";
        if (Arrays.toString(nodeids).contains("f")) {
            out += "not ";
        }
        return out;
    }

    public boolean[] followNode(Node n, boolean[] ids) {
        for (Link l : n.getLinks()) {
            if (!l.isblocked()) {
                Node n2 = l.getOtherNode(n);
                if (!ids[n2.getid()%numNodes]) { 
                    ids[n2.getid()%numNodes] = true;
                    return followNode(n2, ids);
                }
            }
        }
        return ids;
    }
}