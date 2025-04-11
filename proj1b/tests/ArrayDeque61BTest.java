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
        ArrayDeque61B<Integer> testArrayDeque = new ArrayDeque61B<>();
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
        assertThat(testArrayDeque.toList()).containsExactly(13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 , 1, 5, 5, 7, 5, 5, 10, 12);
    }

}
