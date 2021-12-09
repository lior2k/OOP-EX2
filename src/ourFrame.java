import api.DirectedWeightedGraphImpl;

import javax.swing.*;
import java.awt.*;

public class ourFrame extends JFrame {
    myPanel panel;

    public ourFrame(DirectedWeightedGraphImpl graph) {
        super();
        this.setTitle("Directed Weighted Graph GUI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int height = dim.height;
        int width = dim.width;
        panel = new myPanel(graph, dim);
        this.add(panel);
        init_menu();
        this.setSize(width,height);
        this.setVisible(true);
        this.pack();


    //    frame.getContentPane().setBackground(Color.blue);

    }
    public void init_menu() {
        JMenuBar bar = new JMenuBar();

        JMenu file_menu = new JMenu("File");
        file_menu.add(new JMenuItem("save"));
        file_menu.add(new JMenuItem("load"));

        JMenu graph = new JMenu("Graph");
        graph.add(new JMenuItem("add node"));
        graph.add(new JMenuItem("remove node"));
        graph.add(new JMenuItem("add edge"));
        graph.add(new JMenuItem("remove edge"));

        JMenu algorithms = new JMenu("Algorithms");
        algorithms.add(new JMenuItem("isConnected"));
        algorithms.add(new JMenuItem("tsp"));
        algorithms.add(new JMenuItem("shortestPath"));
        algorithms.add(new JMenuItem("shortestPathDist"));
        algorithms.add(new JMenuItem(""));

        bar.add(file_menu);
        bar.add(graph);
        bar.add(algorithms);
        this.setJMenuBar(bar);
    }
}
