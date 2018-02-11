package Searching.BalancedSearchTrees;

import Fundamentals.Bags_Queues_Stacks.Queue;
import Searching.ElementarySymbolTables.ST;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;

public class BST<Key extends Comparable<Key>, Value>
    extends ST<Key, Value>
{
    class Node
    {
        Key key;
        Value value;
        Node left;
        Node right;
        int N;

        public Node(Key key, Value value, int n)
        {
            this.key = key;
            this.value = value;
            N = n;
        }
    }

    private Node root;

    public BST()
    { }

    public int size()
    {
        return root.N;
    }

    int size(Node x)
    {
        if (x == null)  return 0;
        return x.N;
    }

    public Value get(Key key)
    {
        if (key == null)    return null;
        Node node = root;
        while (node != null)
        {
            int cmp = key.compareTo(node.key);
            if      (cmp > 0) node = node.right;
            else if (cmp < 0) node = node.left;
            else return node.value;
        }
        return null;
    }

    public void put(Key key, Value val)
    {
        if (key == null)    return;
        if (val == null) {delete(key); return;}

        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val)
    {
        if (x == null) return new Node(key, val, 1);

        int cmp = key.compareTo(x.key);
        if      (cmp > 0) x.right = put(x.right, key, val);
        else if (cmp < 0) x.left  = put(x.left, key, val);
        else x.value = val;

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public boolean isEmpty()
    {
        return size(root) == 0;
    }

    public boolean contains(Key key)
    {
        return get(key) != null;
    }

    /**
     * Return object to travel through all the tree
     * @return object to travel through all the tree
     */
    public Iterable<Key> keys()
    {
        return keysInOrder(min(), max());
    }

    private Iterable<Key> keysInOrder(Key lo, Key hi)
    {
        Queue<Key> q = new Queue<>();
        keysInOrder(root, q, lo, hi);
        return q;
    }

    /**
     * InOrder traverse the tree <code>x</code> and push nodes in range [lo, hi] to <code>queue</code>
     * @param x the tree to traverse
     * @param queue store the nodes
     * @param lo lower bound of nodes to push
     * @param hi upper bound of nodes to push
     */
    private void keysInOrder(Node x, Queue<Key> queue, Key lo, Key hi)
    {
        if (x == null)  return;
        int cmpLo = lo.compareTo(x.key);
        int cmpHi = hi.compareTo(x.key);
        if (cmpLo < 0)  keysInOrder(x.left, queue, lo, hi);
        if (cmpLo <= 0 && cmpHi >= 0) queue.enqueue(x.key);
        if (cmpHi > 0)  keysInOrder(x.right, queue, lo, hi);
    }

    private void in_order(Queue<Key> q, Node x)
    {
        if (x == null) return;
        in_order(q, x.left);
        q.enqueue(x.key);
        in_order(q, x.right);
    }

    public Key select(int k)
    {
        if (k < 0 || k >= root.N) throw new ArrayIndexOutOfBoundsException("array index out of range in BST.select");
        return select(root, k).key;
    }

    private Node select(Node x, int k)
    {
        if (x == null)         return null;
        if (k < size(x.left))  return select(x.left, k);
        if (k > size(x.left))  return select(x.right, k - size(x.left) - 1);
        return x;
    }

    public int rank(Key key)
    {
        if (key == null)
            throw new IllegalArgumentException("cannot get rank of null in BST");
        return rank(root, key);
    }
    private int rank(Node x, Key key)
    {
        if (x == null)  return 0;
        int cmp = key.compareTo(x.key);
        if (cmp > 0)    return 1 + size(x.left) + rank(x.right, key);
        if (cmp < 0)    return rank(x.left, key);
        return size(x.left);
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
        Node node = floor(root, key);
        if (node == null)   return null;
        return node.key;
    }

    public Key ceiling(Key key)
    {
        if (key == null)    return null;
        Node node = ceiling(root, key);
        if (node == null)   return null;
        return node.key;
    }

    public void deleteMin()
    {
        if (isEmpty()) throw new UnsupportedOperationException("cannot delete from empty BST");
        root = deleteMin(root);
    }

    public void deleteMax()
    {
        if (isEmpty()) throw new UnsupportedOperationException("cannot delete from empty BST");
        root = deleteMax(root);
    }

    public int height()
    {
        return height(root);
    }

    private int height(Node h)
    {
        if (h == null) return -1;
        if (h.left == null && h.right == null) return 0;

        int h_l = height(h.left);
        int h_r = height(h.right);
        return 1 + (h_l > h_r ? h_l : h_r);
    }


    public void draw()
    {
        int height = height();

        StdDraw.setXscale((Integer) min() - 1, (Integer) max() + 1);
        StdDraw.setYscale(-1, height + 1);
        StdDraw.enableDoubleBuffering();

        draw(root, height);

        StdDraw.setFont(new Font("SansSerif", 0, 26));
        StdDraw.text(10, 1, "height : " + height);
        StdDraw.setFont();
        StdDraw.show();
    }

    private void draw(Node h, Integer height)
    {
        if (h == null) return;
        Point2D node = new Point2D((Integer) (h.key), height);
        StdDraw.setPenRadius(0.008D);
        StdDraw.point(node.x(), node.y());
        if (h.left != null)
        {
            Point2D left = new Point2D((Integer) (h.left.key), height - 1);
            StdDraw.setPenRadius();
            StdDraw.line(node.x(), node.y(), left.x(), left.y());
            StdDraw.setPenRadius(0.008D);
            StdDraw.setPenColor(StdDraw.BLACK);
            draw(h.left, height - 1);
        }
        if (h.right != null)
        {
            Point2D right = new Point2D((Integer) (h.right.key), height + (-1));
            StdDraw.setPenRadius();
            StdDraw.line(node.x(), node.y(), right.x(), right.y());
            StdDraw.setPenRadius(0.006D);
            draw(h.right, height - 1);
        }
    }

    /**
     * Delete <code>key</code> from this tree
     * @param key the key to delete
     * @throws IllegalArgumentException if <code>key</code> is null
     * @throws UnsupportedOperationException if empty
     */
    public void delete(Key key)
    {
        if (isEmpty()) throw new UnsupportedOperationException("cannot delete from empty BST");
        if (key == null)
            throw new IllegalArgumentException("cannot delete null in BST");
        root = delete(root, key);
    }

    private Node deleteMin(Node x)
    {
        if (x.left == null)     return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node deleteMax(Node x)
    {
        if (x.right == null)    return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * Return a copy of <code>x</code> with <code>key</code> deleted
     * <code>key</code> must NOT be null, otherwise will cause a nullPointerException
     * @param x the tree to find node to delete
     * @param key the key to delete
     * @return a copy of <code>x</code> with <code>key</code> deleted
     * @throws NullPointerException if <code>key</code> is null
     */
    private Node delete(Node x, Key key)
    {
        if (x == null)  return null;

        int cmp = key.compareTo(x.key);
        if      (cmp > 0)   x.right = delete(x.right, key);
        else if (cmp < 0)   x.left  = delete(x.left,  key);
        else
        {
            if (x.left  == null)   return x.right;
            if (x.right == null)   return x.left;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * @see #floor(Node, Comparable) almost same logic
     * @return the most large node of nodes less than <code>key</code> in tree <code>x</code>
     */
    private Node ceiling(Node x, Key key)
    {
        if (x == null)  return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)   return x;
        if (cmp >  0)   return x.right == null ? x : floor(x.right, key);
        Node node = floor(x.left, key);
        if (node != null)   return node;
        return x;
    }

    /**
     * Return the upper bound of keysInOrder not large than <code> key </code>
     * @param x the root of tree to find
     * @param key the bound
     * @return the upper bound of keysInOrder not large than <code> key </code>
     */
    private Node floor(Node x, Key key)
    {
       if (x == null) return null;
       int cmp = key.compareTo(x.key);
       if (cmp == 0) return x;
       if (cmp <  0) return x.left == null ? x : floor(x.left, key);

       Node node = floor(x.right, key);
       if (node != null) return node;
       else              return x;
    }

    /**
     * Return the node with min key of root <code> x </code>
     * <code> x </code> cannot be null
     * @param x the root of tree to find min node
     * @return the ndoe with min key of root <code> x </code>
     * @throws NullPointerException if <code>x</code>is null;
     */
    private Node min(Node x)
    {
        if (x.left == null) return x;
        return min(x.left);
    }

    /**
     * Return the node with max key of root<code>x</code>
     * @see #min(Node)
     * @param x the root of tree to find max in
     * @return the max key of root <code>x</code>
     */
    private Node max(Node x)
    {
        if (x.right == null) return x;
        return max(x.right);
    }

    public static void main(String[] argv)
    {
        final int size = 4000;
        Integer[] integers = new Integer[size];
        for (int i = 0; i < size; ++i)
            integers[i] = StdRandom.uniform(0, 4000);
        BST<Integer, Integer> bst = new BST<>();
        StdRandom.shuffle(integers);
        StdRandom.shuffle(integers);
        StdRandom.shuffle(integers);
        for (int i = 0; i < size; ++i)
        {
            bst.put(integers[i], integers[i]);
            StdDraw.clear();
            if (i % 1000 == 0)
                bst.draw();
            StdDraw.pause(0);
        }

        System.out.println("Start delete.");
        long cnt = 0;
        long t = 0;
        long index = 0;
        while (true)
        {
            int val = StdRandom.uniform(0, 8000);
            bst.delete(val);
            val = StdRandom.uniform(0, 8000);
            bst.put(val, val);


            StdDraw.clear();

            if (cnt++ % 10000 == 0)
            {
                System.out.println("show");
                System.out.println("delete " + t + ". size : " + bst.size());
                bst.draw();
//                StdDraw.save("bst" + index++ + ".jpg");
                cnt = 1;
            }
            ++t;
        }
    }
}
