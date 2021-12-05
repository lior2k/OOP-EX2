package api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class tests {

    public static void main(String[] args) {
        DirectedWeightedGraphImpl G = new DirectedWeightedGraphImpl();
        DirectedWeightedGraphAlgorithmsImpl Algo = new DirectedWeightedGraphAlgorithmsImpl();
        Algo.load("Assignments/Ex2/data/G1.json");
        System.out.println(Algo.center().getKey());
        Algo.load("Assignments/Ex2/data/G2.json");
        System.out.println(Algo.center().getKey());
        Algo.load("Assignments/Ex2/data/G3.json");
        System.out.println(Algo.center().getKey());
        GeoLocation geo = new GeoLocationImpl(1,1,1);

        MyNode V0 = new MyNode(0, geo, 2,255);
        MyNode V1 = new MyNode(1, geo, 2,255);
        MyNode V2 = new MyNode(2, geo, 2,255);
        MyNode V3 = new MyNode(3, geo, 2,255);

        G.addNode(V0);
        G.addNode(V1);
        G.addNode(V2);
        G.addNode(V3);

        G.connect(3,0,1);
        G.connect(3,2,1);
        G.connect(3,1,1);
        G.connect(0,3,3);
        G.connect(0,2,3);
        G.connect(2,1,3);
        G.connect(1,0,3);


        Algo.init(G);
        System.out.println(Algo.center());

        DirectedWeightedGraphAlgorithmsImpl g = new DirectedWeightedGraphAlgorithmsImpl();
        g.getGraph().addNode(new MyNode(0, geo, 2, 255));
        g.getGraph().addNode(new MyNode(1, geo, 6, 255));
        g.getGraph().addNode(new MyNode(2, geo, 7, 255));
        g.getGraph().addNode(new MyNode(3, geo, 8, 255));
        g.getGraph().addNode(new MyNode(4, geo, 9, 255));
        g.getGraph().addNode(new MyNode(5, geo, 9, 255));
        g.getGraph().addNode(new MyNode(6, geo, 9, 255));
        g.getGraph().addNode(new MyNode(7, geo, 9, 255));
        g.getGraph().addNode(new MyNode(8, geo, 9, 255));
        g.getGraph().connect(4,0,0.5);
        g.getGraph().connect(4,2,4);
        g.getGraph().connect(8,4,3);
        g.getGraph().connect(8,2,20);
        g.getGraph().connect(2,5,2);
        g.getGraph().connect(5,1,7);
        g.getGraph().connect(2,3,13);
        g.getGraph().connect(3,1,9);
        g.getGraph().connect(3,7,5);
        g.getGraph().connect(7,1,0.5);
        g.getGraph().connect(1,6,1);
        g.getGraph().connect(3,6,4);
        System.out.println(g.shortestPathDist(8,6));
        System.out.println(g.center());
//
//        G1.connect(0,1,3);
//        G1.connect(1,2,15);
//        G1.connect(1,3,5);
//        G1.connect(0,4,1);
//        G1.connect(4,1,1);
//        G1.connect(3,2,5);
//        G1.connect(5,6,11);
//        G1.connect(6,7,12);
//        G1.connect(7,8,13);
//        G1.connect(8,2,14);
//
//        DirectedWeightedGraphAlgorithmsImpl Algo = new DirectedWeightedGraphAlgorithmsImpl(G1);
//        DirectedWeightedGraphImpl G3 = (DirectedWeightedGraphImpl) Algo.copy();
//        G1.printGraph();
//        G3.printGraph();
//        List<NodeData> list = new LinkedList<>();
//        list.add(V0);
//        list.add(V1);
//        list.add(V2);
//        list.add(V3);
//        List<NodeData> ans = Algo.tsp(list);
//        System.out.println(ans);
//        System.out.println(Algo.shortestPath(0,1));

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