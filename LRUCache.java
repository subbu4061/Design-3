class LRUCache {
    class Node{
        int key;
        int val;
        Node next;
        Node prev;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    private HashMap<Integer, Node> map;
    private int cap;
    private Node head;
    private Node tail;
    public LRUCache(int capacity) {
        this.map = new HashMap<>();  
        this.cap = capacity;
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.head.next = tail;
        this.tail.prev = head;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev =node.prev;
        node.next = null;
        node.prev =null;
    }
    private void addToHead(Node node) { 
        node.prev = head;
        node.next = head.next;
        head.next.prev = node; 
        head.next = node;
    }
    
    // TimeComplexity: O(1)
    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
    }
    
    // TimeComplexity: O(1)
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
            
        } else {
            if(map.size() == cap) {
                Node remNode = tail.prev;
                removeNode(remNode);
                map.remove(remNode.key);
            }
            Node newEle = new Node(key,value);
            addToHead(newEle);
            map.put(key, newEle);
            
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */