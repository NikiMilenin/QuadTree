public class Dot
{
    private double x;
    private double y;

    public Dot(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void sum(Dot otherDot) {
        x  += otherDot.x;
        y  += otherDot.y;
    }

    public void sub(Dot otherDot) {
        x  -= otherDot.x;
        y  -= otherDot.y;
    }
}
