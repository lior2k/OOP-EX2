package api;

import org.w3c.dom.Node;

import java.util.*;

public class DirectedWeightedGraphAlgorithmsImpl implements DirectedWeightedGraphAlgorithms {
    private DirectedWeightedGraph graph;
    int Black = 0;
    int Grey = 153;
    int White = 255;
    int Time = 0;

    public DirectedWeightedGraphAlgorithmsImpl(DirectedWeightedGraphImpl g) {
        init(g);
    }

    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraphImpl g = new DirectedWeightedGraphImpl();
        for (Map.Entry<Integer, MyNode> me : g.getMap().entrySet()) {
            MyNode temp_node = me.getValue().copy();
            g.addNode(temp_node);
        }
        return g;
    }

    @Override
    public boolean isConnected() {
        DFS();
        DirectedWeightedGraphImpl reversedGraph = reverseGraph();
        int ans = DFS_2(reversedGraph);
        if (ans == 1) {
            return true;
        }
        return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
    }

    @Override
    public NodeData center() {
        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        return false;
    }

    public void DFS() {
        //LinkedList<NodeData> ans = new LinkedList<>();
        this.Time = 0;
        Iterator<NodeData> iter = graph.nodeIter();
        while (iter.hasNext()) {
            MyNode temp = (MyNode) iter.next();
            temp.setDiscovery_time(0);
            temp.setFinish_time(0);
            temp.setTag(White);
        }
        iter = graph.nodeIter();
        while (iter.hasNext()) {
            MyNode n = (MyNode) iter.next();
            if (n.getTag() == White) {
                DFS_Visit(n);
            }
        }
    }

    public void DFS_Visit(MyNode n) {
        n.setTag(Grey);
        this.Time++;
        n.setDiscovery_time(this.Time);
        Iterator<EdgeData> iter = graph.edgeIter(n.getKey());
        while (iter.hasNext()) {
            MyEdge E = (MyEdge) iter.next();
            if (E.getSrc() == n.getKey()) {
                MyNode V = (MyNode) graph.getNode(E.getDest());
                if (V.getTag() == White) {
                    DFS_Visit(V);
                }
            }
        }
        n.setTag(Black);
        this.Time++;
        n.setFinish_time(this.Time);
    }

    public DirectedWeightedGraphImpl reverseGraph() {
        DirectedWeightedGraphImpl reversedGraph = new DirectedWeightedGraphImpl();
        DirectedWeightedGraphImpl temp = (DirectedWeightedGraphImpl) this.getGraph();
        for (Map.Entry<Integer, MyNode> ME : temp.getMap().entrySet()) {
            MyNode temp_node = ME.getValue().reversed();
            reversedGraph.addNode(temp_node);
        }
        return reversedGraph;
    }

    public int DFS_2(DirectedWeightedGraphImpl graph) {
        graph.printGraph();
        int components = 0;
        Iterator<NodeData> iter = graph.nodeIter();
        while (iter.hasNext()) {
            MyNode temp = (MyNode) iter.next();
            temp.setTag(White);
        }
        iter = graph.nodeIter();
        ArrayList<MyNode> arr = new ArrayList<>();
        MyNode[] arr2 = new MyNode[graph.nodeSize()];
        arr2[0] = (MyNode) graph.getNode(0);
        arr2[1] = (MyNode) graph.getNode(2);
        arr2[2] = (MyNode) graph.getNode(1);
//        int k = 0 ;
//        while (iter.hasNext()) {
//            //arr.add((MyNode) iter.next());
//            MyNode tmp = (MyNode) iter.next();
//            System.out.println(tmp);
//            arr2[k] = tmp;
//        }
        System.out.println(Arrays.toString(arr2));
        sortByFinishTime(arr2);
        System.out.println(Arrays.toString(arr2));
        for (int i=0; i<arr2.length; i++) {
            MyNode n = (MyNode) arr2[i];
            if (n.getTag() == White) {
                DFS_Visit_2(graph, n);
                components++;
            }
        }
        return components;
    }

    public void DFS_Visit_2(DirectedWeightedGraphImpl graph, MyNode n) {
        n.setTag(Grey);
        Iterator<EdgeData> iter = graph.edgeIter(n.getKey());
        while (iter.hasNext()) {
            MyEdge E = (MyEdge) iter.next();
            if (E.getSrc() == n.getKey()) {
                MyNode V = (MyNode) graph.getNode(E.getDest());
                if (V.getTag() == White) {
                    DFS_Visit_2(graph, V);
                }
            }
        }
        n.setTag(Black);
    }

    public void sortByFinishTime(MyNode[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-i-1; j++) {
                if (arr[j].getFinish_time() < arr[j+1].getFinish_time()) {
                    swap(arr,j,j+1);
                }
            }
        }
    }

    public void swap(MyNode[] arr, int i, int j) {
        MyNode temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

}
