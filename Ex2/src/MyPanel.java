import api.*;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class MyPanel extends JPanel {
    DirectedWeightedGraphAlgoImpl Algo;
    Dimension dim;
    private int window_height;
    private int window_wide;

    public MyPanel(DirectedWeightedGraphAlgoImpl algo, Dimension dim) {
        this.Algo = algo;
        this.dim = dim;
        this.window_height = dim.height/3;
        this.window_wide = dim.width/3;
        this.setPreferredSize(new Dimension(dim.width*3/4, dim.height));
        this.setSize(window_wide, window_height);
    }


    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        DirectedWeightedGraphImpl G = (DirectedWeightedGraphImpl) Algo.getGraph();
        double max_x = G.max_x();
        double max_y = G.max_y();
        double min_x = G.min_x();
        double min_y = G.min_y();
        Iterator<NodeData> node_iter = G.nodeIter();
        while (node_iter.hasNext()) {
            MyNode n = (MyNode) node_iter.next();
            GeoLocationImpl location = (GeoLocationImpl) n.getLocation();
            double x = (location.x()-min_x)*((window_height)/(max_x-min_x))+400;
            double y = (location.y()-min_y)*((window_wide)/(max_y-min_y))+100;
            g2D.setColor(new Color(n.getTag()));
            g2D.fillOval((int) x, (int) y,10,10);

            Iterator<EdgeData> edge_iter = G.edgeIter(n.getKey());
            while (edge_iter.hasNext()) {
                MyEdge edge = (MyEdge) edge_iter.next();
                if (edge.getSrc() == n.getKey()) {
                    MyNode v = (MyNode) G.getNode(edge.getDest());
                    double x2 = (v.getLocation().x()-min_x)*(window_height /(max_x-min_x))+400;
                    double y2 = (v.getLocation().y()-min_y)*(window_wide /(max_y-min_y))+100;
                    g2D.setColor(new Color(0xD5CC1C));
                    g2D.drawLine((int) x+5, (int) y+5, (int) x2+5, (int) y2+5);
                    drawArrowHead(g,(int) x+5, (int) y+5, (int) x2+5, (int) y2+5);
                }
            }
        }
    }

    private void drawArrowHead(Graphics g, int x1, int y1, int x2, int y2) {
        int arrow_width = 15;
        int arrow_height = 2;
        int diff_x = x2 - x1, diff_y = y2 - y1;
        double D = Math.sqrt(diff_x*diff_x + diff_y*diff_y);
        double xm = D - arrow_width, xn = xm, ym = arrow_height, yn = -arrow_height, x;
        double sin = diff_y / D;
        double cos = diff_x / D;

        x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

        int[] x_points = {x2, (int) xm, (int) xn};
        int[] y_points = {y2, (int) ym, (int) yn};

        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(x_points, y_points, 3);
    }

}
//0xFF0000