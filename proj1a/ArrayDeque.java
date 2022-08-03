public class ArrayDeque<T> {
    T[] items;
    int first;
    int size;
    private int length;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        first = 0;
        size = 0;
        length = items.length;
    }

    public ArrayDeque(T a){
        items = (T[]) new Object[8];
        items[0] = a;
        size = 1;
        first = 0;
        length = items.length;
    }

    private void resize(int capacity){
        T[] a = (T[]) new Object[capacity];
        if(first == 0) {
            System.arraycopy(items, 0, a, 0, size);
        }else{
            System.arraycopy(items,first,a,0,length - first);
            System.arraycopy(items,0,a,length-first,size+first-length);
            first = 0;
            items = a;
            length = capacity;
        }
    }

    private void check(){
        if(length >= 16 && length > 4*size){
            resize(size);
        }
    }

    public void addFirst(T x){
        if(size == length){
            resize(2*size);
        }
        first = (first-1)%length;
        items[first] = x;
        size += 1;
    }

    public void addLast(T x){
        if(size == length){
            resize(2*size);
        }
        int last = (first + size)%length;
        items[last] = x;
        size += 1;
    }

    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for(int i = first;i < first + size;i++){
            System.out.print(items[i%length] + " ");
        }
    }

    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        T temp = items[first];
        first = (first + 1)%length;
        check();
        return temp;
    }

    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        T temp = items[(first+size-1)%length];
        size -= 1;
        check();
        return temp;
    }

    public T get(int index){
        if(index >= size){
            return null;
        }else{
            return items[(first + index)%length];
        }
    }
}