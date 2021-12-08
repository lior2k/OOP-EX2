import api.DirectedWeightedGraphImpl;
import api.MyNode;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Iterator;

public class myPanel extends JPanel {
    DirectedWeightedGraphImpl graph;
    Dimension dim;

    public myPanel(DirectedWeightedGraphImpl graph, Dimension dim) {
        this.graph = graph;
        this.dim = dim;
        this.setPreferredSize(new Dimension(dim.width/2, dim.height/2));

    }

    @Override
    public void paint(Graphics g) {
        System.out.println("paint");
        Graphics2D g2D = (Graphics2D) g;
        Iterator<NodeData> iter = graph.nodeIter();
        double x;
        double y;
        while (iter.hasNext()) {
            MyNode n = (MyNode) iter.next();
            x = n.getLocation().x();
            y = n.getLocation().y();
            g2D.setColor(new Color(0xFF0000));
            g2D.fillOval((int) x, (int) y, 10, 10);
        //    Line2D line = new Line2D.Double(x,y,x+y,x-y);
        }
    }
}
