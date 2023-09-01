package com.poisonedyouth.rps.domain.game

import com.poisonedyouth.rps.domain.player.PlayOption
import com.poisonedyouth.rps.domain.player.Player
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test

class DefaultGameTest {

    @Test
    fun `play returns result for zero rounds`() {
        // given
        val player1 = Player.createFrom(
            name = "First",
            availableOptions = listOf(PlayOption.PAPER)
        ).shouldBeRight()

        val player2 = Player.createFrom(
            name = "Second",
            availableOptions = listOf(PlayOption.PAPER)
        ).shouldBeRight()

        val rounds = 0

        // when
        val actual = DefaultGame.play(
            player1 = player1,
            player2 = player2,
            rounds = rounds
        )

        // then
        actual.player1Result.run {
            this.first shouldBe player1.name
            this.second.wins shouldBe 0
            this.second.draws shouldBe 0
            this.second.losses shouldBe 0
        }
        actual.player2Result.run {
            this.first shouldBe player2.name
            this.second.wins shouldBe 0
            this.second.draws shouldBe 0
            this.second.losses shouldBe 0
        }
    }

    @Test
    fun `play returns result for one round`() {
        // given
        val player1 = Player.createFrom(
            name = "First",
            availableOptions = listOf(PlayOption.PAPER)
        ).shouldBeRight()

        val player2 = Player.createFrom(
            name = "Second",
            availableOptions = listOf(PlayOption.ROCK)
        ).shouldBeRight()

        val rounds = 1

        // when
        val actual = DefaultGame.play(
            player1 = player1,
            player2 = player2,
            rounds = rounds
        )

        // then
        actual.player1Result.run {
            this.first shouldBe player1.name
            this.second.wins shouldBe 1
            this.second.draws shouldBe 0
            this.second.losses shouldBe 0
        }
        actual.player2Result.run {
            this.first shouldBe player2.name
            this.second.wins shouldBe 0
            this.second.draws shouldBe 0
            this.second.losses shouldBe 1
        }
    }

    @Test
    fun `play returns result for multiple rounds static result`() {
        // given
        val player1 = Player.createFrom(
            name = "First",
            availableOptions = listOf(PlayOption.PAPER)
        ).shouldBeRight()

        val player2 = Player.createFrom(
            name = "Second",
            availableOptions = listOf(PlayOption.ROCK)
        ).shouldBeRight()

        val rounds = 3

        // when
        val actual = DefaultGame.play(
            player1 = player1,
            player2 = player2,
            rounds = rounds
        )

        // then
        actual.player1Result.run {
            this.first shouldBe player1.name
            this.second.wins shouldBe 3
            this.second.draws shouldBe 0
            this.second.losses shouldBe 0
        }
        actual.player2Result.run {
            this.first shouldBe player2.name
            this.second.wins shouldBe 0
            this.second.draws shouldBe 0
            this.second.losses shouldBe 3
        }
    }

    @Test
    fun `play returns result for multiple rounds random result`() {
        // given
        val player1 = Player.createFrom(
            name = "First",
            availableOptions = listOf(PlayOption.PAPER, PlayOption.ROCK, PlayOption.SCISSORS)
        ).shouldBeRight()

        val player2 = Player.createFrom(
            name = "Second",
            availableOptions = listOf(PlayOption.PAPER, PlayOption.ROCK, PlayOption.SCISSORS)
        ).shouldBeRight()

        val rounds = 100

        // when
        val actual = DefaultGame.play(
            player1 = player1,
            player2 = player2,
            rounds = rounds
        )

        // then
        actual.player1Result.run {
            this.first shouldBe player1.name
            this.second.wins shouldNotBe 0
            this.second.draws shouldNotBe 0
            this.second.losses shouldNotBe 0
        }
        actual.player2Result.run {
            this.first shouldBe player2.name
            this.second.wins shouldNotBe 0
            this.second.draws shouldNotBe 0
            this.second.losses shouldNotBe 0
        }
    }
}
