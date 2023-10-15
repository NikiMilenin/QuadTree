import java.util.ArrayList;
import java.util.Iterator;

public class QuadTree
{
    private final int nodeCapacity = 1;
    int level = 0;
    private ArrayList<Dot> dots;

    private QuadTree northWest;
    private QuadTree northEast;
    private QuadTree southEast;
    private QuadTree southWest;
    private AABB boundry;

    public QuadTree(int level, double width, double height) {
        dots = new ArrayList<>();
        this.level = level;
        this.boundry = new AABB(new Dot(0, 0), width, height);
        this.northWest = null;
        this.northWest = null;
    }

    public QuadTree(int level, AABB boundary) {
        this.level = level;
        this.boundry = boundary;
        dots = new ArrayList<>();
    }

    public ArrayList<Dot> getDots() {
        return dots;
    }

    public void subdivide() {
        Dot center = this.boundry.getCenter();
        Dot size = this.boundry.getSize();
        double new_width = size.getX() / 2;
        double new_height = size.getY() / 2;


        northWest = new QuadTree(this.level + 1, new AABB(this.boundry.getTopLeft(), center));
        northEast = new QuadTree(this.level + 1, new AABB(new Dot(center.getX() , this.boundry.getTopLeft().getY()),
                new_width, new_height));
        southWest = new QuadTree(this.level + 1, new AABB(new Dot(this.boundry.getTopLeft().getX(), center.getY()),
                new_width, new_height));
        southEast = new QuadTree(this.level + 1, new AABB(center, new_width, new_height));
    }

    public void insert(int x, int y) {
        if (!this.boundry.inRange(x, y)) {
            return;
        }

        Dot new_dot = new Dot(x, y);
        for (Dot d:dots) {
            if (d.getX() == new_dot.getX() && d.getY() == new_dot.getY()) return;
        }
        if (dots.size() < nodeCapacity) {
            dots.add(new_dot);
            return;
        }

        if (northWest == null) subdivide();
        if (this.northWest.boundry.inRange(x, y))
            this.northWest.insert(x, y);
        else if (this.northEast.boundry.inRange(x, y))
            this.northEast.insert(x, y);
        else if (this.southWest.boundry.inRange(x, y))
            this.southWest.insert(x, y);
        else if (this.southEast.boundry.inRange(x, y))
            this.southEast.insert(x, y);
        else
            System.out.printf("ERROR : Unhandled partition %d %d\n", x, y);
    }

    public ArrayList<Dot> DFS() {
        ArrayList<Dot> result = new ArrayList<>();
        DFSIteration(this, result);
        return result;
    }

    private void DFSIteration(QuadTree tree, ArrayList<Dot> result) {
        if (tree == null) return;
        result.addAll(tree.getDots());

        DFSIteration(tree.northWest, result);
        DFSIteration(tree.northEast, result);
        DFSIteration(tree.southWest, result);
        DFSIteration(tree.southEast, result);
    }

}