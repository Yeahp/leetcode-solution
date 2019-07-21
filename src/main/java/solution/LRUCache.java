package solution;

import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    // 运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制
    // 它应该支持以下操作： 获取数据 get 和 写入数据 put
    // 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
    // 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间
    //
    // 示例:
    // LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
    // cache.put(1, 1);
    // cache.put(2, 2);
    // cache.get(1);       // 返回  1
    // cache.put(3, 3);    // 该操作会使得密钥 2 作废
    // cache.get(2);       // 返回 -1 (未找到)
    // cache.put(4, 4);    // 该操作会使得密钥 1 作废
    // cache.get(1);       // 返回 -1 (未找到)
    // cache.get(3);       // 返回  3
    // cache.get(4);       // 返回  4
    //
    //进阶:
    //你是否可以在 O(1) 时间复杂度内完成这两种操作？
    private int capcity;
    private LinkedHashMap<Integer, Integer> keyValuePair = new LinkedHashMap<Integer, Integer>();

    public LRUCache(int capcity) {
        this.capcity = capcity;
    }

    public int get(int key) {
        if (keyValuePair.containsKey(key)) {
            int value = keyValuePair.get(key);
            keyValuePair.remove(key);
            keyValuePair.put(key, value);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        int cap = keyValuePair.size();
        if (cap == 0) {
            keyValuePair.put(key, value);
        } else if (cap < capcity) {
            if (keyValuePair.containsKey(key)) {
                keyValuePair.remove(key);
                keyValuePair.put(key, value);
            } else {
                keyValuePair.put(key, value);
            }
        } else if (cap == capcity) {
            if (keyValuePair.containsKey(key)) {
                keyValuePair.remove(key);
                keyValuePair.put(key, value);
            } else {
                int firstKey = keyValuePair.entrySet().iterator().next().getKey();
                keyValuePair.remove(firstKey);
                keyValuePair.put(key, value);
            }
        }
    }



    /**
     * Why Did you create this class? what does it do?
     */
    public class LRU {

        //public static void main(String[] args) {
        //    LRU lru = new LRU(10);
        //    lru.put(10, 13);
        //}

        int cap = 0;
        Map<Integer, Integer> map;
        Node root, tail;
        Map<Integer, Node> mm = new HashMap<Integer, Node>();

        public LRU(int capacity) {
            cap = capacity;
            map = new HashMap<Integer, Integer>();
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            Node n = mm.get(key);
            if (n.next == null) {
                return map.get(key);
            }
            n.val = n.next.val;
            if (n.next == tail) {
                n.next = null;
                tail = n;
            } else {
                n.next = n.next.next;
            }

            tail.next = new Node(key);
            tail = tail.next;
            mm.put(key, tail);
            return map.get(key);
        }

        public void put(int key, int value) {
            Node n;
            int ex = get(key);

            if (ex == -1) {
                n = new Node(key);
                if (root == null && tail == null)
                    root = tail = n;
                else
                    tail.next = n;
                tail = n;
            } else {
                n = tail;
            }
            map.put(key, value);
            mm.put(key, n);
            if (map.size() > cap) {
                map.remove(root.val);
                mm.remove(root.val);
                root = root.next;
            }

        }

        class Node {
            int val;
            Node next;

            Node(int val) {
                this.val = val;
            }
        }

    }


    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);                        // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);                        // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4
    }
}
