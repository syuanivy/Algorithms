import java.util.HashMap;

public class LRUCache {
    int capacity;
    int size;

    //self-defined doublylinked list, the builtin linkedlist do not support O(1)deletion of remove(entry)
    private class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }


    Node head; // LRU cache
    Node tail; // MRU cache
    HashMap<Integer, Node> map; // key and the Node object

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        head = null;
        tail = null;
        this.map = new HashMap<Integer, Node>();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node target = map.get(key);
            remove(target);
            add(target);
            return target.value;
        } else
            return -1;
    }

    public void set(int key, int value) {
        if (!map.containsKey(key)) {
            Node n = new Node(key, value);
            map.put(key, n);
            if (size < capacity) {
                add(n);
                size++;
            } else {
                remove(head);
                add(n);
            }
        } else {
            Node old = map.get(key);
            remove(old);
            old.value = value;
            add(old);
        }
    }

    public void remove(Node n) {
        map.remove(n.key);
        if (n.next == null) {
            if (n.prev != null) {
                Node newTail = tail.prev;
                tail.prev = null;
                newTail.next = null;
                tail = newTail;
            } else {
                head = null;
                tail = null;
            }
        } else if (n.prev == null) {
            Node newHead = n.next;
            head.next = null;
            newHead.prev = null;
            head = newHead;
        } else {
            n.prev.next = n.next;
            n.next.prev = n.prev;
            n.prev = null;
            n.next = null;
        }

    }

    public void add(Node n) {
        map.put(n.key, n);
        if (tail == null) {
            head = n;
            tail = n;
            n.prev = null;
            n.next = null;
        } else {
            n.prev = tail;
            tail.next = n;
            n.next = null;
            tail = n;//put target on the tail;
        }
    }
}
    
    
    
    