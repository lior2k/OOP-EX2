package api;

import java.util.ArrayList;
import java.util.Iterator;

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
        G1.connect(0,1,10);
        G1.connect(1,2,11);
        G1.connect(2,0,12);
        G1.connect(0,3,13);
        G1.connect(3,0,14);

        MyNode copiednode = V0.copy();
        System.out.println(copiednode == V0);
        System.out.println(copiednode.equals(V0));
        System.out.println(copiednode.getEdges().get(new MyPair(0,1)) == V0.getEdges().get(new MyPair(0,1)));
        System.out.println(copiednode.getEdges().get(new MyPair(0,1)).equals(V0.getEdges().get(new MyPair(0,1))));

        System.out.println(G1);
        G1.printGraph();
//        G1.removeNode(0);
//        G1.removeEdge(1,2);
//        System.out.println(G1);
//        G1.printGraph();
//        System.out.println(G1.getEdge(1,2));

        System.out.println("- - - - - -");
        DirectedWeightedGraphImpl G2 = G1.reverseGraph();
        G2.printGraph();

        DirectedWeightedGraphImpl G3 = new DirectedWeightedGraphImpl();
        MyNode V10 = new MyNode(0, g, 10, 255);
        MyNode V11 = new MyNode(1, g, 10, 255);
        MyNode V12 = new MyNode(2, g, 10, 255);
        G3.addNode(V10);
        G3.addNode(V11);
        G3.addNode(V12);
        G3.connect(0,1,10);
        G3.connect(1,2,10);
        G3.connect(2,0,10);
        DirectedWeightedGraphAlgorithmsImpl Algo = new DirectedWeightedGraphAlgorithmsImpl(G3);
        G3.printGraph();
        Algo.DFS();
        System.out.println(G3);
//        ArrayList<MyNode> arr = new ArrayList<>();
//        arr.add((MyNode) G3.getNode(1));
//        arr.add((MyNode) G3.getNode(2));
//        arr.add((MyNode) G3.getNode(0));
//        sortByFinishTime(arr);
//        System.out.println(arr);
        System.out.println(Algo.isConnected());


    }

    public static void sortByFinishTime(ArrayList<MyNode> arr) {
        for (int i = 0; i < arr.size()-1; i++) {
            for (int j = 0; j < arr.size()-i-1; j++) {
                if (arr.get(i).getFinish_time() < arr.get(j).getFinish_time()) {
                    swap(arr,i,j);
                }
            }
        }
    }

    public static void swap(ArrayList<MyNode> arr, int i, int j) {
        MyNode temp = arr.get(j);
        arr.add(j, arr.get(i));
        arr.add(i, temp);
    }
}
