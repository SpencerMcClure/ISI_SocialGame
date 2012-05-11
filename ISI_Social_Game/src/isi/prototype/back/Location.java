public class Location
{

    private double x;
    private double y;

    public Location(double inx, double iny)
    {
        x = inx;
        y = iny;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    
    public double dist(Location l) {
        return Math.sqrt(Math.pow(l.getX() - getX(), 2) + Math.pow(l.getY() - getY(),2));
    }
}
