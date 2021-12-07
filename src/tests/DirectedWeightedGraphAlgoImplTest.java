package tests;

import api.DirectedWeightedGraphAlgoImpl;
import api.NodeData;

import static org.junit.jupiter.api.Assertions.*;

class DirectedWeightedGraphAlgoImplTest {
    DirectedWeightedGraphAlgoImpl Algo = new DirectedWeightedGraphAlgoImpl();
    String st = "Ex2/data/1000Nodes.json"; //center = node num 362
    String st2 = "Ex2/data/10000Nodes.json"; //center = node num 3846
    String st3 = "Ex2/data/G3.json";

    @org.junit.jupiter.api.Test
    void init() {
    }

    @org.junit.jupiter.api.Test
    void getGraph() {
    }

    @org.junit.jupiter.api.Test
    void copy() {
    }

    @org.junit.jupiter.api.Test
    void isConnected() {
        Algo.load(st);
        assertTrue(Algo.isConnected());
        Algo.load(st2);
        assertTrue(Algo.isConnected());
    }

    @org.junit.jupiter.api.Test
    void shortestPathDist() {
        Algo.load(st);
        double ans = Algo.shortestPathDist(362,384);
        assertEquals(ans, 605.0049493760619);
        Algo.load(st2);
        ans = Algo.shortestPathDist(362,3840);
        assertEquals(ans, 1093.542322166061);
    }

    @org.junit.jupiter.api.Test
    void shortestPath() {
    }

    @org.junit.jupiter.api.Test
    void center() {
        Algo.load(st);
        NodeData n = Algo.getGraph().getNode(362);
        NodeData v = Algo.center();
        assertEquals(n, v);
//        Algo.load(st2);
//        n = Algo.getGraph().getNode(3846);
//        v = Algo.center();
//        assertEquals(n, v);
    }

    @org.junit.jupiter.api.Test
    void tsp() {
    }

    @org.junit.jupiter.api.Test
    void save() {
    }

    @org.junit.jupiter.api.Test
    void load() {
    }
}