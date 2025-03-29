import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    // Below, you'll write your own tests for LinkedListDeque61B.
    @Test
    /** This tests the method isEmpty() */
    public void isEmptyTest() {
        Deque61B<Integer> testList = new LinkedListDeque61B<>();
        assertThat(testList.isEmpty()).isEqualTo(true);
        testList.addFirst(1);
        assertThat(testList.isEmpty()).isEqualTo(false);
    }

    @Test
    /** This tests the method size() */
    public void sizeTest() {
        Deque61B<Integer> testList = new LinkedListDeque61B<>();
        int actual = testList.size();
        int expected = 0;
        assertThat(actual).isEqualTo(expected);
        testList.addFirst(99);
        testList.addLast(-90);
        testList.addFirst(0);
        actual = testList.size();
        expected = 3;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    /** This tests the method get() */
    public void getTest() {
        Deque61B<Integer> testDeque = new LinkedListDeque61B<>();
        assertThat(testDeque.get(0)).isNull();
        assertThat(testDeque.get(1)).isNull();
        assertThat(testDeque.get(-1)).isNull();
        testDeque.addLast(5);
        assertThat(testDeque.get(0)).isEqualTo(5);
        assertThat(testDeque.get(1)).isNull();
        assertThat(testDeque.get(-2)).isNull();
        testDeque.addLast(5);
        assertThat(testDeque.get(1)).isEqualTo(5);
        testDeque.addLast(10);
        assertThat(testDeque.get(2)).isEqualTo(10);
        assertThat(testDeque.get(1)).isEqualTo(5);
    }

    @Test
    /** This test the method getRecursive() */
    public void getRecursiveTest() {
        Deque61B<Integer> testDeque = new LinkedListDeque61B<>();
        assertThat(testDeque.getRecursive(0)).isNull();
        assertThat(testDeque.getRecursive(1)).isNull();
        assertThat(testDeque.getRecursive(-1)).isNull();
        testDeque.addLast(5);
        assertThat(testDeque.getRecursive(0)).isEqualTo(5);
        assertThat(testDeque.getRecursive(1)).isNull();
        assertThat(testDeque.getRecursive(-2)).isNull();
        testDeque.addLast(5);
        assertThat(testDeque.getRecursive(1)).isEqualTo(5);
        testDeque.addLast(10);
        assertThat(testDeque.getRecursive(2)).isEqualTo(10);
        assertThat(testDeque.getRecursive(1)).isEqualTo(5);
    }

    @Test
    /** This tests the methods removeFirst() && removeLast() */
    public void removeFirstAndLastTest() {
        Deque61B<Integer> testDeque = new LinkedListDeque61B<>();
        assertThat(testDeque.removeFirst()).isNull();
        testDeque.addLast(5);
        testDeque.addLast(7);
        testDeque.addLast(8);
        testDeque.addLast(9);
        testDeque.addFirst(0);
        testDeque.addFirst(3);
        testDeque.addFirst(2);
        testDeque.removeFirst();
        assertThat(testDeque.toList()).containsExactly(3, 0, 5, 7, 8, 9);
        testDeque.removeLast();
        assertThat(testDeque.toList()).containsExactly(3, 0, 5, 7, 8);
        assertThat(testDeque.removeFirst()).isEqualTo(3);
        testDeque.removeFirst();
        testDeque.removeLast();
        assertThat(testDeque.toList()).containsExactly(5, 7);
        assertThat(testDeque.removeLast()).isEqualTo(7);
        testDeque.removeLast();
        assertThat(testDeque.removeLast()).isNull();
        testDeque.addLast(5);
        testDeque.removeLast();
        assertThat(testDeque.toList()).containsExactly();
        testDeque.addLast(5);
        testDeque.removeFirst();
        assertThat(testDeque.toList()).containsExactly();
    }
}