class LRUCache {
    // use a doublelinkedlist to record the most frequerent visted nodes
    
    LinkedNodeList linkedNode;
    Map<Integer, Node> pair;
    int cap;
    public LRUCache(int capacity) {
        linkedNode = new LinkedNodeList();
        pair = new HashMap(capacity);
        cap = capacity;
    }
    
    public int get(int key) {
        Node node = pair.get(key);
        if(node == null){
            return -1;
        }
        else{
            linkedNode.moveToHead(node);
            return node.value;
        }
    }
    
    public void put(int key, int value) {
        Node node = pair.get(key);
        if(node != null){
            linkedNode.moveToHead(node);
            node.value = value;
        }
        else{
            Node newNode = new Node(key,value);
            if(pair.size() == cap){
                Node tail = linkedNode.getTail();
                pair.remove(tail.key);
                linkedNode.removeTail();
            }
            pair.put(key, newNode);
            linkedNode.addToHead(newNode);
        }
    }
}

class LinkedNodeList {
    Node head ;
    Node tail ;
    
    LinkedNodeList(){
        head = new Node(0,0);
        tail = new Node(0,0);
        head.post = tail;
        tail.prev = head;
    }
    
    
    void addToHead(Node node){
        Node temp = head.post;
        head.post = node;
        node.prev = head;
        node.post = temp;
        temp.prev = node;
    }
    
    public void moveToHead(Node node){
        node.prev.post = node.post;
        node.post.prev = node.prev;
        addToHead(node);
    }
    
    
    public void removeTail(){
        Node temp = tail.prev.prev;
        temp.post = tail;
        tail.prev = temp;
        
    }
    
    public Node getTail(){
        return tail.prev;
        
    }
}

class Node {
    int key; 
    int value;
    Node prev;
    Node post;
    
    Node(int key, int value){
        this.key = key;
        this.value = value;
    }
    
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */