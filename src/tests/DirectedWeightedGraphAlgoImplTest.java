package tests;

import api.*;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DirectedWeightedGraphAlgoImplTest {
    DirectedWeightedGraphAlgoImpl Algo = new DirectedWeightedGraphAlgoImpl();
    String st = "data/1000Nodes.json"; //center = node num 362
    String st2 = "data/10000Nodes.json"; //center = node num 3846
    String G3 = "data/G3.json";
    String G2 = "data/G2.json";
    String G1 = "data/G1.json";

    @org.junit.jupiter.api.Test
    void init() {
        Algo.load(G3);
        DirectedWeightedGraphAlgoImpl Algo2 = new DirectedWeightedGraphAlgoImpl();
        Algo2.init(Algo.getGraph());
        assertEquals(Algo.getGraph(), Algo2.getGraph());

    }

    @org.junit.jupiter.api.Test
    void getGraph() {
        DirectedWeightedGraphImpl G1 = new DirectedWeightedGraphImpl();
        Algo.init(G1);
        assertEquals(G1, Algo.getGraph());
    }

    @org.junit.jupiter.api.Test
    void copy() {
        DirectedWeightedGraphImpl G1 = new DirectedWeightedGraphImpl();
        MyNode n = new MyNode(0, new GeoLocationImpl(1,2,3));
        MyNode v = new MyNode(1, new GeoLocationImpl(1,2,4));
        G1.addNode(n);
        G1.addNode(v);
        Algo.init(G1);
        DirectedWeightedGraphImpl G2 = (DirectedWeightedGraphImpl) Algo.copy();
        assertNotEquals(G1, G2);
        assertNotEquals(G1.getNode(0), G2.getNode(0));
        assertNotEquals(G1.getNode(1), G2.getNode(1));
        assertEquals(0, G2.getNode(0).getKey());
        assertEquals(1, G2.getNode(1).getKey());
    }

    @org.junit.jupiter.api.Test
    void isConnected() {
        Algo.load(G1);
        assertTrue(Algo.isConnected());
        Algo.load(G2);
        assertTrue(Algo.isConnected());
        Algo.load(G3);
        assertTrue(Algo.isConnected());
        Algo.load(st);
        assertTrue(Algo.isConnected());
        Algo.load(st2);
        assertTrue(Algo.isConnected());
    }

    @org.junit.jupiter.api.Test
    void shortestPathDist() {
        double ans;
        Algo.load(G1);
        ans = Algo.shortestPathDist(6,10);
        assertEquals(5.4283296635770935, ans);
        Algo.load(st);
        ans = Algo.shortestPathDist(362,384);
        assertEquals(605.0049493760619, ans);
        Algo.load(st2);
        ans = Algo.shortestPathDist(362,3840);
        assertEquals(ans, 1093.542322166061);
    }

    @org.junit.jupiter.api.Test
    void shortestPath() {
        List<NodeData> ans;
        Algo.load(G3);
        ans = Algo.shortestPath(7,31);
        List<NodeData> eq = new LinkedList<>();
        eq.add(Algo.getGraph().getNode(7));
        eq.add(Algo.getGraph().getNode(11));
        eq.add(Algo.getGraph().getNode(13));
        eq.add(Algo.getGraph().getNode(14));
        eq.add(Algo.getGraph().getNode(29));
        eq.add(Algo.getGraph().getNode(30));
        eq.add(Algo.getGraph().getNode(31));
        for (int i=0; i<ans.size(); i++) {
            assertEquals(ans.get(i), eq.get(i));
        }
        Algo.load(st2);
        ans = Algo.shortestPath(362, 3840);
        eq = new LinkedList<>();
        eq.add(Algo.getGraph().getNode(362));
        eq.add(Algo.getGraph().getNode(5676));
        eq.add(Algo.getGraph().getNode(2869));
        eq.add(Algo.getGraph().getNode(6643));
        eq.add(Algo.getGraph().getNode(3840));
        for (int i=0; i<ans.size(); i++) {
            assertEquals(ans.get(i), eq.get(i));
        }
    }

    @org.junit.jupiter.api.Test
    void center() {
        NodeData n;
        NodeData v;
        Algo.load(G1);
        n = Algo.getGraph().getNode(8);
        v = Algo.center();
        assertEquals(n, v);
        Algo.load(G2);
        n = Algo.getGraph().getNode(0);
        v = Algo.center();
        assertEquals(n, v);
        Algo.load(G3);
        n = Algo.getGraph().getNode(40);
        v = Algo.center();
        assertEquals(n, v);
        Algo.load(st);
        n = Algo.getGraph().getNode(362);
        v = Algo.center();
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