package Searching.BalancedSearchTrees;

import Fundamentals.Bags_Queues_Stacks.Queue;
import Searching.ElementarySymbolTables.ST;
import Stuff.ArrayFactor;
import Stuff.Rand;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.NoSuchElementException;

public class RedBlackBST<Key extends Comparable<Key>, Value>
    extends ST<Key, Value>
{
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private RB_Node root;

    private class RB_Node
    {
        Key key;
        Value val;
        RB_Node left, right;
        int N;
        boolean color;

        public RB_Node(Key key, Value val, int N, boolean color)
        {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    public int size()
    {
        return root == null ? 0 : root.N;
    }

    public boolean isEmpty()
    {
        return size() == 0;
    }

    public int height()
    {
        return height(root);
    }

    public void draw()
    {
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale((Integer) min() - 1, (Integer) max() + 1);
        StdDraw.setYscale(-1, height() + 1);
        draw(root, height());
    }
    public void drawAndShow()
    {
        draw();
        StdDraw.show();
    }

    private void draw(RB_Node h, Integer height)
    {
        if (h == null) return;
        Point2D node = new Point2D((Integer) (h.key), height);
        StdDraw.setPenRadius(0.006D);
        StdDraw.point(node.x(), node.y());
        if (h.left != null)
        {
            Point2D left = new Point2D((Integer) (h.left.key), height - 1);
            StdDraw.setPenRadius();
            if (isRed(h.left)) StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.x(), node.y(), left.x(), left.y());
            StdDraw.setPenRadius(0.006D);
            StdDraw.setPenColor(StdDraw.BLACK);
            draw(h.left, height - 1);
        }
        if (h.right != null)
        {
            Point2D right = new Point2D((Integer) (h.right.key), height - 1);
            StdDraw.setPenRadius();
            StdDraw.line(node.x(), node.y(), right.x(), right.y());
            StdDraw.setPenRadius(0.006D);
            draw(h.right, height - 1);
        }
    }

    /**
     * @return object to travel through all the tree
     */
    public Iterable<Key> keys()
    {
        return InOrder(min(), max());
    }

    /**
     * @param k teh order statistic
     * @return the kth smallest key in the tree
     */
    public Key select(int k)
    {
        if (k < 0 || k >= root.N) throw new ArrayIndexOutOfBoundsException("array index out of range in BST.select");
        return select(root, k).key;
    }

    /**
     * @param key the key
     * @return the number of keys in the tree strictly less than {@code key}
     */
    public int rank(Key key)
    {
        if (key == null)
            throw new IllegalArgumentException("cannot get rank of null in BST");
        return rank(root, key);
    }

    public Key min()
    {
        return min(root).key;
    }

    public Key max()
    {
        return max(root).key;
    }

    public Key floor(Key key)
    {
        if (key == null) return null;
        RB_Node node = floor(root, key);
        if (node == null) return null;
        return node.key;
    }

    public Key ceiling(Key key)
    {
        if (key == null) return null;
        RB_Node node = ceiling(root, key);
        if (node == null) return null;
        return node.key;
    }

    private boolean isRed(RB_Node x)
    {
        return x != null && x.color == RED;
    }

    private int size(RB_Node x)
    {
        return x == null ? 0 : x.N;
    }

    /**
     * @return the height of the BST (a 1-node tree has height 0)
     */
    private int height(RB_Node h)
    {
        if (h == null) return -1;
        return 1 + Math.max(height(h.left), height(h.right));
    }

    // the key of rank k in the subtree rooted at rot
    private RB_Node select(RB_Node rot, int k)
    {
        if (rot == null) return null;
        if (k < size(rot.left)) return select(rot.left, k);
        if (k > size(rot.left)) return select(rot.right, k - size(rot.left) - 1);
        return rot;
    }

    private int rank(RB_Node x, Key key)
    {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp > 0) return 1 + size(x.left) + rank(x.right, key);
        if (cmp < 0) return rank(x.left, key);
        return size(x.left);
    }

    /**
     * Return the node with min key of root <code> x </code>
     * <code> x </code> cannot be null
     *
     * @param x the root of tree to find min node
     * @return the ndoe with min key of root <code> x </code>
     * @throws NullPointerException if <code>x</code>is null;
     */
    private RB_Node min(RB_Node x)
    {
        if (x.left == null) return x;
        return min(x.left);
    }

    /**
     * Return the node with max key of root<code>x</code>
     *
     * @param x the root of tree to find max in
     * @return the max key of root <code>x</code>
     * @see #min(RB_Node)
     */
    private RB_Node max(RB_Node x)
    {
        if (x.right == null) return x;
        return max(x.right);
    }

    /**
     * @return the most large node of nodes less than <code>key</code> in tree <code>x</code>
     * @see #floor(RB_Node, Comparable) almost same logic
     */
    private RB_Node ceiling(RB_Node x, Key key)
    {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return x.right == null ? x : floor(x.right, key);
        RB_Node node = floor(x.left, key);
        if (node != null) return node;
        return x;
    }

    /**
     * @param x   the root of tree to find
     * @param key the bound
     * @return the upper bound of InOrder not large than <code> key </code>
     */
    private RB_Node floor(RB_Node x, Key key)
    {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return x.left == null ? x : floor(x.left, key);

        RB_Node node = floor(x.right, key);
        if (node != null) return node;
        else return x;
    }

    private Iterable<Key> InOrder(Key lo, Key hi)
    {
        Queue<Key> q = new Queue<>();
        InOrder(root, q, lo, hi);
        return q;
    }

    /**
     * InOrder traverse the tree <code>x</code> and push nodes in range [lo, hi] to <code>queue</code>
     *
     * @param x     the tree to traverse
     * @param queue store the nodes
     * @param lo    lower bound of nodes to push
     * @param hi    upper bound of nodes to push
     */
    private void InOrder(RB_Node x, Queue<Key> queue, Key lo, Key hi)
    {
        if (x == null) return;
        int cmpLo = lo.compareTo(x.key);
        int cmpHi = hi.compareTo(x.key);
        if (cmpLo < 0) InOrder(x.left, queue, lo, hi);
        if (cmpLo <= 0 && cmpHi >= 0) queue.enqueue(x.key);
        if (cmpHi > 0) InOrder(x.right, queue, lo, hi);
    }

// Change content of tree ==============================================================================================

    /**
     * Called only if the color of <code>h</code>'s right link is RED (color and pos of h does not matter!)
     * Change the color of <code>h</code>'s right link and left link
     *
     * @param h the node to rotate
     * @return new tree after rotate to change link of parent
     */
    private RB_Node rotateLeft(RB_Node h)
    {
        RB_Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    /**
     * @param h the node to rotate
     * @return new tree after rotate to change link of parent
     * @see #rotateLeft(RB_Node) almost same, only switch left and right in the process
     */
    private RB_Node rotateRight(RB_Node h)
    {
        RB_Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.right) + size(h.left) + 1;
        return x;
    }

    private void flipColors(RB_Node h)
    {
        // assure h have opposite color of its two children
        assert (h != null) && (h.left != null) && (h.right != null);
        assert (!isRed(h) && isRed(h.left) && isRed(h.right)) ||
                (isRed(h) && !isRed(h.left) && !isRed(h.right));
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    public void put(Key key, Value val)
    {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        root = put(root, key, val);
        root.color = BLACK;
    }

    private RB_Node put(RB_Node h, Key key, Value val)
    {
        if (h == null) return new RB_Node(key, val, 1, RED);

        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left)  &&  isRed(h.right)) flipColors(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    private RB_Node moveRedLeft(RB_Node h)
    {
        assert (h != null);
        assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);
        // h is Red, h.left and h.left.left are Black
        // change h.left or one of h.left's children to Red
        flipColors(h);
        if (isRed(h.right.left))
        {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }


    public boolean check()
    {
        return check(root);
    }

    public void deleteMin()
    {
        // if both children of root are black, set root to red(make a temporary three node)
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
        assert check();
    }

    // delete the key-value pair with the minimum key rooted at h
    private RB_Node deleteMin(RB_Node h)
    {
        if (h.left == null) return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }

    private RB_Node balance(RB_Node h)
    {
        assert (h != null);

        if (isRed(h.right))                         h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left))    h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))        flipColors(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    public boolean contains(Key key)
    {
        return !isEmpty() && get(key) != null;
    }

    public Value get(Key key)
    {
        if (key == null) throw new IllegalArgumentException("null key to call get");
        if (isEmpty()) throw new NoSuchElementException("get from empty bst");
        return get(root, key);
    }

    private Value get(RB_Node h, Key key)
    {
        if (h == null) return null;

        int cmp = key.compareTo(h.key);
        if (cmp > 0)    return get(h.right, key);
        if (cmp < 0)    return get(h.left, key);
        return h.val;
    }

    /**
     * Remove the specified key and its associated value from the symbol table
     * (if the key is in the symbol table)
     * @param key the key
     * @throws IllegalArgumentException if <code>key</code> is <code>null</code>
     */
    public void delete(Key key)
    {
        if (key == null) throw new IllegalArgumentException("argument to delete is null");
        if (!contains(key)) return;

        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
        assert check();
    }

    // delete the key-value pair with the given key rooted at h
    private RB_Node delete(RB_Node h, Key key)
    {
        if (key.compareTo(h.key) < 0)
        {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }
        else
        {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0)
            {
                RB_Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }

    public void deleteMax()
    {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
        assert check();
    }

    private RB_Node deleteMax(RB_Node h)
    {
        if (isRed(h.left))
            h = rotateRight(h);

        if (h.right == null)
            return null;

        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);
        h.right = deleteMax(h.right);
        return balance(h);
    }

    private RB_Node moveRedRight(RB_Node h)
    {
        flipColors(h);
        if (isRed(h.left.left))
        {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }



// Check RB_Tre property  ==============================================================================================

    private boolean check(RB_Node h)
    {
        if (h == null) return true;
        for (int i = 0; i < size(h); ++i)
            if (i != rank(select(i)))
                return false;
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0)
                return false;
        return true;
    }


    public boolean is23()
    {
        return is23(root);
    }

    private boolean is23(RB_Node h)
    {
        if (h == null) return true;
        if (isRed(h.right)) return false;
        if (isRed(h.left) && isRed(h.left.left)) return false;
        return is23(h.left) && is23(h.right);
    }

    // do all paths from root to leaf have same number of black edges?
    private boolean isBalanced()
    {
        int black = countBlack(root);     // number of black links on path from root to min
        return isBalanced(root, black);
    }

    private int countBlack(RB_Node x)
    {
        int black = 0;
        while (x != null)
        {
            if (!isRed(x)) black++;
            x = x.left;
        }
        return black;
    }

    // does every path from the root to a leaf have the given number of black links?
    private boolean isBalanced(RB_Node x, int black) {
        if (x == null) return black == 0;
        if (!isRed(x)) black--;
        return isBalanced(x.left, black) && isBalanced(x.right, black);
    }

    private int countMostLeftBlack(RB_Node h)
    {
        if (h == null)  return -1;
        return (isRed(h) ? 0 : 1) + countMostLeftBlack(h.left);

    }

    public boolean isBinaryTree()
    {
        return isBinaryTree(root);
    }
    private boolean isBinaryTree(RB_Node h)
    {
        if (h == null) return true;
        return isBinaryTree(h.left) &&
                isBinaryTree(h.right) &&
                size(h) == size(h.left) + size(h.right) + 1;
    }

    public boolean isOrdered()
    {
        return isOrdered(root);
    }

    private boolean isOrdered(RB_Node h)
    {
        if (h == null) return true;
        if (!isOrdered(h.left)) return false;
        if (!isOrdered(h.right)) return false;
        if (h.left != null && h.key.compareTo(h.left.key) <= 0)
            return false;
        if (h.right != null && h.key.compareTo(h.right.key) >= 0)
            return false;
        return true;
    }

    public boolean isOrdered(RB_Node h, Key min, Key max)
    {
        if (!isOrdered(h)) return false;
        return min().compareTo(min) == 0 && max().compareTo(max) == 0;
    }

    private boolean hasNoDuplicates(RB_Node h)
    {
        if (h == null)  return true;
        if (h.left != null && h.key.compareTo(h.left.key)  == 0 ) return false;
        if (h.right!= null && h.key.compareTo(h.right.key) == 0 ) return false;
        if (h.left != null && h.right != null && h.left.key.compareTo(h.right.key)  == 0 ) return false;

        return hasNoDuplicates(h.left) && hasNoDuplicates(h.right);
    }

    public boolean isBST()
    {
        return isBST(root);
    }

    private boolean isBST(RB_Node h)
    {
        return  isBinaryTree(h) &&
                isOrdered(h, min(), max())&&
                hasNoDuplicates(h);
    }

    public boolean isRedBlackBST()
    {
        return isBST() && is23() && isBalanced();
    }

    private boolean isRedBlackBST(RB_Node h)
    {
        return isBST(h) && is23(h) && isBalanced(h, countBlack(h));
    }

    public static void main(String[] argv)
    {
        RedBlackBST<Integer, Integer> rb_bst = new RedBlackBST<>();

        for (int i = 0; i < 10; ++i)
            rb_bst.put(2*i + 1, i);
        for (int i = 0; i < 10; ++i)
            System.out.println(rb_bst.rank(i));
        System.out.println("-----------------------");
        for (int i = 0; i < 10; ++i)
            System.out.println(rb_bst.select(i));

    }
}
