import api.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

public class DW_GraphGUI extends JFrame implements ActionListener{
    LinkedList<NodeData> nodePos = new LinkedList<>();
    private int kRADIUS = 5;
    private int mWin_h = 500;
    private int mWin_w = 500;
    DirectedWeightedGraphAlgoImpl graphAlgo;
    DirectedWeightedGraphImpl graph;

    public DW_GraphGUI(DirectedWeightedGraphAlgorithms graphAlgo, DirectedWeightedGraph graph) {
        this.graphAlgo = (DirectedWeightedGraphAlgoImpl) graphAlgo;
        this.graph = (DirectedWeightedGraphImpl) graph;
        initGUI();
    }

    public void initGUI() {

        this.setSize(mWin_h, mWin_w);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenu graphMenu = new JMenu("Graph_Methods");
        JMenuItem addNode = new JMenuItem("addNode");
        JMenuItem getNode = new JMenuItem("getNode");
        JMenuItem removeNode = new JMenuItem("removeNode");
        JMenuItem nodeSize = new JMenuItem("nodeSize");
        JMenuItem conncet = new JMenuItem("connect");
        JMenuItem getEdge = new JMenuItem("getEdge");
        JMenuItem removeEdge = new JMenuItem("removeEdge");
        JMenuItem edgeSize = new JMenuItem("edgeSize");
        JMenuItem getMc = new JMenuItem("getMC");

        addNode.addActionListener(this);
        getNode.addActionListener(this);
        removeNode.addActionListener(this);
        nodeSize.addActionListener(this);
        conncet.addActionListener(this);
        getEdge.addActionListener(this);
        removeEdge.addActionListener(this);
        edgeSize.addActionListener(this);
        getMc.addActionListener(this);

        graphMenu.add(addNode);
        graphMenu.add(getNode);
        graphMenu.add(removeNode);
        graphMenu.add(nodeSize);
        graphMenu.add(conncet);
        graphMenu.add(getEdge);
        graphMenu.add(removeEdge);
        graphMenu.add(edgeSize);
        graphMenu.add(getMc);

        JMenu graphAlgoMenu = new JMenu("Graph_Algo_Methods");
        JMenuItem getGraph = new JMenuItem("getGraph");
        JMenuItem copy = new JMenuItem("copy");
        JMenuItem isConnected = new JMenuItem("isConnected");
        JMenuItem shortestPathDist = new JMenuItem("shortestPathDist");
        JMenuItem shortestPath = new JMenuItem("shortestPath");
        JMenuItem center = new JMenuItem("center");
        JMenuItem tsp = new JMenuItem("tsp");
        JMenuItem save = new JMenuItem("save");
        JMenuItem load = new JMenuItem("load");

        getGraph.addActionListener(this);
        copy.addActionListener(this);
        isConnected.addActionListener(this);
        shortestPathDist.addActionListener(this);
        conncet.addActionListener(this);
        shortestPath.addActionListener(this);
        center.addActionListener(this);
        tsp.addActionListener(this);


        graphAlgoMenu.add(getGraph);
        graphAlgoMenu.add(copy);
        graphAlgoMenu.add(isConnected);
        graphAlgoMenu.add(shortestPathDist);
        graphAlgoMenu.add(shortestPath);
        graphAlgoMenu.add(center);
        graphAlgoMenu.add(tsp);
        graphAlgoMenu.add(save);
        graphAlgoMenu.add(load);


        JMenuBar menuBar = new JMenuBar();
        menuBar.add(graphMenu);
        menuBar.add(graphAlgoMenu);
        this.setJMenuBar(menuBar);
        graphMenu.addActionListener(this);
        graphAlgoMenu.addActionListener(this);


    }

    @Override
    public void paint(Graphics g) {
        DirectedWeightedGraphImpl graph = (DirectedWeightedGraphImpl) graphAlgo.getGraph();
        Iterator<NodeData> nodes = graph.nodeIter();
        while (nodes.hasNext()) {
            MyNode n = (MyNode) nodes.next();
            double x = n.getLocation().x();
            double y = n.getLocation().y();
            double z = n.getLocation().z();

            g.setColor(Color.BLUE);
            g.fillOval((int) x - kRADIUS, (int) y - kRADIUS, 2 * kRADIUS, 2 * kRADIUS);
            Iterator<EdgeData> EdgeIter = graph.edgeIter(n.getKey());
            while (EdgeIter.hasNext()) {
                MyEdge e = (MyEdge) EdgeIter.next();
                if (e.getSrc() == n.getKey()) {
                    MyNode v = (MyNode) graph.getNode(e.getDest());
                    double _x = v.getLocation().x();
                    double _y = v.getLocation().y();
                    g.setColor(Color.RED);
                    g.drawLine((int) x, (int) y, (int) _x, (int) _y);
                }
            }


        }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        String str = e.getActionCommand();
//            switch(str) {
//                case "addNode":
//            }
//    }
//
//    @Override
//    public void mouseClicked(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//
//    }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
