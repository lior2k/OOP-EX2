import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Iterator;

public class myPanel extends JPanel {
    DirectedWeightedGraphImpl graph;
    Dimension dim;
    private int mWin_h = 750;
    private int mWin_w = 750;
    private int radius = 5;

    public myPanel(DirectedWeightedGraphImpl graph, Dimension dim) {
        this.graph = graph;
        this.dim = dim;
        this.setPreferredSize(new Dimension(dim.width*3/4, dim.height));
        this.setSize(mWin_h, mWin_w);
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("paint");
        Graphics2D g2D = (Graphics2D) g;
        double max_x = graph.max_x();
        double max_y = graph.max_y();
        double min_x = graph.min_x();
        double min_y = graph.min_y();
        Iterator<NodeData> node_iter = graph.nodeIter();
        while (node_iter.hasNext()) {
            MyNode n = (MyNode) node_iter.next();
            GeoLocationImpl location = (GeoLocationImpl) n.getLocation();
            double x = (location.x()-min_x)*((mWin_h)/(max_x-min_x))+100;
            double y = (location.y()-min_y)*((mWin_w)/(max_y-min_y))+100;
            g2D.setColor(new Color(n.getTag()));
            g2D.fillOval((int) x, (int) y,10,10);
            Iterator<EdgeData> edge_iter = graph.edgeIter(n.getKey());
            while (edge_iter.hasNext()) {
                MyEdge edge = (MyEdge) edge_iter.next();
                if (edge.getSrc() == n.getKey()) {
                    MyNode v = (MyNode) graph.getNode(edge.getDest());
                    double x2 = (v.getLocation().x()-min_x)*(mWin_h/(max_x-min_x))+100;
                    double y2 = (v.getLocation().y()-min_y)*(mWin_w/(max_y-min_y))+100;
                    g2D.setColor(new Color(0xFF0000));
                    g2D.drawLine((int) x+radius, (int) y+radius, (int) x2+radius, (int) y2+radius);
                }
            }
        //    Line2D line = new Line2D.Double(x,y,x+y,x-y);
        }
    }
}
