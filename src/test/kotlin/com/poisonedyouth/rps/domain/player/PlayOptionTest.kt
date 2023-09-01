package com.poisonedyouth.rps.domain.player

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayOptionTest {

    @Test
    fun `evaluate returns draw for this and other option are the same`() {
        // given
        val playOption = PlayOption.SCISSORS

        // when
        val actual = playOption.evaluate(PlayOption.SCISSORS)

        // then
        actual shouldBe RoundResult.DRAW
    }

    @Test
    fun `evaluate returns win for this scissors and other option paper`() {
        // given
        val playOption = PlayOption.SCISSORS

        // when
        val actual = playOption.evaluate(PlayOption.PAPER)

        // then
        actual shouldBe RoundResult.WIN
    }

    @Test
    fun `evaluate returns win for this paper and other option rock`() {
        // given
        val playOption = PlayOption.PAPER

        // when
        val actual = playOption.evaluate(PlayOption.ROCK)

        // then
        actual shouldBe RoundResult.WIN
    }

    @Test
    fun `evaluate returns win for this rock and other option scissors`() {
        // given
        val playOption = PlayOption.ROCK

        // when
        val actual = playOption.evaluate(PlayOption.SCISSORS)

        // then
        actual shouldBe RoundResult.WIN
    }

    @Test
    fun `evaluate returns loss for this rock and other option paper`() {
        // given
        val playOption = PlayOption.ROCK

        // when
        val actual = playOption.evaluate(PlayOption.PAPER)

        // then
        actual shouldBe RoundResult.LOSS
    }

    @Test
    fun `evaluate returns loss for this paper and other option scissors`() {
        // given
        val playOption = PlayOption.PAPER

        // when
        val actual = playOption.evaluate(PlayOption.SCISSORS)

        // then
        actual shouldBe RoundResult.LOSS
    }

    @Test
    fun `evaluate returns loss for this scissors and other option rock`() {
        // given
        val playOption = PlayOption.SCISSORS

        // when
        val actual = playOption.evaluate(PlayOption.ROCK)

        // then
        actual shouldBe RoundResult.LOSS
    }
}
