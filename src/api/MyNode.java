package api;

import java.util.HashMap;
import java.util.Map;

public class MyNode implements NodeData {
    private final int key;
    private GeoLocationImpl location;
    private double weight;
    private int tag;
    private String info;
    private HashMap<MyPair, EdgeData> edges;
    private int discovery_time;
    private int finish_time;
    private double dist;
    private MyNode prev;

    public MyNode(int k, GeoLocation g) {
        this.key = k;
        this.location = (GeoLocationImpl) g;
        this.weight = 0;
        this.tag = 255;
        this.info = "Node: "+key+" Located at: "+location+" Weight: "+weight+" Color: "+tag;
        this.edges = new HashMap<>();
        this.discovery_time = 0;
        this.finish_time = 0;
        this.dist = 0;
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
        this.location = (GeoLocationImpl) p;
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

    public double getDist() {
        return this.dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public MyNode getPrev() {
        return this.prev;
    }

    public void setPrev(MyNode prev) {
        this.prev = prev;
    }

    public void addEdge(MyEdge E) {
        this.edges.put(E.getPair(), E);
    }

    public HashMap<MyPair, EdgeData> getEdges() {
        return this.edges;
    }

    public String toString() {
        return "Node: "+key+" Located at: "+location+" Weight: "+weight+" Color: "+tag+" D_Time: "+discovery_time+" F_Time: "+finish_time+" dist:"+this.dist;
    }

    public MyEdge removeEdge(MyPair p) {
        return (MyEdge) this.edges.remove(p);
    }

    public MyNode copy() {
        MyNode temp_node = new MyNode(this.key, this.location.copy());
        temp_node.tag = this.tag;
        temp_node.weight = this.weight;
        temp_node.discovery_time = this.discovery_time;
        temp_node.finish_time = this.finish_time;
        temp_node.prev = this.prev;
        temp_node.dist = this.dist;
        temp_node.setInfo(this.info);
        for (EdgeData E : this.edges.values()) {
            temp_node.addEdge(((MyEdge) E).copy());
        }
        return temp_node;
    }

    public MyNode reversed() {
        MyNode temp_node = new MyNode(this.key, this.location.copy());
        temp_node.tag = this.tag;
        temp_node.weight = this.weight;
        temp_node.setInfo(this.info);
        temp_node.finish_time = this.finish_time;
        temp_node.discovery_time = this.discovery_time;
        temp_node.prev = this.prev;
        temp_node.dist = this.dist;
        for (EdgeData E : this.edges.values()) {
            temp_node.addEdge(((MyEdge) E).reversedEdge());
        }
        return temp_node;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MyNode) {
            return ((MyNode) obj).getKey() == this.key;
        }
        return false;
    }


}