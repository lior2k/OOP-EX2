package api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class tests {

    public static void main(String[] args) {
        DirectedWeightedGraphAlgorithms Algo = new DirectedWeightedGraphAlgorithmsImpl();
        Algo.load("Assignments/Ex2/data/G1.json");
        DirectedWeightedGraphImpl G = (DirectedWeightedGraphImpl) Algo.getGraph();
        G.printGraph();
        G.removeNode(0);
        System.out.println();
        G.printGraph();
    }

}