import api.*;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class PaintGraph extends JComponent {

    public DirectedWeightedGraph graph;

    public PaintGraph(DirectedWeightedGraph g){
        this.graph = g;
    }

    public void PaintTheGraph(Graphics graphics){

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(200,200,200,200));
        panel.setLayout(new GridLayout(0,1));

        frame.add(panel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Our_Graph");
        frame.pack();
        frame.setVisible(true);

        Iterator<NodeData> iter= this.graph.nodeIter();
        while (iter.hasNext()){
            NodeData node = iter.next();
            GeoLocation location = node.getLocation();
            double x = location.x();
            double y = location.y();
            double z = location.z();
            graphics.drawLine((int) x,(int)y,(int) x,(int) y);
        }

    }

    public static void main(String[] args) {
        DirectedWeightedGraph g = new DirectedWeightedGraphImpl();
        GeoLocation l0 = new GeoLocationImpl(1, 1, 1);
        GeoLocation l1 = new GeoLocationImpl(30, 30, 30);
        GeoLocation l2 = new GeoLocationImpl(100, 100, 100);
        MyNode n0 = new MyNode(0, l0);
        MyNode n1 = new MyNode(1, l1);
        MyNode n2 = new MyNode(2, l2);
        g.addNode(n0);
        g.addNode(n1);
        g.addNode(n2);
        PaintGraph p = new PaintGraph(g);
        p.PaintTheGraph(p.getGraphics());
    }
}

