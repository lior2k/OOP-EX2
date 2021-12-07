package api;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class tests {
    public static void main(String[] args) {

        DirectedWeightedGraphAlgoImpl Algo = new DirectedWeightedGraphAlgoImpl();
        Algo.load("data/G1.json");
        System.out.println(Algo.center());
        Algo.load("data/G2.json");
        System.out.println(Algo.center());
        Algo.load("data/G3.json");
        System.out.println(Algo.center());
        Algo.load("data/1000Nodes.json");
        System.out.println(Algo.center());
//        DirectedWeightedGraph g = new DirectedWeightedGraphImpl();
//        GeoLocation l = new GeoLocationImpl(0,0,0);
//        MyNode n0 = new MyNode(0,l,0);
//        MyNode n1 = new MyNode(1,l,0);
//        MyNode n2 = new MyNode(2,l,0);
//        MyNode n3 = new MyNode(3,l,0);
//        MyNode n4 = new MyNode(4,l,0);
//        MyNode n5 = new MyNode(5,l,0);
//        MyNode n6 = new MyNode(6,l,0);
//        MyNode n7 = new MyNode(7,l,0);
//        MyNode n8 = new MyNode(8,l,0);
//        g.addNode(n0);
//        g.addNode(n1);
//        g.addNode(n2);
//        g.addNode(n3);
//        g.addNode(n4);
//        g.addNode(n5);
//        g.addNode(n6);
//        g.addNode(n7);
//        g.addNode(n8);
//        g.connect(4,0,0.5);
//        g.connect(4,2,4);
//        g.connect(8,4,3);
//        g.connect(8,2,20);
//        g.connect(2,5,2);
//        g.connect(5,1,7);
//        g.connect(2,3,13);
//        g.connect(3,1,9);
//        g.connect(3,7,5);
//        g.connect(7,1,0.5);
//        g.connect(1,6,1);
//        g.connect(3,6,4);
//
//
//
//
//        DirectedWeightedGraphAlgoImpl algo = new DirectedWeightedGraphAlgoImpl();
//        algo.init(g);
//        System.out.println(algo.shortestPathDist(8,6));
//        System.out.println(algo.isConnected());
//        System.out.println(algo.center());
//        System.out.println(algo.getGraph());
//
//
//
//        Iterator<NodeData> it = g.nodeIter();
//        Iterator<EdgeData> it2 = g.edgeIter();
////        Iterator<EdgeData> it3 = g.edgeIter(1);
//
//
//        while(it.hasNext()){
//            MyNode node = (MyNode)it.next();
//            System.out.println(node);
//
//        }
//        System.out.println();
//
//        while(it2.hasNext()) {
//            MyEdge e = (MyEdge)it2.next();
//            System.out.println(e);
//        }
//        System.out.println();

//        while(it3.hasNext()) {
//            MyEdge e = (MyEdge)it3.next();
//            System.out.println(e);
//        }


    }
}
