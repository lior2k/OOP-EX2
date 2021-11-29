package api;


public class MyEdge implements EdgeData {
    private MyPair pair;
    private int tag;
    private double weight;
    private String info;

    public MyEdge(int src, int dest, int tag, double weight) {
        this.pair = new MyPair(src, dest);
        this.tag = tag;
        this.weight = weight;
        this.info = "[Edge: from: "+pair.getLeft()+" to: "+pair.getRight()+" color: "+tag+" weight: "+weight+"]";
    }
    @Override
    public int getSrc() {
        return this.pair.getLeft();
    }

    @Override
    public int getDest() {
        return this.pair.getRight();
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {

    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }

    public MyPair getPair() {
        return this.pair;
    }

    public String toString() {
        return this.info;
    }

    public MyEdge copy() {
        MyEdge tempedge = new MyEdge(this.getSrc(), this.getDest(), this.tag, this.weight);
        tempedge.setInfo(this.info);
        return tempedge;
    }

    public MyEdge reversedEdge() {
        MyEdge tempedge = new MyEdge(this.getDest(), this.getSrc(), this.tag, this.weight);
        tempedge.setInfo("[Edge: from: "+pair.getRight()+" to: "+pair.getLeft()+" color: "+tag+" weight: "+weight+"]");
        return tempedge;
    }
}