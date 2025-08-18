package deque;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Comparator;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import java.util.List;

public class TestDeque {
    @Test
    public void testIteratorToStringEquals() {
        Deque61B<Integer> testArrayDeque1 = new ArrayDeque61B<>();
        Deque61B<Integer> testArrayDeque2 = new ArrayDeque61B<>();
        Deque61B<Integer> testLLDeque1 = new LinkedListDeque61B<>();
        Deque61B<Integer> testLLDeque2 = new LinkedListDeque61B<>();
        for(int i = 5; i >= 0; i--) {
            testArrayDeque1.addFirst(i);
            testArrayDeque2.addFirst(i);
            testLLDeque1.addFirst(i);
            testLLDeque2.addFirst(i);
        }

        assertThat(testArrayDeque1.equals(testArrayDeque2)).isTrue();
        assertThat(testLLDeque1.equals(testLLDeque2)).isTrue();

        List<Integer> helpList1 = new ArrayList<>();
        List<Integer> helpList2 = new ArrayList<>();

        for(int el : testArrayDeque1) {
            helpList1.add(el);
        }
        for(int el: testLLDeque1) {
            helpList2.add(el);
        }

        assertThat(helpList1).containsExactly(0, 1, 2, 3, 4, 5);
        assertThat(helpList2).containsExactly(0, 1, 2, 3, 4, 5);
        System.out.println(helpList1.toString());
    }
}
