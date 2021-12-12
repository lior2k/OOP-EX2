package api;

public class tests {
    public static void main(String[] args) {
        DirectedWeightedGraphAlgoImpl Algo = new DirectedWeightedGraphAlgoImpl();
        Algo.init_random_graph(1000000);
        Algo.save("Million.json");
    }

}
