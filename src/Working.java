public class Working {
    public static void main(String[] args) {
        AVLTree avl = new AVLTree();
        avl.add(6);
        avl.remove(6);
        avl.add(6);
        avl.add(3);
        avl.add(9);
        avl.add(7);
        avl.add(8);
        avl.add(2);
        avl.add(0);
        avl.remove(7);
        avl.remove(3);
        avl.remove(6);
        avl.add(1);
    }
}
