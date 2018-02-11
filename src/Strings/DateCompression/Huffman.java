package Strings.DateCompression;
// Created by yirany on 2018/2/4

import Sortings.PriorityQueues.MinPQ;

public class Huffman extends DataCompressionBase
{
    private static final boolean ONE = true;
    private static final boolean ZERO = false;

    private static int radix = 256;

    // do not instantiate out of package
    Huffman(){}

    public static void compress()
    {
        // read input
        String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();

        // count frequency
        int[] freq = new int[radix];
        for (char c : input)
            freq[c]++;

        // build tree
        Node root = buildTries(freq);

        // build compile table
        String[] st = buildCode(root);

        // output tries
        writeTries(root);

        // output number of chars
        BinaryStdOut.write(input.length);

        for (char c : input)
        {
            String code = st[c];
            for (int j = 0; j < code.length(); ++j)
            {
                if (code.charAt(j) == '1')
                    BinaryStdOut.writeBit(ONE);
                else
                    BinaryStdOut.writeBit(ZERO);
            }
        }
        BinaryStdOut.close();
        BinaryStdIn.close();
    }

    @Override
    public void doCompress()
    {
        compress();
    }

    @Override
    public void doExpand()
    {
        expand();
    }

    public static void expand()
    {
        Node root = readTries();
        int len = BinaryStdIn.readInt();
        for (int i = 0; i < len; ++i)
        {
            Node x = root;
            while (!x.isLeaf())
            {
                if (BinaryStdIn.readBit())
                    x = x.right;
                else
                    x = x.left;
            }
            BinaryStdOut.writeByte(x.ch);
        }
        BinaryStdOut.close();
        BinaryStdIn.close();
    }

    private static Node buildTries(int[] freq)
    {
        MinPQ<Node>pq = new MinPQ<>();
        for (char c = 0; c < radix; ++c)
            if (freq[c] > 0)
                pq.insert(new Node(c, freq[c], null, null));

        while (pq.size() > 1)
        {
            // 合并频率最小的两棵树
            Node x = pq.getAndDeleteMin();
            Node y = pq.getAndDeleteMin();
            Node parent = new Node('\0', x.frequency + y.frequency, x, y);
            pq.insert(parent);
        }
        return pq.getMin();
    }

    /**
     * Write tree with root {@code rot} in pre-order
     */
    private static void writeTries(Node rot)
    {
        if (rot == null) return;

        if (rot.isLeaf())
        {
            BinaryStdOut.writeBit(ONE);
            BinaryStdOut.writeByte(rot.ch);
            return;
        }

        // write inner node
        BinaryStdOut.writeBit(ZERO);

        // write subtrees
        writeTries(rot.left);
        writeTries(rot.right);
    }

    private static Node readTries()
    {
        // if is leaf node
        if (BinaryStdIn.readBit() == ONE)
            return new Node(BinaryStdIn.readByte(), 0, null, null);
        return new Node ('\0', 0, readTries(), readTries());
    }

    private static String[] buildCode(Node root)
    {
        String[] st = new String[radix];
        buildCode(st, root, "");
        return st;
    }

    private static void buildCode(String[] st, Node rot, String s)
    {
        if (rot.isLeaf())
        {
            st[rot.ch] = s;
            return;
        }

        buildCode(st, rot.left, s + '0');
        buildCode(st, rot.right, s + '1');
    }

    private static class Node implements Comparable<Node>
    {
        private char ch;
        private int frequency;
        private final Node left, right;

        public Node(char ch, int frequency, Node left, Node right)
        {
            this.ch = ch;
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf()
        {
            return left == null && right == null;
        }

        @Override
        public int compareTo(Node that)
        {
            return this.frequency - that.frequency;
        }
    }


    public static void main(String[] args)
    {
        new Huffman().test(
                "G:\\Code\\Algorithms note\\Code\\src\\Strings\\DateCompression\\img.png", "" +
                        "G:\\Code\\Algorithms note\\Code\\src\\Strings\\DateCompression\\testExpand.png");
    }
}
