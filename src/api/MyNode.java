package api;

import java.util.HashMap;
import java.util.Iterator;

public class MyNode implements NodeData {
    private int key;
    private GeoLocation location;
    private double weight;
    private int tag;
    private String info;
    private HashMap<MyPair, MyEdge> edges;
    private int in_degree;
    private int out_degree;

    public MyNode(int k, GeoLocation g, double w, int tag) {
        this.key = k;
        this.location = g;
        this.weight = w;
        this.tag = tag;
        this.info = "Node: "+key+" Located at: "+location+" Weight: "+weight+" Color: "+tag+" in_deg: "+in_degree+" out_deg: "+out_degree;
        this.edges = new HashMap<>();
        this.in_degree = 0;
        this.out_degree = 0;
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
        return "Node: "+key+" Located at: "+location+" Weight: "+weight+" Color: "+tag+" in_deg: "+in_degree+" out_deg: "+out_degree;
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

    public int getDegree() {
        return this.in_degree+this.out_degree;
    }

}
