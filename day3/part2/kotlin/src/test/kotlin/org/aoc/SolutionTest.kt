package org.aoc

import org.junit.Assert.assertEquals
import org.junit.Test

class SolutionTest {

    @Test
    fun `should find first higher than sample`() {
        assertEquals(findFirstHigherThanNumByExtendingField(5), 10)
        assertEquals(findFirstHigherThanNumByExtendingField(304), 330)
        assertEquals(findFirstHigherThanNumByExtendingField(747), 806)
    }
}