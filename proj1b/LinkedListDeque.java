public class LinkedListDeque<T> implements Deque<T> {

    private static class Node<T> {
        T item;
        Node pre;
        Node next;

        Node(T item, Node pre, Node next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }

    }

    private Node sentinel;
    private  int size;

    public LinkedListDeque() {
        this.sentinel = new Node(0, null, null);
        this.sentinel.pre = this.sentinel;
        this.sentinel.next = this.sentinel;
        this.size = 0;
    }

    @Override
    public void addFirst(T item) {
        size++;
        Node next = sentinel.next;
        sentinel.next = new Node(item, sentinel, next);
        next.pre = sentinel.next;
    }

    @Override
    public void addLast(T item) {
        size++;
        Node pre = sentinel.pre;
        sentinel.pre = new Node(item, pre, sentinel);
        pre.next = sentinel.pre;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node ptr = sentinel;
        while (ptr.next != sentinel.pre) {
            ptr = ptr.next;
            System.out.print(ptr.item + " ");
        }
        System.out.println(sentinel.pre);
    }

    @Override
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        size--;
        T item = (T) sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.pre = sentinel;
        return item;
    }

    @Override
    public T removeLast() {
        if (sentinel.pre == sentinel) {
            return null;
        }
        size--;
        T item = (T) sentinel.pre.item;
        sentinel.pre = sentinel.pre.pre;
        sentinel.pre.next = sentinel;
        return item;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        Node ptr = sentinel;
        while (index-- >= 0) {
            ptr = ptr.next;
        }
        return (T) ptr.item;
    }

    public T getRecursive(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        return getRecursive(index, sentinel.next);
    }

    private T getRecursive(int index, Node curr) {
        if (index == 0) {
            return (T) curr.item;
        }
        index--;
        return getRecursive(index, curr.next);
    }
}
