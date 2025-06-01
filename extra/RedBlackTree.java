/**
 * Generic Red–Black Tree implementation in Java.
 *
 * @param <T> Type of keys stored in the tree; must be Comparable.
 */
public class RedBlackTree<T extends Comparable<T>> {

    /** Node colors */
    private enum Color { RED, BLACK }

    /** Tree node */
    private class Node {
        T key;
        Color color;
        Node left, right, parent;

        Node(T key, Color color) {
            this.key = key;
            this.color = color;
            this.left = NIL;
            this.right = NIL;
            this.parent = NIL;
        }
    }

    /** Sentinel NIL node, shared by all real nodes as their leaf references */
    private final Node NIL = new Node(null, Color.BLACK);

    /** Root of the Red–Black Tree */
    private Node root = NIL;

    /** Public constructor */
    public RedBlackTree() {
        root = NIL;
    }


    /**
     * Inserts a new key into the Red–Black Tree.
     * @param key the value to insert
     */
    public void insert(T key) {
        Node newNode = new Node(key, Color.RED);
        insertBST(newNode);
        insertFixup(newNode);
    }

    /**
     * Checks whether a given key exists in the tree.
     * @param key the value to search for
     * @return true if found, false otherwise
     */
    public boolean contains(T key) {
        return search(root, key) != NIL;
    }

    /**
     * Performs inorder traversal and prints keys in ascending order.
     */
    public void inorderTraversal() {
        inorderHelper(root);
        System.out.println();
    }

    /** Standard BST‐style insertion (without rebalancing). */
    private void insertBST(Node z) {
        Node y = NIL;
        Node x = root;

        while (x != NIL) {
            y = x;
            if (z.key.compareTo(x.key) < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;

        if (y == NIL) {
            root = z;
        } else if (z.key.compareTo(y.key) < 0) {
            y.left = z;
        } else {
            y.right = z;
        }

        z.left = NIL;
        z.right = NIL;
        z.color = Color.RED;
    }

    /** Fixup routine to restore Red–Black properties after insertion. */
    private void insertFixup(Node z) {
        while (z.parent.color == Color.RED) {
            if (z.parent == z.parent.parent.left) {
                Node y = z.parent.parent.right; // uncle
                // Case 1: Uncle is RED → recolor parent & uncle to BLACK, grandparent to RED
                if (y.color == Color.RED) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    // Case 2: Uncle is BLACK and z is right‐child → left‐rotate parent
                    if (z == z.parent.right) {
                        z = z.parent;
                        leftRotate(z);
                    }
                    // Case 3: Uncle is BLACK and z is left‐child → recolor & right‐rotate grandparent
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    rightRotate(z.parent.parent);
                }
            } else {
                // Mirror cases: parent is right‐child of grandparent
                Node y = z.parent.parent.left; // uncle
                if (y.color == Color.RED) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    leftRotate(z.parent.parent);
                }
            }
            if (z == root) break;
        }
        root.color = Color.BLACK; // Ensure root is always BLACK
    }

    /** Left‐rotate sub­tree rooted at x. */
    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != NIL) {
            y.left.parent = x;
        }
        y.parent = x.parent;

        if (x.parent == NIL) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }

        y.left = x;
        x.parent = y;
    }

    /** Right‐rotate sub­tree rooted at y. */
    private void rightRotate(Node y) {
        Node x = y.left;
        y.left = x.right;
        if (x.right != NIL) {
            x.right.parent = y;
        }
        x.parent = y.parent;

        if (y.parent == NIL) {
            root = x;
        } else if (y == y.parent.right) {
            y.parent.right = x;
        } else {
            y.parent.left = x;
        }

        x.right = y;
        y.parent = x;
    }

    /** Standard BST search: returns the node containing key, or NIL if not found. */
    private Node search(Node node, T key) {
        while (node != NIL && key.compareTo(node.key) != 0) {
            if (key.compareTo(node.key) < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node;
    }

    /** Inorder traversal helper (prints keys in sorted order). */
    private void inorderHelper(Node node) {
        if (node != NIL) {
            inorderHelper(node.left);
            System.out.print(node.key + " ");
            inorderHelper(node.right);
        }
    }
    /**
     * Main method to demonstrate the Red–Black Tree functionality.
     */
    public static void main(String[] args) {
        RedBlackTree<Integer> rbt = new RedBlackTree<>();

        int[] values = { 10, 20, 30, 15, 25, 5, 1 };
        for (int v : values) {
            rbt.insert(v);
        }

        System.out.print("Inorder traversal: ");
        rbt.inorderTraversal();  // Should print: 1 5 10 15 20 25 30

        System.out.println("Contains 15? " + rbt.contains(15));
        System.out.println("Contains 99? " + rbt.contains(99));
    }
}
