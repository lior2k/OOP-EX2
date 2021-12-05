import api.DirectedWeightedGraphImpl;
import api.EdgeData;
import api.GeoLocation;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class showGraph extends JComponent {
    private DirectedWeightedGraphImpl graph;
    public showGraph(DirectedWeightedGraphImpl g) {
        this.graph = g;
    }

    public void paint(Graphics g) {
        Iterator<EdgeData> iter = graph.edgeIter();
        while (iter.hasNext()) {
            EdgeData edge = iter.next();
            GeoLocation src =graph.getNode(edge.getSrc()).getLocation();
            GeoLocation dest =graph.getNode(edge.getDest()).getLocation();
            double srcX = src.x()  ;
            double srcY = src.y() ;
            double destX = dest.x() *10;
            double destY = dest.y() *10;
            g.drawLine((int)srcX, (int) srcY, (int)destX, (int) destY);
        }

    }
}
