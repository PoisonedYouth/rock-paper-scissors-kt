package com.poisonedyouth.rps

import io.kotest.assertions.throwables.shouldNotThrowAny
import org.junit.jupiter.api.Test

class MainIntegrationTest {

    @Test
    fun `main is running successful`() {
        shouldNotThrowAny {
            main()
        }
    }
}
