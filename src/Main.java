public class Main {
    public static void main(String[] args) {
        QuadTree tree = new QuadTree(0, 600, 600);
        tree.insert(210, 470);
        tree.insert(470, 210);
        tree.insert(210, 470);
        tree.insert(210, 470);
        tree.insert(210, 470);



        for (Dot t:tree.DFS()) {
            System.out.println("X: " + t.getX() + " Y: " + t.getY() );
        }
    }
}
