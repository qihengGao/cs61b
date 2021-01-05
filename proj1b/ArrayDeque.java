public class ArrayDeque<T> implements Deque<T> {

    private static final int MIN_ARRAY_SIZE = 8;
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = (items.length - 1) / 2;
        nextLast = (nextFirst + 1 + size) % items.length;
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
        for (int i = 0; i < size - 1; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println(get(size() - 1));
    }

    private void resize(int capacity) {
        capacity = capacity < MIN_ARRAY_SIZE ? MIN_ARRAY_SIZE : capacity;
        T[] newItems = (T[]) new Object[capacity];
        int start = (newItems.length - 1) / 2 + 1;
        for (int i = 0; i < size; i++) {
            newItems[start++] = get(i);
        }
        items = newItems;
        nextFirst = (items.length - 1) / 2;
        nextLast = (nextFirst + 1 + size) % items.length;
    }

    @Override
    public void addFirst(T item) {
        size++;
        nextFirst = nextFirst < 0 ? items.length - 1 : nextFirst;
        items[nextFirst--] = item;
        if (size == items.length) {
            resize(items.length << 1);
        }
    }

    @Override
    public void addLast(T item) {
        size++;
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        if (size == items.length) {
            resize(items.length << 1);
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = get(0);
        size--;
        nextFirst = (nextFirst + 1) > (items.length - 1) ? 0 : nextFirst + 1;
        items[nextFirst] = null;
        if (size < items.length / 4) {
            resize(items.length >> 1);
        }
        return item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = get(size() - 1);
        size--;
        nextLast = (nextLast - 1) < 0 ? items.length - 1 : nextLast - 1;
        items[nextLast] = null;
        if (size < items.length / 4) {
            resize(items.length >> 1);
        }
        return item;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        return items[(nextFirst + 1 + index) % items.length];
    }
}
