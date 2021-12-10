package tests;

import api.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyNodeTest {
    MyNode n = new MyNode(0, new GeoLocationImpl(1,2,3));

    @Test
    void addEdge() {
        MyEdge E = new MyEdge(0,2, 0);
        n.addEdge(E);
        assertEquals(E, n.getEdges().get(new MyPair(0,2)));
    }

    @Test
    void removeEdge() {
        MyEdge E = new MyEdge(0,2, 0);
        n.addEdge(E);
        assertEquals(E, n.removeEdge(new MyPair(0,2)));
    }

    @Test
    void copy() {
        MyNode v = n.copy();
        assertNotEquals(v, n);
        MyNode u = v.copy();
        assertNotEquals(u, v);
    }

//    @Test
//    void reversed() {
//        MyEdge zeroToOne = new MyEdge(0,1, 0);
//        MyEdge twoToZero = new MyEdge(2,0, 0);
//        MyEdge zeroToThree = new MyEdge(0,3, 0);
//        n.addEdge(zeroToOne);
//        n.addEdge(twoToZero);
//        n.addEdge(zeroToThree);
//        MyNode v = n.reversed();
//        MyEdge zeroToOneReversed = new MyEdge(1,0, 0);
//        MyEdge twoToZeroReversed = new MyEdge(0,2, 0);
//        MyEdge zeroToThreeReversed = new MyEdge(3,0, 0);
//        MyEdge first_edge = (MyEdge) v.getEdges().get(new MyPair(1,0));
//        MyEdge second_edge = (MyEdge) v.getEdges().get(new MyPair(0,2));
//        MyEdge third_edge = (MyEdge) v.getEdges().get(new MyPair(3,0));
//        assertEquals(zeroToOneReversed.getPair().getLeft(), first_edge.getPair().getLeft());
//        assertEquals(zeroToOneReversed.getPair().getRight(), first_edge.getPair().getRight());
//        assertEquals(twoToZeroReversed.getPair().getLeft(), second_edge.getPair().getLeft());
//        assertEquals(twoToZeroReversed.getPair().getRight(), second_edge.getPair().getRight());
//        assertEquals(zeroToThreeReversed.getPair().getLeft(), third_edge.getPair().getLeft());
//        assertEquals(zeroToThreeReversed.getPair().getRight(), third_edge.getPair().getRight());
//    }

    @Test
    void testEquals() {
        MyNode v = new MyNode(0, new GeoLocationImpl(1,2,3));
        assertFalse(v.equals(n));
    }
}