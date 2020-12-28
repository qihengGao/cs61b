import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void testNullCase() {
        ArrayDeque<Integer> arr = new ArrayDeque<>();
        assertEquals(true, arr.isEmpty());
        arr.addLast(0);
        assertEquals(false, arr.isEmpty());
    }
    @Test
    public void testArray() {
        ArrayDeque<String> arrOne = new ArrayDeque<>();
        ArrayDeque<String> arrTwo = new ArrayDeque<>();
        arrOne.addFirst("last");
        arrOne.addFirst("middle");
        arrOne.addFirst("first");
        arrTwo.addLast("first");
        arrOne.printDeque();
        arrTwo.printDeque();
        assertEquals(arrOne.get(0), arrTwo.get(0));
    }

    @Test
    public void testAdds() {
        ArrayDeque<Integer> arrOne = new ArrayDeque<>(0);
        for (int i = 1; i <= 3; i++) {
            arrOne.addFirst(i);
        }
        for (int i = 4; i < 8; i++) {
            arrOne.addLast(i);
        }
        int last = arrOne.get(arrOne.getSize() - 1);
        assertEquals(7, last);
    }

    @Test
    public void testAddLastAndResize() {
        ArrayDeque<Integer> arrOne = new ArrayDeque<>(0);
        for (int i = 1; i < 8; i++) {
            arrOne.addLast(i);
        }
        arrOne.addFirst(-1);
        arrOne.printDeque();
        assertEquals(9, arrOne.getSize());
        int last = arrOne.get(arrOne.getSize() - 1);
        assertEquals(7, last);
    }

    @Test
    public void testAddFirstAndResize() {
        ArrayDeque<Integer> arrTwo = new ArrayDeque<>(0);
        for (int i = 1; i < 8; i++) {
            arrTwo.addFirst(i);
        }
        arrTwo.addFirst(-1);
        int lastTwo = arrTwo.get(arrTwo.getSize() - 1);
        arrTwo.printDeque();
        assertEquals(0, lastTwo);
    }

    @Test
    public void testRemoves() {
        ArrayDeque<Integer> arrTwo = new ArrayDeque<>(0);
        for (int i = 1; i < 8; i++) {
            arrTwo.addFirst(i);
        }
        arrTwo.addFirst(-1);
        int size = arrTwo.getSize();
        for (int i = 0; i < size; i++) {
            arrTwo.removeLast();
        }
        assertEquals(true, arrTwo.isEmpty());
        assertEquals(null, arrTwo.removeFirst());
    }
}
