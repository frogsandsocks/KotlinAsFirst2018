package exam

import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test


class Test {

    @Test
    fun testSets() {
        assertEquals(setOf(3), setsIntersect("input/exam_in1.txt", "A & C & !X"))
        assertEquals(setOf(2, 4, 0), setsIntersect("input/exam_in1.txt", "A & C & X"))
        assertEquals(setOf(2, 4, 0), setsIntersect("input/exam_in1.txt", "A & X"))
        assertEquals(setOf(5), setsIntersect("input/exam_in1.txt", "X & !A"))
        assertEquals(setOf(-1, 7), setsIntersect("input/exam_in1.txt", "A & !C & !X"))
        assertEquals(setOf(-1, 7), setsIntersect("input/exam_in1.txt", "A & !C"))
    }
}