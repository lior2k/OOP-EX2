package api;

import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DirectedWeightedGraphAlgoImpl implements DirectedWeightedGraphAlgorithms{

    private DirectedWeightedGraph graph;
    int Time = 0;
    int Grey = 153;
    int Black = 0;
    int White = 255;

    public DirectedWeightedGraphAlgoImpl() {
        this.graph = new DirectedWeightedGraphImpl();
    }

    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph g = new DirectedWeightedGraphImpl();
        Iterator<NodeData> iter = this.graph.nodeIter();
        while(iter.hasNext()){
            MyNode n = (MyNode)iter.next();
            g.addNode(n.copy());
        }
        return g;
    }
    @Override
    public boolean isConnected() {
        MyNode n = (MyNode) graph.getNode(0);
        BFS(n);
        Iterator<NodeData> iter = graph.nodeIter();
        while (iter.hasNext()) {
            MyNode v = (MyNode) iter.next();
            if (v.getDistance() == Integer.MAX_VALUE) {
                return false;
            }
        }
        return true;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        Dijkstra((MyNode) graph.getNode(src));
        double shortest_dis = ((MyNode) graph.getNode(dest)).getDistance();
        if(shortest_dis == Integer.MAX_VALUE){
            return -1;
        }
        return shortest_dis;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        List<NodeData> list = new LinkedList<>();
        Dijkstra((MyNode) graph.getNode(src));
        MyNode temp = (MyNode) graph.getNode(dest);
        if(temp.getDistance() == Integer.MAX_VALUE){
            return null;
        }
        //add nodes destnode -> destnodeprev -> ... -> src
        while(temp != null){
            list.add(temp);
            temp = temp.getPrev();
        }
        //reverse the list
        List<NodeData> ans = new LinkedList<>();
        int size = list.size()-1;
        while (size >= 0){
            ans.add(list.get(size));;
            size--;
        }
        return ans;
    }

    @Override
    public NodeData center() {
//        if (!isConnected()) {
//            return null;
//        }
        double shortest_dist = Integer.MAX_VALUE;
        NodeData ans = null;
        Iterator<NodeData> iter = graph.nodeIter();
        while (iter.hasNext()) {
            MyNode n = (MyNode) iter.next();
            Dijkstra(n);
            Iterator<NodeData> iter2 = graph.nodeIter();
            double max_dist = -1;
            while (iter2.hasNext()) {
                MyNode v = (MyNode) iter2.next();
                if (v.getDistance() > max_dist) {
                    max_dist = v.getDistance();
                }
            }
            if (max_dist < shortest_dist) {
                shortest_dist = max_dist;
                ans = n;
            }
        }
        return ans;
    }

