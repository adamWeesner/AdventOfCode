package test

import calculateFuelCost
import calculateNavCost
import calculateTotalNavCost
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

internal class Day1Test {
    @TestFactory
    fun testFuelCost() = listOf(
        12 to 2,
        14 to 2,
        1969 to 654,
        100756 to 33583
    ).map { (input, expected) ->
        dynamicTest("fuel for mass $input should $expected") {
            calculateFuelCost(input) shouldBe expected
        }
    }

    @TestFactory
    fun testNavCost() = listOf(
        14 to 2,
        1969 to 966,
        100756 to 50346
    ).map { (input, expected) ->
        dynamicTest("nav cost for $input should be $expected") {
            calculateNavCost(input) shouldBe expected
        }
    }

    @Test
    fun `calculate total nav cost`(){
        val totalCost = calculateTotalNavCost()
        println(totalCost)
        totalCost shouldNotBe 0
    }
}