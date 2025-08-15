import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import static org.junit.Assert.*;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }

    @Test
    /** This tests the addFirst and addLast and toList methods */
    public void testAddFirstAndLastAndToList() {
        ArrayDeque61B<Integer> testArrayDeque0 = new ArrayDeque61B<>();
        testArrayDeque0.addFirst(0);
        testArrayDeque0.addFirst(1);
        testArrayDeque0.addFirst(2);
        testArrayDeque0.addFirst(3);
        testArrayDeque0.addLast(4);
        testArrayDeque0.addLast(5);
        testArrayDeque0.addLast(6);
        testArrayDeque0.addLast(7);
        assertThat((testArrayDeque0.toList())).containsExactly(3, 2, 1, 0, 4, 5, 6, 7);
    }

    @Test
    public void testAddFirstWrapAround() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        // Fill deque partially at the back
        deque.addLast(0);
        deque.addLast(1);
        deque.addLast(2);

        // Pop front to move front pointer
        deque.removeFirst(); // front moves right

        // Now addFirst should wrap around correctly
        deque.addFirst(10);
        deque.addFirst(11);

        // Logical order should match expected
        assertThat(deque.toList()).containsExactly(11, 10, 1, 2);
    }

    @Test
    public void testAddLastWrapAround() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        // Fill deque partially at the front
        deque.addFirst(0);
        deque.addFirst(1);
        deque.addFirst(2);

        // Pop last to move "back" pointer
        deque.removeLast(); // back moves left

        // Now addLast should wrap around correctly
        deque.addLast(10);
        deque.addLast(11);

        assertThat(deque.toList()).containsExactly(2, 1, 10, 11);
    }

    @Test
    public void testResizeWhileAddingFrontAndBack() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        // Fill deque to trigger resize
        for (int i = 0; i < 16; i++) {
            deque.addLast(i);
        }

        // Add more elements at both ends
        deque.addFirst(100);
        deque.addLast(200);

        List<Integer> expected = new ArrayList<>();
        expected.add(100);
        for (int i = 0; i < 16; i++) expected.add(i);
        expected.add(200);

        assertThat(deque.toList()).containsExactlyElementsIn(expected);
    }

    @Test
    public void testAddAfterMultipleWraps() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        // Add and remove repeatedly to wrap front/back multiple times
        for (int i = 0; i < 8; i++) deque.addLast(i);
        for (int i = 0; i < 4; i++) deque.removeFirst();
        for (int i = 0; i < 4; i++) deque.addFirst(100 + i);

        // Expected logical order after wrapping
        List<Integer> expected = Arrays.asList(103, 102, 101, 100, 4, 5, 6, 7);
        assertThat(deque.toList()).containsExactlyElementsIn(expected);
    }

    @Test
    public void testRemoveFrontSimple() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        assertEquals((Integer)1, deque.removeFirst());
        assertThat(deque.toList()).containsExactly(2, 3);

        assertEquals((Integer)2, deque.removeFirst());
        assertThat(deque.toList()).containsExactly(3);

        assertEquals((Integer)3, deque.removeFirst());
        assertThat(deque.toList()).isEmpty();
    }

    @Test
    public void testRemoveBackSimple() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3); // deque = [3,2,1]

        assertEquals((Integer)1, deque.removeLast());
        assertThat(deque.toList()).containsExactly(3, 2);

        assertEquals((Integer)2, deque.removeLast());
        assertThat(deque.toList()).containsExactly(3);

        assertEquals((Integer)3, deque.removeLast());
        assertThat(deque.toList()).isEmpty();
    }

    @Test
    public void testRemoveFrontWrapAround() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        for (int i = 0; i < 8; i++) deque.addLast(i);
        for (int i = 0; i < 5; i++) deque.removeFirst();
        deque.addLast(100);
        deque.addLast(101);

        assertEquals((Integer)5, deque.removeFirst());
        assertThat(deque.toList()).containsExactly(6, 7, 100, 101);
    }

    @Test
    public void testRemoveBackWrapAround() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        for (int i = 0; i < 8; i++) deque.addFirst(i);
        for (int i = 0; i < 3; i++) deque.removeLast();
        deque.addFirst(100);
        deque.addFirst(101);

        assertEquals((Integer)3, deque.removeLast());
        List<Integer> expected = Arrays.asList(101, 100, 7, 6, 5, 4);
        assertThat(deque.toList()).containsExactlyElementsIn(expected);
    }

    /*@Test
    public void testRemoveFirstFromEmptyDeque() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        try {
            deque.removeFirst();
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Test passes
        }
    }

    @Test
    public void testRemoveLastFromEmptyDeque() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        try {
            deque.removeLast();
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Test passes
        }
    }*/
}
