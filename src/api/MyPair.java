package api;

import java.util.Objects;

public class MyPair {
    private int left;
    private int right;

    public MyPair(int x,int y){
        this.left = x;
        this.right = y;
    }

    public int getLeft(){
        return this.left;
    }

    public int getRight(){
        return this.right;
    }

    public void reversePair(){
        int temp = this.right;
        this.right = this.left;
        this.left = temp;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof MyPair){
            return ((MyPair) o).left == this.left && ((MyPair) o).right == this.right;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(this.left) + Objects.hashCode(this.right);
    }

}
