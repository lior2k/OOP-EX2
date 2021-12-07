import api.DirectedWeightedGraphAlgoImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    private DirectedWeightedGraphAlgoImpl Algo;

    public GUI() {
        Algo = new DirectedWeightedGraphAlgoImpl();
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
