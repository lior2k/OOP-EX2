package api;

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

        System.out.println(G1);
        G1.printgraph();
        G1.removeNode(0);
 //       G1.removeEdge(1,2);
        System.out.println(G1);
        G1.printgraph();

    }
}
