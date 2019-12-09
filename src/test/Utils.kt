package test

import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

infix fun Any.shouldBe(expected: Any) = assertEquals(expected, this)

infix fun Any.shouldNotBe(expected: Any) = assertNotEquals(expected, this)