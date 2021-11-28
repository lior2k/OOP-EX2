package api;

import javax.swing.text.html.HTMLDocument;
import java.lang.reflect.Array;
import java.util.*;

public class DirectedWeightedGraphImpl implements DirectedWeightedGraph {
    private HashMap<Integer, MyNode> graph;
    private int node_size;
    private int edge_size;
    private int MC;

    public DirectedWeightedGraphImpl() {
        graph = new HashMap<>();
        node_size = 0;
        edge_size = 0;
        MC = 0;
    }

    @Override
    public NodeData getNode(int key) {
        return graph.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return graph.get(src).getEdges().get(new MyPair(src,dest));
    }

    @Override
    public void addNode(NodeData n) {
        graph.put(n.getKey(), (MyNode) n);
        node_size++;
        MC++;
    }

    @Override
    public void connect(int src, int dest, double w) {
        EdgeData E = new MyEdge(src, dest, 255, w);
        graph.get(src).addEdge((MyEdge) E);
        graph.get(dest).addEdge((MyEdge) E);
        edge_size++;
        MC++;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        int temp = MC;
        ArrayList<NodeData> arr = new ArrayList<>();
        for (Map.Entry<Integer, MyNode> me : graph.entrySet()) {
            arr.add(me.getValue());
        }
        return arr.iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        ArrayList<EdgeData> arr = new ArrayList<>();
        Iterator<NodeData> iter = this.nodeIter();
        while (iter.hasNext()) {
            MyNode temp = (MyNode) iter.next();
            for (Map.Entry<MyPair, MyEdge> ME : temp.getEdges().entrySet()) {
                arr.add(ME.getValue());
            }
        }
        return arr.iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        ArrayList<EdgeData> arr = new ArrayList<>();
        MyNode temp = graph.get(node_id);
        for (Map.Entry<MyPair, MyEdge> ME : temp.getEdges().entrySet()) {
            arr.add(ME.getValue());
        }
        return arr.iterator();
    }

    @Override
    public NodeData removeNode(int key) {
        for (Map.Entry<MyPair, MyEdge> E: graph.get(key).getEdges().entrySet()) {
            if (E.getValue().getSrc() == key) {
                graph.get(E.getValue().getDest()).removeEdge(new MyPair(E.getValue().getSrc(), E.getValue().getDest()));
            }
            if (E.getValue().getDest() == key) {
                graph.get(E.getValue().getSrc()).removeEdge(new MyPair(E.getValue().getSrc(), E.getValue().getDest()));
            }
        }
        MyNode V = graph.remove(key);
        edge_size = edge_size - V.getDegree();
        node_size--;
        MC++;
        return V;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        MyEdge E = graph.get(src).removeEdge(new MyPair(src,dest));
        graph.get(dest).removeEdge(new MyPair(src,dest));
        edge_size--;
        MC++;
        return E;
    }

    @Override
    public int nodeSize() {
        return this.node_size;
    }

    @Override
    public int edgeSize() {
        return this.edge_size;
    }

    @Override
    public int getMC() {
        return this.MC;
    }

    public String toString() {
        String st = "node_size: "+node_size+", edge_size: "+edge_size+", "+graph.toString();
        return st;
    }

    public void printGraph() {
        System.out.println("Graph - start");
        for (Map.Entry<Integer, MyNode> n : graph.entrySet()) {
            System.out.print(n.getValue().getKey());
            System.out.println(n.getValue().getEdges());
        }
        System.out.println("Graph - end");
    }

    public HashMap<Integer, MyNode> getMap() {
        return this.graph;
    }


    public DirectedWeightedGraphImpl reverseGraph() {
        DirectedWeightedGraphImpl reversedGraph = new DirectedWeightedGraphImpl();
        for (Map.Entry<Integer, MyNode> ME : this.getMap().entrySet()) {
            MyNode temp_node = ME.getValue().reversed();
            reversedGraph.addNode(temp_node);
        }
        return reversedGraph;
    }
}
