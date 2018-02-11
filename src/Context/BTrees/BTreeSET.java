package Context.BTrees;
// Created by yirany on 2018/2/6

import Fundamentals.Bags_Queues_Stacks.List;
import Searching.BalancedSearchTrees.RedBlackBST;


public class BTreeSET<Key extends Comparable<Key>>
{
    private static final int pagesPerNode = 2 * 500;

    private Page root = new Page(true);

    // 通过添加一个哨兵初始化，哨兵必须小于任何可能插入的键值
    public BTreeSET(Key sentinel)
    {
        add(sentinel);
    }

    public boolean contains(Key key)
    {
        return contains(root, key);
    }

    private boolean contains(Page rot, Key key)
    {
        if (rot.isExternal())
            return rot.contains(key);
        return contains(rot.next(key), key);
    }

    public void add(Key key)
    {
        add(root, key);
        if (root.isFull())
        {
            Page leftHalf = root;
            Page rightHalf = root.splitAndReturnRightHalf();
            root = new Page(false);
            root.add(leftHalf);
            root.add(rightHalf);
        }
    }

    public void add(Page rot, Key key)
    {
        if (rot.isExternal())
        {
            rot.add(key);
            return;
        }

        Page next = rot.next(key);
        add(next, key); // recursive until found the external page to put in

        if (next.isFull())
            rot.add(next.splitAndReturnRightHalf());
        next.close();

    }

    public class Page
    {
        private RedBlackBST<Key, Page> content;
        private boolean isExternal;

        public Page(boolean isExternal)
        {
            this.isExternal = isExternal;
            content = new RedBlackBST<>();
        }

        public void close()
        {
            // do nothing
        }

        public void add(Key key)
        {
            checkIsExternal();
            content.put(key, null);
        }

        // open p, insert one entry to it and connect the least key with this page
        public void add(Page p)
        {
            checkNotExternal();
            Key leastKey = p.content.min();
            content.put(leastKey, p);
        }

        public boolean isExternal()
        {
            return isExternal;
        }

        public boolean contains(Key key)
        {
            return content.contains(key);
        }

        public Page next(Key key)
        {
            int rank = content.rank(key);
            Key target = content.select(rank - 1);
            return content.get(target);
        }

        public boolean isFull()
        {
            return content.size() == pagesPerNode;
        }

        // 将较大的中间键移动到一个新页中
        // and return right half
        public Page splitAndReturnRightHalf()
        {
            if (!isFull()) throw new RuntimeException();

            Page rightHalf = new Page(isExternal);
            for (int i = 0; i < pagesPerNode/2; ++i)
            {
                Key key = content.max();
                rightHalf.add(key);
                content.deleteMax();
            }
            return rightHalf;
        }

        public Iterable<Key> keys()
        {
            List<Key> list = new List<>();
            collect(this, list);
            return list;
        }

        private void collect(Page page, List<Key> list)
        {
            if (isExternal)
            {
                for (Key key : content.keys())
                    list.push(key);
                return;
            }
            for (Key key : content.keys())
                collect(content.get(key), list);
        }

        private void checkIsExternal()
        {
            if (!isExternal)
                throw new RuntimeException("add key directly to an not external page");
        }

        private void checkNotExternal()
        {
            if (isExternal)
                throw new RuntimeException("add page to a external page");
        }
    }
}
