package Ex2.tests;

import api.DirectedWeightedGraphAlgorithmsImpl;
import api.DirectedWeightedGraphImpl;
import api.MyNode;
import api.NodeData;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DirectedWeightedGraphAlgorithmsImplTest {
    DirectedWeightedGraphAlgorithmsImpl Algo = new DirectedWeightedGraphAlgorithmsImpl();
    String st = "Assignments/Ex2/data/1000Nodes.json"; //center = node num 362
    String st2 = "Assignments/Ex2/data/10000Nodes.json"; //center = node num 3846
    String st3 = "Assignments/Ex2/data/G3.json";
    DirectedWeightedGraphImpl G = new DirectedWeightedGraphImpl();

    @Test
    public void center() {
        Algo.load(st2);
        NodeData n = Algo.getGraph().getNode(3846);
        NodeData v = Algo.center();
        assertEquals(n, v);
    }

    @Test
    public void shortestPathDist() {
        Algo.load(st);
        double ans = Algo.shortestPathDist(362,384);
        assertEquals(ans, 605.0049493760619);
        Algo.load(st2);
        ans = Algo.shortestPathDist(362,3840);
        assertEquals(ans, 1093.542322166061);

    }

}
