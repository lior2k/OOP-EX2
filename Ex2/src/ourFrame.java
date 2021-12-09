import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ourFrame extends JFrame implements ActionListener {
    myPanel panel;
    DirectedWeightedGraphAlgoImpl Algo;

    public ourFrame(DirectedWeightedGraphAlgoImpl algo) {
        super();
        this.Algo = algo;
        this.setTitle("Directed Weighted Graph GUI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int height = dim.height;
        int width = dim.width;
        panel = new myPanel(Algo, dim);
        this.add(panel);
        init_menu();
        this.setSize(width, height);
        this.setVisible(true);
        this.pack();
        this.setBackground(new Color(0xFF524747));
    }

    public void init_menu() {
        JMenuBar bar = new JMenuBar();

        JMenu file_menu = new JMenu("File");
        JMenuItem save = new JMenuItem("save");
        JMenuItem load = new JMenuItem("load");
        file_menu.add(save);
        file_menu.add(load);
        save.addActionListener(this);
        load.addActionListener(this);
        JMenu graph = new JMenu("Graph");
        JMenuItem getNode = new JMenuItem("getNode");
        JMenuItem getEdge = new JMenuItem("getEdge");
        JMenuItem addNode = new JMenuItem("addNode");
        JMenuItem connect = new JMenuItem("connect");
        JMenuItem removeNode = new JMenuItem("removeNode");

        JMenuItem removeEdge = new JMenuItem("removeEdge");
        graph.add(getNode);
        graph.add(getEdge);
        graph.add(addNode);
        graph.add(connect);
        graph.add(removeNode);
        graph.add(removeEdge);
        getNode.addActionListener(this);
        getEdge.addActionListener(this);
        addNode.addActionListener(this);
        connect.addActionListener(this);
        removeNode.addActionListener(this);
        removeEdge.addActionListener(this);
        JMenu algorithms = new JMenu("Algorithms");
        JMenuItem isConnected = new JMenuItem("isConnected");
        JMenuItem tsp = new JMenuItem("tsp");
        JMenuItem shortestPath = new JMenuItem("shortestPath");
        JMenuItem shortestPathDist = new JMenuItem("shortestPathDist");
        JMenuItem center = new JMenuItem("center");
        algorithms.add(isConnected);
        algorithms.add(tsp);
        algorithms.add(shortestPath);
        algorithms.add(shortestPathDist);
        algorithms.add(center);
        isConnected.addActionListener(this);
        tsp.addActionListener(this);
        shortestPath.addActionListener(this);
        shortestPathDist.addActionListener(this);
        center.addActionListener(this);

        bar.add(file_menu);
        bar.add(graph);
        bar.add(algorithms);
        this.setJMenuBar(bar);
    }

//    public void init_removeNodeAction() {
//        JLabel label = new JLabel("Insert Key: ");
//        label.setVisible(true);
//        label.setBounds(40,5,200,50);
//        JTextField textField = new JTextField();
//        textField.setEnabled(true);
//        textField.setEditable(true);
//        textField.setBounds(40,50,100,50);
//        this.add(label);
//        this.panel.add(textField);
//
//        textField.addActionListener(e2 -> {
//            textField.setVisible(true);
//            String nodekey = textField.getText();
//            Algo.getGraph().removeNode((Integer.parseInt(nodekey)));
//            repaint();
////                panel.remove(textField);
////                panel.remove(label);
//            textField.setVisible(false);
//            label.setVisible(false);
//        });
//    }
//
//    public void init_addNodeAction() {
//        System.out.println("x");
//        JPanel panel2 = new JPanel();
//        this.add(panel2);
//        enter_x = new JLabel("enter x: ");
//        key_text = new JTextField();
//        enter_x.setVisible(true);
//        key_text.setVisible(true);
//        enter_x.setEnabled(true);
//        key_text.setEnabled(true);
//        key_text.setEditable(true);
//        enter_x.setBounds(25,25,1000,500);
//        key_text.setBounds(25,50,1000,500);
//        panel2.add(enter_x);
//        panel2.add(key_text);
//        panel2.setVisible(true);
//        panel2.setEnabled(true);
//        key_text.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//}

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        String st = e.getActionCommand();
        if (Objects.equals(st, "removeNode")) {
                String node_id_str = JOptionPane.showInputDialog("insert node id");
                int node_id = Integer.parseInt(node_id_str);
                Algo.getGraph().removeNode(node_id);
                repaint();
        } else if (Objects.equals(st,"addNode")) {
            String node_id_str = JOptionPane.showInputDialog("insert node id");
            String node_location_x_str = JOptionPane.showInputDialog("insert x value");
            String node_location_y_str = JOptionPane.showInputDialog("insert y value");
            int node_id = Integer.parseInt(node_id_str);
            double node_location_x = Double.parseDouble(node_location_x_str);
            double node_location_y = Double.parseDouble(node_location_y_str);
            MyNode add = new MyNode(node_id, new GeoLocationImpl(node_location_x, node_location_y, 0));
            Algo.getGraph().addNode(add);
            repaint();
        } else if (Objects.equals(st,"load")) {
            String load_filename_str = JOptionPane.showInputDialog("insert json file including location");
            Algo.load(load_filename_str);
            repaint();
        } else if (Objects.equals(st, "save")) {
            String save_filename_str = JOptionPane.showInputDialog("insert filename");
            Algo.save(save_filename_str);
        } else if (Objects.equals(st, "removeEdge")) {
            String remove_edge_src_str = JOptionPane.showInputDialog("insert src node key");
            String remove_edge_dest_str = JOptionPane.showInputDialog("insert dest node key");
            int src_node_index = Integer.parseInt(remove_edge_src_str);
            int dest_node_index = Integer.parseInt(remove_edge_dest_str);
            Algo.getGraph().removeEdge(src_node_index, dest_node_index);
            repaint();
        } else if (Objects.equals(st, "connect")) {
            String src_node_index_str = JOptionPane.showInputDialog("insert src node key");
            String dest_node_index_str = JOptionPane.showInputDialog("insert dest node key");
            String weight_str = JOptionPane.showInputDialog("insert edge weight");
            int src_node_index = Integer.parseInt(src_node_index_str);
            int dest_node_index = Integer.parseInt(dest_node_index_str);
            double weight = Double.parseDouble(weight_str);
            Algo.getGraph().connect(src_node_index, dest_node_index, weight);
            repaint();
        } else if (Objects.equals(st, "isConnected")) {
            boolean ans = Algo.isConnected();
            JOptionPane.showMessageDialog(null, ans);
        } else if (Objects.equals(st, "shortestPathDist")) {
            String src_node_str = JOptionPane.showInputDialog("insert src node key");
            String dest_node_str = JOptionPane.showInputDialog("insert dest node key");
            int src_node_index = Integer.parseInt(src_node_str);
            int dest_node_index = Integer.parseInt(dest_node_str);
            double ans = Algo.shortestPathDist(src_node_index, dest_node_index);
            JOptionPane.showMessageDialog(null, ans);
        } else if (Objects.equals(st, "shortestPath")) {
            String src_node_str = JOptionPane.showInputDialog("insert src node key");
            String dest_node_str = JOptionPane.showInputDialog("insert dest node key");
            int src_node_index = Integer.parseInt(src_node_str);
            int dest_node_index = Integer.parseInt(dest_node_str);
            List<NodeData> ans = Algo.shortestPath(src_node_index, dest_node_index);
            for (int i = 0; i<ans.size()-1; i++) {
                MyNode n = (MyNode) ans.get(i);
                MyNode n2 = (MyNode) ans.get(i+1);
            //    MyEdge edge = (MyEdge) Algo.getGraph().getEdge(n.getKey(), n2.getKey());
                n.setTag(0x00FF04);
                n2.setTag(0x00FF04);
             //   edge.setTag(0x00FF04);
            }
            repaint();
        } else if (Objects.equals(st, "center")) {
            MyNode n = (MyNode) Algo.center();
            int ans = n.getKey();
            n.setTag(0x00FF04);
            JOptionPane.showMessageDialog(null, ans);
            repaint();
        } else if (Objects.equals(st, "tsp")) {
            String cities_size_str = JOptionPane.showInputDialog("insert how many cities you want to visit");
            int cities_size = Integer.parseInt(cities_size_str);
            List<NodeData> cities = new LinkedList<>();
            for (int i=1; i<cities_size+1; i++) {
                String city_str = JOptionPane.showInputDialog("insert the "+i+"th city");
                int city = Integer.parseInt(city_str);
                cities.add(Algo.getGraph().getNode(city));
            }
            List<NodeData> ans = Algo.tsp(cities);
            List<Integer> ans_int = new LinkedList<>();
            for (NodeData city : ans) {
                city.setTag(0x00FF04);
                ans_int.add(city.getKey());
            }
            repaint();
            String ans_str = "";
            for (int i : ans_int) {
                 ans_str = ans_str+i+" ";
            }
            JOptionPane.showMessageDialog(null, ans_str);
        }
    }
}