//    public NodeData center2() {
//        double shortest_dist = Integer.MAX_VALUE;
//        NodeData ans = null;
//        Iterator<NodeData> iter = graph.nodeIter();
//        while (iter.hasNext()) {
//            MyNode n = (MyNode) iter.next();
//            double max_dist = Dijkstra_center(n);
//            if (max_dist < shortest_dist) {
//                shortest_dist = max_dist;
//                ans = n;
//            }
//        }
//        return ans;
//    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        DirectedWeightedGraphImpl G = (DirectedWeightedGraphImpl) copy();
        List<NodeData> ans = new LinkedList<>();
        int i = 0;
        while (i < cities.size()-1) {
            while (i < cities.size()-1) {
                if (!ans.contains(cities.get(i))) {
                    break;
                } else {
                    i++;
                }
            }
            if (i >= cities.size()) {
                break;
            }
            int j = i + 1;
            while (j < cities.size()) {
                if (!ans.contains(cities.get(j))) {
                    break;
                } else {
                    j++;
                }
            }
            if (j >= cities.size()) {
                break;
            }
            MyNode n = (MyNode) cities.get(i);
            MyNode v = (MyNode) cities.get(j);
            List<NodeData> path = shortestPath_TSP(n.getKey(), v.getKey(), G);
            if (path == null) {
                return null;
            }
            for (NodeData temp : path) {
                if (!temp.equals(v)) {
                    ans.add(temp);
                }
            }
            i++;
        }
        i = 0;
        //last while loop just to find the last node in cities that we didn't add to ans
        while (i < cities.size()) {
            if (!ans.contains(cities.get(i))) {
                ans.add(cities.get(i));
            }
            i++;
        }
        return ans;
    }

    @Override
    public boolean save(String file) {
        JsonObject JsonGraph = new JsonObject();
        JsonArray nodesArr = new JsonArray();
        JsonArray edgesArr = new JsonArray();
        Iterator<NodeData> iter = graph.nodeIter();
        while (iter.hasNext()) {
            MyNode n = (MyNode) iter.next();
            int key = n.getKey();
            JsonObject JsonNode = new JsonObject();
            JsonNode.addProperty("pos", n.getLocation().x()+","+n.getLocation().y()+","+n.getLocation().z());
            JsonNode.addProperty("id", key);
            nodesArr.add(JsonNode);
            Iterator<EdgeData> edgeIter = graph.edgeIter(key);
            while (edgeIter.hasNext()) {
                MyEdge e = (MyEdge) edgeIter.next();
                if (e.getSrc() == key) {
                    JsonObject JsonEdge = new JsonObject();
                    JsonEdge.addProperty("src", e.getSrc());
                    JsonEdge.addProperty("w", e.getWeight());
                    JsonEdge.addProperty("dest", e.getDest());
                    edgesArr.add(JsonEdge);
                }
            }
        }
        JsonGraph.add("Edges", edgesArr);
        JsonGraph.add("Nodes", nodesArr);
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(String.valueOf(JsonGraph));
            fw.flush();
            fw.close();
        } catch (IOException e){
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
                MyNode n = new MyNode(obj.get("id").getAsInt(), location);
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

//    private DirectedWeightedGraphImpl reverse(){
//        DirectedWeightedGraphImpl reversed_graph = (DirectedWeightedGraphImpl) copy();
//        Iterator<EdgeData> iter = reversed_graph.edgeIter();
//        while (iter.hasNext()){
//            MyEdge e = (MyEdge) iter.next();
//            e.reverseEdge();
//        }
//        return reversed_graph;
//    }

//    private void DFS(){
//        Iterator<NodeData> iter = this.graph.nodeIter();
//        while(iter.hasNext()){
//            MyNode n = (MyNode) iter.next();
//            n.setTag(White);
//        }
//        Time = 0;
//        iter = this.graph.nodeIter();
//        while(iter.hasNext()){
//            MyNode u = (MyNode) iter.next();
//            if(u.getTag() == White){
//                DFS_visit(u);
//            }
//        }
//    }
//
//    private void DFS_visit(MyNode u){
//        u.setTag(Grey);
//        Time++;
//        u.setDiscovery_time(Time);
//        Iterator<EdgeData> iter = this.graph.edgeIter(u.getKey());
//        while(iter.hasNext()){
//            MyEdge e = (MyEdge) iter.next();
//            if(e.getSrc() == u.getKey()){
//                MyNode v = (MyNode) this.graph.getNode(e.getDest());
//                if(v.getTag() == White){
//                    DFS_visit(v);
//                }
//            }
//        }
//        u.setTag(Black);
//        u.setFinish_time(++Time);
//    }
//
//    private int DFS2(DirectedWeightedGraph g){
//        Iterator<NodeData> iter = g.nodeIter();
//        while(iter.hasNext()){
//            MyNode n = (MyNode) iter.next();
//            n.setTag(White);
//        }
//        int counter = 0;
//        iter = g.nodeIter();
//        MyNode[] arr = new MyNode[g.nodeSize()];
//        while(iter.hasNext()){
//            MyNode n = (MyNode) iter.next();
//            arr[counter++] = n;
//        }
//        counter = 0;
//        mergeSort(arr,arr.length);
//        for (int i=0; i< arr.length; i++){
//            if(arr[i].getTag() == White){
//                counter++;
//                DFS2_visit(g,arr[i]);
//            }
//        }
//        return counter;
//    }
//
//    private void DFS2_visit(DirectedWeightedGraph g,MyNode u){
//        u.setTag(Grey);
//        Iterator<EdgeData> iter = g.edgeIter(u.getKey());
//        while(iter.hasNext()){
//            MyEdge e = (MyEdge) iter.next();
//            if(e.getSrc() == u.getKey()){
//                MyNode v = (MyNode) g.getNode(e.getDest());
//                if(v.getTag() == White){
//                    DFS2_visit(g,v);
//                }
//            }
//        }
//        u.setTag(Black);
//    }

//    private void mergeSort(MyNode[] a, int n) {
//        if (n < 2) {
//            return;
//        }
//        int mid = n / 2;
//        MyNode[] l = new MyNode[mid];
//        MyNode[] r = new MyNode[n - mid];
//
//        for (int i = 0; i < mid; i++) {
//            l[i] = a[i];
//        }
//        for (int i = mid; i < n; i++) {
//            r[i - mid] = a[i];
//        }
//        mergeSort(l, mid);
//        mergeSort(r, n - mid);
//
//        merge(a, l, r, mid, n - mid);
//    }
//
//    private void merge(MyNode[] a, MyNode[] l, MyNode[] r, int left, int right) {
//
//        int i = 0, j = 0, k = 0;
//        while (i < left && j < right) {
//            if (l[i].getFinish_time() >= r[j].getFinish_time()) {
//                a[k++] = l[i++];
//            }
//            else {
//                a[k++] = r[j++];
//            }
//        }
//        while (i < left) {
//            a[k++] = l[i++];
//        }
//        while (j < right) {
//            a[k++] = r[j++];
//        }
//    }

    private void Dijkstra(MyNode src_node) {
        List<NodeData> queue = new LinkedList<>();
        Iterator<NodeData> iter = graph.nodeIter();
        while(iter.hasNext()){
            MyNode n = (MyNode) iter.next();
            n.setPrev(null);
            n.setDistance(Integer.MAX_VALUE);
            queue.add(n);
        }
        src_node.setDistance(0);

        while (queue.size() != 0) {
            int key = getMinDistNodeKey(queue);
            if(key == -1) {
                break;
            }
            MyNode u = (MyNode) graph.getNode(key);
            queue.remove(u);
            Iterator<EdgeData> EdgeIter = graph.edgeIter(u.getKey());
            while (EdgeIter.hasNext()) {
                MyEdge e = (MyEdge) EdgeIter.next();
                if (e.getSrc() == u.getKey()) {
                    MyNode v = (MyNode) graph.getNode(e.getDest());
                    double dis_from_src = u.getDistance() + e.getWeight();
                    if(dis_from_src < v.getDistance()){
                      v.setDistance(dis_from_src);
                      v.setPrev(u);
                    }
                }
            }
        }
    }

//    private double Dijkstra_center(MyNode src_node) {
//        double ans = -1;
//        List<NodeData> queue = new LinkedList<>();
//        Iterator<NodeData> iter = graph.nodeIter();
//        while(iter.hasNext()){
//            MyNode n = (MyNode) iter.next();
//            n.setPrev(null);
//            n.setDistance(Integer.MAX_VALUE);
//            queue.add(n);
//        }
//        src_node.setDistance(0);
//        while (queue.size() != 0) {
//            int key = getMinDistNodeKey(queue);
//            if(key == -1) {
//                break;
//            }
//            MyNode u = (MyNode) graph.getNode(key);
//            queue.remove(u);
//            Iterator<EdgeData> EdgeIter = graph.edgeIter(u.getKey());
//            while (EdgeIter.hasNext()) {
//                MyEdge e = (MyEdge) EdgeIter.next();
//                if (e.getSrc() == u.getKey()) {
//                    MyNode v = (MyNode) graph.getNode(e.getDest());
//                    double dis_from_src = u.getDistance() + e.getWeight();
//                    if(dis_from_src < v.getDistance()){
//                        v.setDistance(dis_from_src);
//                        v.setPrev(u);
//                        if (v.getDistance() > ans) {
//                            ans = v.getDistance();
//                        }
//                    }
//                }
//            }
//        }
//        return ans;
//    }

    public void Dijkstra_TSP(MyNode S, DirectedWeightedGraphImpl G) {
        List<NodeData> Que = new LinkedList<>();
        Iterator<NodeData> iter = G.nodeIter();
        while (iter.hasNext()) {
            MyNode n = (MyNode) iter.next();
            n.setDistance(Integer.MAX_VALUE);
            n.setPrev(null);
            Que.add(n);
        }
        S.setDistance(0);
        while (Que.size() > 0) {
            int node_index = getMinDistNodeKey(Que);
            if (node_index == -1) {
                break;
            }
            MyNode u = (MyNode) Que.get(node_index);
            Que.remove(node_index);
            Iterator<EdgeData> EdgeIter = G.edgeIter(u.getKey());
            while (EdgeIter.hasNext()) {
                MyEdge e = (MyEdge) EdgeIter.next();
                if (e.getSrc() == u.getKey()) {
                    MyNode v = (MyNode) G.getNode(e.getDest());
                    double dis_from_src = u.getDistance() + e.getWeight();
                    if(dis_from_src < v.getDistance()){
                        v.setDistance(dis_from_src);
                        v.setPrev(u);
                    }
                }
            }
        }
    }

    public List<NodeData> shortestPath_TSP(int src, int dest, DirectedWeightedGraphImpl G) {
        List<NodeData> list = new LinkedList<>();
        Dijkstra_TSP((MyNode) G.getNode(src), G);
        MyNode temp = (MyNode) G.getNode(dest);
        if (temp.getDistance() == Integer.MAX_VALUE) {
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

    public int getMinDistNodeKey(List<NodeData> L) {
        int ans = -1;
        double dist = Integer.MAX_VALUE;
        for (NodeData nodeData : L) {
            if (((MyNode) nodeData).getDistance() < dist) {
                dist = ((MyNode) nodeData).getDistance();
                ans = nodeData.getKey();
            }
        }
        return ans;
    }

    public void BFS(MyNode s) {
        Iterator<NodeData> node_iter = graph.nodeIter();
        while (node_iter.hasNext()) {
            MyNode n = (MyNode) node_iter.next();
            n.setDistance(Integer.MAX_VALUE);
            n.setTag(White);
        }
        s.setTag(Grey);
        s.setDistance(0);
        List<NodeData> queue = new LinkedList<>();
        queue.add(s);
        while (queue.size() > 0) {
            MyNode u = (MyNode) queue.remove(0);
            Iterator<EdgeData> edge_iter = graph.edgeIter(u.getKey());
            while (edge_iter.hasNext()) {
                MyEdge e = (MyEdge) edge_iter.next();
                if (e.getSrc() == u.getKey()) {
                    MyNode v = (MyNode) graph.getNode(e.getDest());
                    if (v.getTag() == White) {
                        v.setTag(Grey);
                        v.setDistance(u.getDistance()+1);
                        queue.add(v);
                    }
                }
            }
            u.setTag(Black);
        }
    }
}
