public class LinkedListDeque<T> {
    private TNode sentinel;
    private int size;
    public class TNode{
        TNode pre;
        T item;
        TNode next;

        public  TNode(TNode x, T y, TNode z){
            pre = x;
            item = y;
            next = z;
        }
        /**construct for sentinel node*/
        public  TNode(TNode x, TNode y){
            pre = x;
            next = y;
        }
    }

    LinkedListDeque(){
        sentinel = new TNode(null, null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    LinkedListDeque(T a){
        sentinel = new TNode(null, null);
        TNode first = new TNode(sentinel, a, sentinel);
        sentinel.next = first;
        sentinel.pre = first;
        size = 1;
    }

    LinkedListDeque(TNode senti, int s){
        sentinel = senti;
        size = s;
    }
    /**add an item to the front of the deque*/
    public void addFirst(T item){
        TNode first = new TNode(sentinel, item, sentinel.next);
        sentinel.next.pre = first;
        sentinel.next = first;
        size += 1;
    }
    /**add an item to the back of the deque*/
    public void addLast(T item){
        TNode last = new TNode(sentinel.pre, item, sentinel);
        sentinel.next.next = last;
        sentinel.pre = last;
        size += 1;
    }
    /**return true if deque is empty*/
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }else {
            return false;
        }
    }
    /**Returns the number of items in the deque.*/
    public int size(){
        return size;
    }
    /**Prints the items in the deque from first to last, separated by a space.*/
    public void printDeque(){
        TNode p = sentinel;
        while(p.next != sentinel){
            p = p.next;
            System.out.println(p.item + " ");
        }
    }
    /**Removes and returns the item at the front of the deque. If no such item exists, returns null.*/
    public T removeFirst(){
        if(size == 0){
            return null;
        }else{
            T temp = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.pre = sentinel;
            size -= 1;
            return temp;
        }
    }
    /**Removes and returns the item at the back of the deque. If no such item exists, returns null.*/
    public T removeLast(){
        T temp = sentinel.pre.item;
        sentinel.pre = sentinel.pre.pre;
        sentinel.pre.next = sentinel;
        size -= 1;
        return temp;
    }
    /**Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.*/
    public T get(int index){
        if(size - 1 < index){
            return null;
        }else{
            TNode temp = sentinel;
            while (index > 0){
                temp = temp.next;
                index -= 1;
            }
            return temp.next.item;
        }
    }
    /**Same as get, but uses recursion.*/
    public T getRecursive(int index){
        if(size - 1 < index){
            return null;
        }else{
            if(index == 0){
                return sentinel.next.item;
            }else{
                LinkedListDeque<T> temp = new LinkedListDeque<>(sentinel,size);
                temp.sentinel = sentinel.next;
                return temp.getRecursive(index - 1);
            }
        }
    }
}