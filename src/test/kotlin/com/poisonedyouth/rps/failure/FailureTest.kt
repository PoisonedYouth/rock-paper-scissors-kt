package com.poisonedyouth.rps.failure

import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import org.junit.jupiter.api.Test

class FailureTest {

    @Test
    fun `eval returns value if evaluation is successful`() {
        // given
        val evaluation = { 5 + 5 }

        // when
        val actual = eval {
            evaluation.invoke()
        }

        // then
        actual.shouldBeRight() shouldBe 10
    }

    @Test
    fun `eval returns generic failure if evaluation is throwing an exception`() {
        // given
        val evaluation = { 5 / 0 }

        // when
        val actual = eval {
            evaluation.invoke()
        }

        // then
        actual.shouldBeLeft().shouldBeTypeOf<Failure.GenericFailure>()
    }
}
