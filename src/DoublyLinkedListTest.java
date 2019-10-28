/*
 * NAME: Jason Chau
 * PID: A14388619
 */

/*
This class is to test my methods for DoublyLinkedList
 */
import org.junit.Test;
import java.util.AbstractList;
import static org.junit.Assert.*;

public class DoublyLinkedListTest {
    AbstractList<Integer> normal = new DoublyLinkedList<>();

    @Test
    public void Testadd() {
        Integer x = 4;
        normal.add(x);
        assertEquals(x, normal.get(0));

        x++;
        normal.add(x);
        assertEquals(x, normal.get(1));

        x++;
        normal.add(x);
        assertEquals(x, normal.get(2));

        x++;
        normal.add(x);
        assertEquals(x, normal.get(3));
    }

    @Test(expected = NullPointerException.class)
    public void TestNullPointerExceptionInAdd() {
        normal.add(null);
    }


    @Test
    public void testAddindex() {
        Integer x = 4;
        normal.add(0, x);
        assertEquals(x, normal.get(0));
        normal.clear();

        normal.add(x);
        normal.add(0, x);
        assertEquals(x, normal.get(0));

        x++;
        normal.add(0, x);
        assertEquals(x, normal.get(0));

        normal.clear();
        normal.add(x);
        x++;
        normal.add(1, x);
        x--;
        assertEquals(x, normal.get(0));
        x++;
        assertEquals(x, normal.get(1));

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void IndexOutOfBoundsExceptionInAdd() {
        Integer x = 5;

        normal.add(x);
        normal.add(x);
        normal.add(4, x);


    }

    @Test
    public void testclear() {
        Integer x = 4;
        normal.add(x);
        normal.clear();
        assertEquals(0, normal.size());

        normal.add(x);
        normal.add(x);
        normal.add(x);
        normal.add(x);
        normal.clear();
        assertEquals(0, normal.size());

        x++;

        normal.add(x);
        normal.add(x);
        normal.add(x);
        normal.add(x);
        normal.add(x);
        normal.add(x);
        normal.add(x);
        normal.clear();
        assertEquals(0, normal.size());
    }

    @Test
    public void testcontains() {
        normal.clear();
        Integer x = 5;
        normal.add(x);
        x++;
        normal.add(x);
        x++;
        normal.add(x);
        x++;
        normal.add(x);
        x++;
        normal.add(x);
        x--;
        assertTrue(normal.contains(x));

        x--;
        assertTrue(normal.contains(x));
        x--;
        assertTrue(normal.contains(x));
        x--;
        assertTrue(normal.contains(x));
        x--;
        assertFalse(normal.contains(x));
    }

    @Test
    public void testget() {

        Integer x = 4;
        normal.add(x);
        normal.add(0, x);
        assertEquals(x, normal.get(0));

        x++;
        normal.add(0, x);
        assertEquals(x, normal.get(0));

        normal.clear();
        normal.add(x);
        x++;
        normal.add(1, x);
        x--;
        assertEquals(x, normal.get(0));
        x++;
        assertEquals(x, normal.get(1));

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void IndexOutOfBoundsExceptionInGet() {
        Integer x = 5;

        normal.add(x);
        normal.add(x);
        normal.get(4);

    }

    @Test
    public void testisEmpty() {
        normal.clear();
        assertTrue(normal.isEmpty());

        normal.add(new Integer(4));
        assertFalse(normal.isEmpty());

        normal.add(new Integer(4));
        assertFalse(normal.isEmpty());
    }

    @Test
    public void testRemove() {
        normal.clear();
        Integer x = 3;
        normal.add(x);
        x++;

        normal.add(x);
        x++;

        normal.add(x);
        x++;

        x = 4;

        assertEquals(x, normal.remove(1));
        assertEquals(2, normal.size());
        x = 3;

        assertEquals(x, normal.remove(0));
        assertEquals(1, normal.size());

        x = 5;
        assertEquals(x, normal.remove(0));
        assertEquals(0, normal.size());

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void IndexOutOfBoundsExceptionInRemove() {
        Integer x = 5;

        normal.add(x);
        normal.add(x);
        normal.remove(4);

    }

    @Test
    public void testset() {
        normal.clear();
        Integer x = 3;
        normal.add(x);
        x++;

        normal.add(x);
        x++;

        normal.add(x);
        x++;

        Integer y = 10;
        x = 3;
        assertEquals(x, normal.set(0, y));
        assertEquals(y, normal.remove(0));
        assertEquals(2, normal.size());

        x = 5;
        assertEquals(x, normal.set(1, y));
        assertEquals(y, normal.remove(1));
        assertEquals(1, normal.size());

        x = 4;
        assertEquals(x, normal.set(0, y));
        assertEquals(y, normal.remove(0));
        assertEquals(0, normal.size());

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void IndexOutOfBoundsExceptionInSet() {
        Integer x = 5;

        normal.add(x);
        normal.add(x);
        normal.set(4, x);

    }

    @Test
    public void testsize() {
        normal.clear();
        Integer x = 3;
        normal.add(x);
        x++;

        normal.add(x);
        x++;

        normal.add(x);
        x++;

        x = 4;

        assertEquals(x, normal.remove(1));
        assertEquals(2, normal.size());
        x = 3;

        assertEquals(x, normal.remove(0));
        assertEquals(1, normal.size());

        x = 5;
        assertEquals(x, normal.remove(0));
        assertEquals(0, normal.size());

    }

    @Test
    public void testsplice() {
        DoublyLinkedList<Integer> testsplice = new DoublyLinkedList<>();
        DoublyLinkedList<Integer> normal = new DoublyLinkedList<>();
        Integer x = 4;
        //[4,5,6]
        testsplice.add(x);
        x++;
        testsplice.add(x);
        x++;
        testsplice.add(x);
        x++;

        normal.clear();
        //[7,8,9]
        normal.add(x);
        x++;
        normal.add(x);
        x++;
        normal.add(x);
        x++;

        //[4,7,8,9,5,6]
        testsplice.splice(1,normal);
        x = 4;
        assertEquals(x,testsplice.get(0));
        x = 7;
        assertEquals(x,testsplice.get(1));
        x++;
        assertEquals(x,testsplice.get(2));
        x++;
        assertEquals(x,testsplice.get(3));
        x = 5;
        assertEquals(x,testsplice.get(4));
        x++;
        assertEquals(x,testsplice.get(5));

    }
    @Test
    public void testmatch() {
        DoublyLinkedList<String> testsplice = new DoublyLinkedList<>();
        DoublyLinkedList<String> normal = new DoublyLinkedList<>();
        normal.add("A");
        normal.add("C");
        normal.add("A");
        normal.add("B");
        normal.add("A");
        normal.add("C");
        normal.add("A");
        normal.add("B");
        normal.add("A");

        testsplice.add("A");
        testsplice.add("B");
        testsplice.add("A");


        int[] test = normal.match(testsplice);
        int[] output = {2,6};
        assertArrayEquals(output,test);
        testsplice.clear();

        testsplice.add("A");
        testsplice.add("C");
        testsplice.add("A");

        test = normal.match(testsplice);
        int[] output1 = {0,4};
        assertArrayEquals(output1,test);

        testsplice.clear();
        test = normal.match(testsplice);
        int[] output2 = {};
        assertArrayEquals(output2,test);


    }
}