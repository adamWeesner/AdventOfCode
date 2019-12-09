package test

import intCodeInstruction
import intCodeProgram
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

internal class Day2Test {
    @TestFactory
    fun testIntCodeInstruction() = listOf(
        mutableListOf(1, 0, 0, 0, 99) to 2,
        mutableListOf(2, 3, 0, 3, 99) to 6,
        mutableListOf(2, 4, 4, 5, 99, 0) to 9801
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("$expected at index 0 for int code instruction with $input") {
            intCodeInstruction(0, input) shouldBe expected
        }
    }

    @TestFactory
    fun testIntCodeProgram() = listOf(
        mutableListOf(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50) to 3500,
        mutableListOf(1, 0, 0, 0, 99) to 2,
        mutableListOf(1, 1, 1, 4, 99, 5, 6, 0, 99) to 30
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("$expected at index 0 for int code program with $input") {
            intCodeProgram(input) shouldBe expected
        }
    }

    @Test
    fun `int code program for AoC`() {
        val value = intCodeProgram(
            mutableListOf(
                1,
                12,
                2,
                3,
                1,
                1,
                2,
                3,
                1,
                3,
                4,
                3,
                1,
                5,
                0,
                3,
                2,
                1,
                10,
                19,
                1,
                19,
                5,
                23,
                2,
                23,
                6,
                27,
                1,
                27,
                5,
                31,
                2,
                6,
                31,
                35,
                1,
                5,
                35,
                39,
                2,
                39,
                9,
                43,
                1,
                43,
                5,
                47,
                1,
                10,
                47,
                51,
                1,
                51,
                6,
                55,
                1,
                55,
                10,
                59,
                1,
                59,
                6,
                63,
                2,
                13,
                63,
                67,
                1,
                9,
                67,
                71,
                2,
                6,
                71,
                75,
                1,
                5,
                75,
                79,
                1,
                9,
                79,
                83,
                2,
                6,
                83,
                87,
                1,
                5,
                87,
                91,
                2,
                6,
                91,
                95,
                2,
                95,
                9,
                99,
                1,
                99,
                6,
                103,
                1,
                103,
                13,
                107,
                2,
                13,
                107,
                111,
                2,
                111,
                10,
                115,
                1,
                115,
                6,
                119,
                1,
                6,
                119,
                123,
                2,
                6,
                123,
                127,
                1,
                127,
                5,
                131,
                2,
                131,
                6,
                135,
                1,
                135,
                2,
                139,
                1,
                139,
                9,
                0,
                99,
                2,
                14,
                0,
                0
            )
        )
        println(value)
        value shouldNotBe -1
    }
}