package api;

import org.w3c.dom.Node;

import java.lang.reflect.Array;
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
        List<NodeData> list = shortestPath(src,dest);
        double ans = 0;
        for (NodeData n : list) {
            ans = ans + n.getWeight();
        }
        return ans;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        List<NodeData> list = new LinkedList<>();
        BFS((MyNode) graph.getNode(src));
        MyNode temp = (MyNode) graph.getNode(dest);
        while (temp.getKey() != src) {
            list.add(temp.getPrev());
            temp = temp.getPrev();
        }
        //reverse the list
        List<NodeData> ans = new LinkedList<>();
        int size = list.size()-1;
        while (size >= 0) {
            ans.add(list.get(size));
            size--;
        }
        return ans;
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
        int components = 0;
        Iterator<NodeData> iter = graph.nodeIter();
        while (iter.hasNext()) {
            MyNode temp = (MyNode) iter.next();
            temp.setTag(White);
        }
        iter = graph.nodeIter();
        MyNode[] arr = new MyNode[graph.nodeSize()];
        int k = 0 ;
        while (iter.hasNext()) {
            MyNode tmp = (MyNode) iter.next();
            arr[k++] = tmp;
        }
        System.out.println(Arrays.toString(arr));
        sortByFinishTime(arr);
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            MyNode n = (MyNode) arr[i];
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

    public void BFS(MyNode S) {
        List<NodeData> Que = new LinkedList<>();
        Iterator<NodeData> iter = this.graph.nodeIter();
        while (iter.hasNext()) {
            MyNode n = (MyNode) iter.next();
            n.setTag(White);
            n.setDist(Integer.MAX_VALUE);
            n.setPrev(null);
        }
        S.setTag(Grey);
        S.setDist(0);
        Que.add(S);
        while (Que.size() > 0) {
            MyNode U = (MyNode) Que.get(0);
            Que.remove(0);
            for (Map.Entry<MyPair, MyEdge> ME : U.getEdges().entrySet()) {
                if (ME.getValue().getSrc() != U.getKey()) {
                    MyNode V = (MyNode) this.graph.getNode(ME.getValue().getDest());
                    if (V.getTag() == White) {
                        V.setTag(Grey);
                        Que.add(V);
                        if (V.getDist() > ME.getValue().getWeight() + U.getDist()) {
                            V.setDist(ME.getValue().getWeight() + U.getDist());
                            V.setPrev(U);
                        }
                    }
                }
            }
            U.setTag(Black);
        }
    }

    public List<NodeData> Dijkstra(MyNode S) {
        List<NodeData> ans = new LinkedList<>();
        List<NodeData> Que = new LinkedList<>();
        Iterator<NodeData> iter = this.graph.nodeIter();
        while (iter.hasNext()) {
            MyNode n = (MyNode) iter.next();
            n.setTag(White);
            n.setDist(Integer.MAX_VALUE);
        }
        S.setTag(Grey);
        S.setDist(0);
        Que.add(S);
        while (Que.size() > 0) {
            MyNode U = (MyNode) Que.get(0);
            Que.remove(0);
            for (Map.Entry<MyPair, MyEdge> ME : U.getEdges().entrySet()) {
                if (ME.getValue().getSrc() != U.getKey()) {
                    MyNode V = (MyNode) this.graph.getNode(ME.getValue().getDest());
                    if (V.getTag() == White) {
                        V.setTag(Grey);
                        V.setDist(U.getDist() + 1);
                        Que.add(V);
                    }
                }
            }
            U.setTag(Black);
        }
        return ans;
    }

}
