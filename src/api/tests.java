package api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class tests {

    public static void main(String[] args) {
        GeoLocation g = new GeoLocationImpl(1,1,1);

        MyNode V0 = new MyNode(0, g, 2,255);
        MyNode V1 = new MyNode(1, g, 2,255);
        MyNode V2 = new MyNode(2, g, 2,255);
        MyNode V3 = new MyNode(3, g, 2,255);

        DirectedWeightedGraphImpl G1 = new DirectedWeightedGraphImpl();
        G1.addNode(V0);
        G1.addNode(V1);
        G1.addNode(V2);
        G1.addNode(V3);
        G1.connect(0,1,2);
        G1.connect(1,2,3);
        G1.connect(2,3,4);
        G1.connect(0,3,15);

        DirectedWeightedGraphAlgorithmsImpl Algo = new DirectedWeightedGraphAlgorithmsImpl(G1);
        Algo.Dijkstra((MyNode) G1.getNode(0));
        System.out.println(G1);
        System.out.println(Algo.shortestPath(0,3));
        System.out.println(Algo.shortestPathDist(0,3));

        Algo.save("testgraph.json");


//
//        MyNode copiednode = V0.copy();
//        System.out.println(copiednode == V0);
//        System.out.println(copiednode.equals(V0));
//        System.out.println(copiednode.getEdges().get(new MyPair(0,1)) == V0.getEdges().get(new MyPair(0,1)));
//        System.out.println(copiednode.getEdges().get(new MyPair(0,1)).equals(V0.getEdges().get(new MyPair(0,1))));
//
//        System.out.println(G1);
//        G1.printGraph();
//        G1.removeNode(0);
//        G1.removeEdge(1,2);
//        System.out.println(G1);
//        G1.printGraph();
//        System.out.println(G1.getEdge(1,2));

//        System.out.println("- - - - - -");
//        DirectedWeightedGraphImpl G2 = G1.reverseGraph();
//        G2.printGraph();

//        DirectedWeightedGraphImpl G3 = new DirectedWeightedGraphImpl();
//        MyNode V4 = new MyNode(4, g, 10, 255);
//        MyNode V5 = new MyNode(5, g, 10, 255);
//        MyNode V6 = new MyNode(6, g, 10, 255);
//        MyNode V7 = new MyNode(7, g, 10, 255);
//        G3.addNode(V0);
//        G3.addNode(V1);
//        G3.addNode(V2);
//        G3.addNode(V3);
//        G3.addNode(V4);
//        G3.addNode(V5);
//        G3.addNode(V6);
//        G3.addNode(V7);
//        G3.connect(0,1,1);
//        G3.connect(0,2,1);
//        G3.connect(1,3,1);
//        G3.connect(2,6,1);
//        G3.connect(2,7,1);
//        G3.connect(3,5,1);
//        G3.connect(4,3,1);
//        G3.connect(5,6,1);
//        G3.connect(6,4,1);
//        G3.connect(6,7,1);
//        G3.connect(7,0,1);
//        G3.connect(7,6,1);
//        DirectedWeightedGraphAlgorithmsImpl Algo = new DirectedWeightedGraphAlgorithmsImpl(G3);
//        G3.printGraph();
//        System.out.println(G3);
//        ArrayList<MyNode> arr = new ArrayList<>();
//        arr.add((MyNode) G3.getNode(1));
//        arr.add((MyNode) G3.getNode(2));
//        arr.add((MyNode) G3.getNode(0));
//        System.out.println(arr);
//        System.out.println(Algo.isConnected());


    }

}