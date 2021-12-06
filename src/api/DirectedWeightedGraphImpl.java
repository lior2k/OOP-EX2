package api;

import java.util.*;

public class DirectedWeightedGraphImpl implements DirectedWeightedGraph {
    private HashMap<Integer, NodeData> graph;
    private HashMap<MyPair, EdgeData> all_edges;
    private int node_size;
    private int MC;

    public DirectedWeightedGraphImpl() {
        graph = new HashMap<>();
        all_edges = new HashMap<>();
        node_size = 0;
        MC = 0;
    }

    @Override
    public NodeData getNode(int key) {
        return graph.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        if (graph.get(src) == null) {
            return null;
        }
        MyNode temp = (MyNode) graph.get(src);
        return temp.getEdges().get(new MyPair(src,dest));
    }

    @Override
    public void addNode(NodeData n) {
        graph.put(n.getKey(), n);
        node_size++;
        MC++;
    }

    @Override
    public void connect(int src, int dest, double w) {
        if (graph.get(src) == null || graph.get(dest) == null) {
            return;
        }
        MyEdge E = new MyEdge(src, dest, w);
        MyNode srcnode = (MyNode) graph.get(src);
        MyNode destnode = (MyNode) graph.get(dest);
        srcnode.addEdge(E);
        destnode.addEdge(E);
        all_edges.put(E.getPair(), E);
        MC++;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return graph.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return all_edges.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        MyNode n = (MyNode) graph.get(node_id);
        return n.getEdges().values().iterator();
    }

    @Override
    public NodeData removeNode(int key) {
        if (graph.get(key) == null) {
            return null;
        }

        MyNode V = (MyNode) graph.get(key);
        for (EdgeData E : V.getEdges().values()) {
            MyPair p = new MyPair(E.getSrc(), E.getDest());
            if (E.getSrc() == key) {
                MyNode src_node = (MyNode) graph.get(E.getDest());
                src_node.removeEdge(p);
            }
            if (E.getDest() == key) {
                MyNode dest_node = (MyNode) graph.get(E.getSrc());
                dest_node.removeEdge(p);
            }
            all_edges.remove(((MyEdge) E).getPair());
        }
        graph.remove(key);
        node_size--;
        MC++;
        return V;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        if (graph.get(src) == null || graph.get(dest) == null) {
            return null;
        }
        all_edges.remove(new MyPair(src,dest));
        MyNode src_node = (MyNode) graph.get(src);
        MyNode dest_node = (MyNode) graph.get(dest);
        MyEdge E = src_node.removeEdge(new MyPair(src,dest));
        dest_node.removeEdge(new MyPair(src,dest));
        MC++;
        return E;
    }

    @Override
    public int nodeSize() {
        return this.node_size;
    }

    @Override
    public int edgeSize() {
        return all_edges.size();
    }

    @Override
    public int getMC() {
        return this.MC;
    }

    public String toString() {
        return "node_size: "+node_size+", edge_size: "+all_edges.size()+", "+graph.toString();
    }

    public void printGraph() {
        System.out.println("Graph - start");
        for (NodeData n : graph.values()) {
            System.out.println(n);
            System.out.println(((MyNode) n).getEdges());
        }
        System.out.println("Graph - end");
    }
}