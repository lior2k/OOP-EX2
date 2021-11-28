package api;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyNode implements NodeData {
    private int key;
    private GeoLocation location;
    private double weight;
    private int tag;
    private String info;
    private HashMap<MyPair, MyEdge> edges;
    private int in_degree;
    private int out_degree;
    private int discovery_time;
    private int finish_time;

    public MyNode(int k, GeoLocation g, double w, int tag) {
        this.key = k;
        this.location = g;
        this.weight = w;
        this.tag = tag;
        this.info = "Node: "+key+" Located at: "+location+" Weight: "+weight+" Color: "+tag+" in_deg: "+in_degree+" out_deg: "+out_degree;
        this.edges = new HashMap<>();
        this.in_degree = 0;
        this.out_degree = 0;
        this.discovery_time = 0;
        this.finish_time = 0;
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public GeoLocation getLocation() {
        return this.location;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.location = p;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {

    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }

    public int getDegree() {
        return this.in_degree+this.out_degree;
    }

    public int getDiscovery_time() {
        return  this.discovery_time;
    }

    public int getFinish_time() {
        return this.finish_time;
    }

    public void setDiscovery_time(int time) {
        this.discovery_time = time;
    }

    public void setFinish_time(int time) {
        this.finish_time = time;
    }

    public void addEdge(MyEdge E) {
        if (E.getSrc() == this.key) {
            this.out_degree++;
        } else {
            this.in_degree++;
        }
        this.edges.put(E.getPair(), E);
    }

    public HashMap<MyPair, MyEdge> getEdges() {
        return this.edges;
    }

    public String toString() {
        return "Node: "+key+" Located at: "+location+" Weight: "+weight+" Color: "+tag+" in_deg: "+in_degree+"" +
                " out_deg: "+out_degree+" D_Time: "+discovery_time+" F_Time: "+finish_time;
    }

    public MyEdge removeEdge(MyPair p) {
        MyEdge E = this.edges.remove(p);
        if (E.getSrc() == this.key) {
            this.out_degree--;
        } else {
            this.in_degree--;
        }
        return E;
    }

    public MyNode copy() {
        MyNode temp_node = new MyNode(this.key, this.location, this.weight, this.tag);
        temp_node.in_degree = this.in_degree;
        temp_node.out_degree = this.out_degree;
        temp_node.setInfo(this.info);
        for (Map.Entry<MyPair, MyEdge> me : this.edges.entrySet()) {
            MyEdge temp_edge = me.getValue().copy();
            temp_node.addEdge(temp_edge);
        }
        return temp_node;
    }

    public MyNode reversed() {
        MyNode temp_node = new MyNode(this.key, this.location, this.weight, this.tag);
        temp_node.in_degree = this.in_degree;
        temp_node.out_degree = this.out_degree;
        temp_node.setInfo(this.info);
        temp_node.finish_time = this.finish_time;
        temp_node.discovery_time = this.discovery_time;
        for (Map.Entry<MyPair, MyEdge> me : this.edges.entrySet()) {
            MyEdge temp_edge = me.getValue().reversedEdge();
            temp_node.addEdge(temp_edge);
        }
        return temp_node;
    }

}
