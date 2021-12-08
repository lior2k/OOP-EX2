import api.DirectedWeightedGraphAlgoImpl;
import api.DirectedWeightedGraphImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    private DirectedWeightedGraphAlgoImpl Algo;

    public GUI(DirectedWeightedGraphAlgoImpl algo) {
        this.Algo = algo;
    }

    public void draw() {
        new ourFrame((DirectedWeightedGraphImpl) Algo.getGraph());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
