

public class ExampleDriver
{
    public static void main(String[] args)
    {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        for (int i = 0; i < 10; ++i) {
            tree.insert(i);
        }

        System.out.println(tree.toString());
    }
}
