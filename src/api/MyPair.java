package api;

import java.util.Objects;

public class MyPair {
    private int left;
    private int right;

    public MyPair(int src, int dest) {
        left = src;
        right = dest;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public String toString() {
        return "("+this.left+","+this.right+")";
    }

    @Override
    public boolean equals(Object p){
        if (p instanceof MyPair) {
            return this.right == ((MyPair) p).getRight() && this.left == ((MyPair) p).getLeft();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.right) + Objects.hashCode(this.left);
    }
}