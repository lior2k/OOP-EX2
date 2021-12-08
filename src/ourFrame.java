import api.DirectedWeightedGraphImpl;

import javax.swing.*;
import java.awt.*;

public class ourFrame extends JFrame {
    myPanel panel;

    public ourFrame(DirectedWeightedGraphImpl graph) {
        super();

        this.setTitle("our gui");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int height = dim.height;
        int width = dim.width;
        panel = new myPanel(graph, dim);
        this.add(panel);
        this.setSize(width/2,height/2);
        this.setVisible(true);
        this.pack();
    //    frame.getContentPane().setBackground(Color.blue);

    }
}
