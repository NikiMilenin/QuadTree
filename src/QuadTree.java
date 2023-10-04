import java.util.ArrayList;

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
    }

    public QuadTree(int level, AABB boundary) {
        this.level = level;
        this.boundry = boundary;
        dots = new ArrayList<>();
    }

    public void subdivide() {
        Dot center = this.boundry.getCenter();
        Dot size = this.boundry.getSize();
        double new_width = size.getX() / 2;
        double new_height = size.getY() / 2;


        northWest = new QuadTree(this.level + 1, new AABB(this.boundry.getTopLeft(), center));
        northEast = new QuadTree(this.level + 1, new AABB(new Dot(center.getX() + new_width, center.getY()),
                new_width, new_height));
        southWest = new QuadTree(this.level + 1, new AABB(new Dot(center.getX(), center.getY() + new_width),
                new_width, new_height));
        southEast = new QuadTree(this.level + 1, new AABB(center, new_width, new_height));
    }

    public void insert(int x, int y) {
        if (!this.boundry.inRange(x, y)) {
            return;
        }
        Dot new_dot = new Dot(x, y);
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
            System.out.printf("ERROR : Unhandled partition %d %d", x, y);
    }

    public static void DFS(QuadTree tree) {
        if (tree == null) return;
        DFS(tree.northWest);
        DFS(tree.northEast);
        DFS(tree.southWest);
        DFS(tree.northEast);
    }
}
