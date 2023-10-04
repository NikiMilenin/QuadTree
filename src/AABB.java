public class AABB {
    private final Dot topLeft;
    private final double width;
    private final double height;


    public AABB(Dot topLeft, double width, double height) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
    }


    public AABB(Dot topLeft, Dot bottomRight) {
        this.topLeft = topLeft;
        this.width = bottomRight.getX() - topLeft.getX();
        this.height = bottomRight.getY() - topLeft.getY();
    }

    public double getLeft() {
        return topLeft.getX();
    }

    public double getRight() {
        return topLeft.getX() + width;
    }

    public double getTop() {
        return topLeft.getY();
    }

    public double getBottom() {
        return topLeft.getY() + height;
    }

    public Dot getCenter() {
        return new Dot(topLeft.getX() + width / 2, topLeft.getY() + height / 2);
    }

    public Dot getTopLeft() {
        return topLeft;
    }

    public Dot getSize() {
        return new Dot(width, height);
    }

    public boolean contains(AABB otherBox) {
        return (this.getLeft() <= otherBox.getLeft()  && this.getRight() >= otherBox.getRight() &&
                this.getTop()  <= otherBox.getTop() && this.getBottom() >= otherBox.getBottom());
    }

    public boolean intersects(AABB otherBox) {
        return !(this.getLeft() >= otherBox.getRight()  || this.getRight() <= otherBox.getLeft() ||
                this.getTop()  >= otherBox.getBottom() || this.getBottom() <= otherBox.getTop());
    }

    public boolean inRange(int x, int y) {
        return (x >= this.getLeft() && x <= this.getRight()
                && y >= this.getTop() && y <= this.getBottom());
    }
}
