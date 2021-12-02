package api;

public class GeoLocationImpl implements GeoLocation {
    private double x;
    private double y;
    private double z;

    public GeoLocationImpl(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public double x() {
        return x;
    }

    @Override
    public double y() {
        return y;
    }

    @Override
    public double z() {
        return z;
    }

    @Override
    public double distance(GeoLocation g) {
        double dx = (this.x - g.x())*(this.x - g.x());
        double dy = (this.y - g.x())*(this.x - g.y());
        double dz = (this.z - g.x())*(this.x - g.z());
        return Math.sqrt((dx*dx)+(dy*dy)+(dz*dz));
    }

    public String toString() {
        return x+","+y+","+z;
    }
}
