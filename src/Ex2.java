import api.*;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    private static DirectedWeightedGraphAlgorithmsImpl Algo;

    public Ex2() {
        this.Algo = new DirectedWeightedGraphAlgorithmsImpl();
    }

    public Ex2(DirectedWeightedGraphAlgorithmsImpl algo) {
        this.Algo = algo;
    }

    public void init(String file) {
        this.Algo.load(file);
    }

    public static DirectedWeightedGraph getGrapg(String json_file) {
        Algo.load(json_file);
        DirectedWeightedGraph ans = Algo.getGraph();
        return ans;
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        Algo.load(json_file);
        DirectedWeightedGraphAlgorithms ans = Algo;
        return ans;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        Iterator<NodeData> iter = alg.getGraph().nodeIter();
        while (iter.hasNext()) {
            MyNode n = (MyNode) iter.next();
            Color color = new Color(n.getTag());
            StdDraw.setPenColor(color);
            StdDraw.filledCircle(n.getLocation().x(), n.getLocation().y(), 0.1);
        }
//        Graphics2D graph;
//        // creating object of JFrame(Window popup)
//        JFrame window = new JFrame();
//
//        // setting closing operation
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // setting size of the pop window
//        window.setBounds(50, 50, 10000, 10000);
//
//        // setting canvas for draw
//        DirectedWeightedGraphImpl g1 = (DirectedWeightedGraphImpl) getGrapg(json_file);
//        window.getContentPane().add(new showGraph(g1));
//
//        // set visibility
//        window.setVisible(true);
    }

    public static void main(String[] args) {
        Ex2 test = new Ex2();
        test.init("Assignments/Ex2/data/G2.json");
        runGUI("Assignments/Ex2/data/G2.json");
    }
}