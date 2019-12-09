package test

import calculateFuelCost
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory

internal class Day1Test {
    @TestFactory
    fun testFuelCost() = listOf(
        12 to 2,
        14 to 2,
        1969 to 654,
        100756 to 33583
    ).map { (input, expected) ->
        dynamicTest("fuel for mass $input is $expected") {
            calculateFuelCost(input) shouldBe expected
        }
    }
}