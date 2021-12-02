package api;

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
        return ans == 1;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        shortestPath(src,dest);
        MyNode temp = (MyNode) graph.getNode(dest);
        if (temp.getDist() == Integer.MAX_VALUE) {
            return -1;
        }
        return temp.getDist();
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        List<NodeData> list = new LinkedList<>();
        Dijkstra((MyNode) graph.getNode(src));
        MyNode temp = (MyNode) graph.getNode(dest);
        if (temp.getDist() == Integer.MAX_VALUE) {
            return null;
        }
        //add nodes destnode -> destnodeprev -> ... -> src
        list.add(temp);
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
        if (ans.size() == 0) {
            return null;
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
        try {
            FileWriter fw = new FileWriter(file);
            Gson gson = new GsonBuilder().create();
            Iterator<NodeData> iter = graph.nodeIter();
            List<nodesToJson> nodes = new ArrayList<>();
            while (iter.hasNext()) {
                MyNode n = (MyNode) iter.next();
                nodesToJson v = new nodesToJson(n.getKey(), n.getLocation().toString());
                nodes.add(v);
            }
            graph g = new graph(nodes);
            gson.toJson(g, fw);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean load(String file) {
        try {
            FileReader fr = new FileReader(file);
            JsonElement file_elem = JsonParser.parseReader(fr);
            JsonObject object = file_elem.getAsJsonObject();
            JsonArray nodes = object.getAsJsonArray("Nodes");
            JsonArray edges = object.getAsJsonArray("Edges");
            DirectedWeightedGraph G = new DirectedWeightedGraphImpl();
            for (JsonElement elem : nodes) {
                JsonObject obj = elem.getAsJsonObject();
                String st = obj.get("pos").getAsString();
                String[] split = st.split(",");
                GeoLocation location = new GeoLocationImpl(Double.parseDouble(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]));
                MyNode n = new MyNode(obj.get("id").getAsInt(), location, 0, White);
                G.addNode(n);
            }
            for (JsonElement elem : edges) {
                JsonObject obj = elem.getAsJsonObject();
                int src,dest;
                double w;
                src = obj.get("src").getAsInt();
                dest = obj.get("dest").getAsInt();
                w = obj.get("w").getAsDouble();
                G.connect(src, dest, w);
            }
            init(G);
        } catch (FileNotFoundException E) {
            E.printStackTrace();
            return false;
        }
        return true;
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
            MyNode n = arr[i];
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

    public void Dijkstra(MyNode S) {
        List<NodeData> Que = new LinkedList<>();
        Iterator<NodeData> iter = this.graph.nodeIter();
        while (iter.hasNext()) {
            MyNode n = (MyNode) iter.next();
            n.setDist(Integer.MAX_VALUE);
            n.setPrev(null);
            Que.add(n);
        }
        S.setDist(0);
        while (Que.size() > 0) {
            int node_index = getMinDistNodeIndex(Que);
            if (node_index == -1) {
                break;
            }
            MyNode U = (MyNode) Que.get(node_index);
            Que.remove(node_index);
            for (Map.Entry<MyPair, MyEdge> ME : U.getEdges().entrySet()) {
                if (ME.getValue().getSrc() == U.getKey()) {
                    MyNode V = (MyNode) graph.getNode(ME.getValue().getDest());
                    if (Que.contains(V)) {
                        double temp = U.getDist() + graph.getEdge(U.getKey(), V.getKey()).getWeight();
                        if (V.getDist() > temp) {
                            V.setDist(temp);
                            V.setPrev(U);
                        }
                    }
                }
            }
        }
    }

//    public void BFS(MyNode S) {
//        List<NodeData> Que = new LinkedList<>();
//        Iterator<NodeData> iter = this.graph.nodeIter();
//        while (iter.hasNext()) {
//            MyNode n = (MyNode) iter.next();
//            n.setTag(White);
//            n.setDist(Integer.MAX_VALUE);
//            n.setPrev(null);
//        }
//        S.setTag(Grey);
//        S.setDist(0);
//        Que.add(S);
//        while (Que.size() > 0) {
//            MyNode U = (MyNode) Que.get(0);
//            Que.remove(0);
//            for (Map.Entry<MyPair, MyEdge> ME : U.getEdges().entrySet()) {
//                if (ME.getValue().getSrc() == U.getKey()) {
//                    MyNode V = (MyNode) this.graph.getNode(ME.getValue().getDest());
//                    if (V.getTag() == White) {
//                        V.setTag(Grey);
//                        V.setDist(U.getDist() + 1);
//                        V.setPrev(U);
//                        Que.add(V);
//                    }
//                }
//            }
//            U.setTag(Black);
//        }
//    }

    public int getMinDistNodeIndex(List<NodeData> L) {
        int ans = -1;
        double dist = Integer.MAX_VALUE;
        for (int i = 0; i < L.size(); i++) {
            MyNode temp = (MyNode) L.get(i);
            if (temp.getDist() < dist) {
                dist = temp.getDist();
                ans = i;
            }
        }
        return ans;
    }

}