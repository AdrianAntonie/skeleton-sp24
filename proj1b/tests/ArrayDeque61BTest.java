import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

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
        //Testing just linear front ... back
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

        //Testing when front has to move from right to left
        ArrayDeque61B<Integer> testArrayDeque1 = new ArrayDeque61B<>();
        testArrayDeque1.addFirst(0);
        testArrayDeque1.addFirst(1);
        testArrayDeque1.addLast(2);
        testArrayDeque1.addLast(3);
        testArrayDeque1.addFirst(4);
        testArrayDeque1.addFirst(5);
        testArrayDeque1.addFirst(6);
        testArrayDeque1.addFirst(7);
        assertThat(testArrayDeque1.toList()).containsExactly(7, 6, 5, 4, 1, 0, 2, 3);

        //Testing when back has to move from right to left
        ArrayDeque61B<Integer> testArrayDeque2 = new ArrayDeque61B<>();
        testArrayDeque2.addFirst(0);
        testArrayDeque2.addFirst(1);
        testArrayDeque2.addLast(2);
        testArrayDeque2.addLast(3);
        testArrayDeque2.addLast(4);
        testArrayDeque2.addLast(5);
        testArrayDeque2.addLast(6);
        testArrayDeque2.addLast(7);
        assertThat(testArrayDeque2.toList()).containsExactly(1, 0, 2, 3, 4, 5, 6, 7);
        /*ArrayDeque61B<Integer> testArrayDeque = new ArrayDeque61B<>();
        testArrayDeque.addFirst(1);
        testArrayDeque.addFirst(2);
        testArrayDeque.addLast(5);
        testArrayDeque.addFirst(3);
        testArrayDeque.addFirst(4);
        testArrayDeque.addLast(5);
        testArrayDeque.addFirst(5);
        testArrayDeque.addLast(7);
        testArrayDeque.addFirst(6);
        testArrayDeque.addFirst(7);
        testArrayDeque.addLast(5);
        testArrayDeque.addFirst(8);
        testArrayDeque.addFirst(9);
        testArrayDeque.addFirst(10);
        testArrayDeque.addFirst(11);
        testArrayDeque.addFirst(12);
        testArrayDeque.addLast(5);
        testArrayDeque.addLast(10);
        testArrayDeque.addLast(12);
        testArrayDeque.addFirst(13);
        assertThat(testArrayDeque.toList()).containsExactly(13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 , 1, 5, 5, 7, 5, 5, 10, 12);*/
        /*
        ArrayDeque61B<Integer> testArrayDeque2 = new ArrayDeque61B<>();
        testArrayDeque2.addLast(1);
        testArrayDeque2.addLast(2);
        testArrayDeque2.addLast(3);
        testArrayDeque2.addLast(6);
        testArrayDeque2.addLast(7);
        testArrayDeque2.addLast(9);
        testArrayDeque2.addLast(11);
        testArrayDeque2.addLast(5);
        testArrayDeque2.addLast(3);
        testArrayDeque2.addLast(4);
        assertThat(testArrayDeque2.toList()).containsExactly(1, 2, 3, 6, 7, 9, 11, 5, 3, 4);
        ArrayDeque61B<Integer> testArrayDeque3 = new ArrayDeque61B<>();
        testArrayDeque3.addLast(1);
        testArrayDeque3.addLast(2);
        testArrayDeque3.addLast(3);
        testArrayDeque3.addLast(6);
        testArrayDeque3.addLast(7);
        testArrayDeque3.addLast(9);
        testArrayDeque3.addLast(11);
        testArrayDeque3.addLast(5);
        testArrayDeque3.addFirst(3);
        testArrayDeque3.addFirst(4);
        assertThat(testArrayDeque3.toList()).containsExactly(4, 3, 1, 2, 3, 6, 7, 9, 11, 5);*/
        /*ArrayDeque61B<Integer> testArrayDeque4 = new ArrayDeque61B<>();
        testArrayDeque4.addFirst(1);
        testArrayDeque4.addFirst(2);
        testArrayDeque4.addFirst(3);
        testArrayDeque4.addFirst(6);
        testArrayDeque4.addFirst(7);
        testArrayDeque4.addFirst(9);
        testArrayDeque4.addFirst(11);
        testArrayDeque4.addFirst(5);
        testArrayDeque4.addFirst(3);
        testArrayDeque4.addLast(4);
        assertThat(testArrayDeque4.toList()).containsExactly(3, 5, 11, 9, 7, 6, 3, 2, 1, 4);*/
    }

}
