package com.poisonedyouth.rps.domain.player

import com.poisonedyouth.rps.failure.Failure
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `two players with different names are treated as different`() {
        // given
        val player1 = Player.createFrom(
            name = "Player 1",
            availableOptions = listOf(PlayOption.PAPER)
        ).shouldBeRight()
        val player2 = Player.createFrom(
            name = "Player 2",
            availableOptions = listOf(PlayOption.PAPER)
        ).shouldBeRight()

        // when
        val actual = player1 == player2

        // then
        actual shouldBe false
    }

    @Test
    fun `two players with same name are treated as equal`() {
        // given
        val player1 = Player.createFrom(
            name = "Player 1",
            availableOptions = listOf(PlayOption.PAPER)
        ).shouldBeRight()
        val player2 = Player.createFrom(
            name = player1.name,
            availableOptions = listOf(PlayOption.PAPER)
        ).shouldBeRight()

        // when
        val actual = player1 == player2

        // then
        actual shouldBe true
    }

    @Test
    fun `createFrom returns validation failure if no play options are provided`() {
        // given
        val availableOptions = emptyList<PlayOption>()

        // when
        val actual = Player.createFrom(
            name = "Player 1",
            availableOptions = availableOptions
        )

        // then
        actual.shouldBeLeft().shouldBeTypeOf<Failure.ValidationFailure>()
    }

    @Test
    fun `createFrom returns validation failure if name is empty`() {
        // given
        val availableOptions = listOf(
            PlayOption.PAPER
        )

        // when
        val actual = Player.createFrom(
            name = "",
            availableOptions = availableOptions
        )

        // then
        actual.shouldBeLeft().shouldBeTypeOf<Failure.ValidationFailure>()
    }

    @Test
    fun `chooseOption returns result for player with single option`() {
        // given
        val player = Player.createFrom(
            name = "Player 1",
            availableOptions = listOf(PlayOption.PAPER)
        ).shouldBeRight()

        // when
        val actual = player.chooseOption()

        // then
        actual shouldBe PlayOption.PAPER
    }

    @Test
    fun `chooseOption returns all available options for player with multiple options`() {
        // given
        val player = Player.createFrom(
            name = "Player 1",
            availableOptions = listOf(PlayOption.PAPER, PlayOption.ROCK, PlayOption.SCISSORS)
        ).shouldBeRight()

        // when
        val actual: MutableSet<PlayOption> = mutableSetOf()
        repeat(100) {
            actual.add(player.chooseOption())
        }

        // then
        actual shouldContainAll PlayOption.entries // There is the risk that this can fail
    }

    @Test
    fun `recordGame adds new GameResult with value of 1 when not exist`() {
        // given
        val player = Player.createFrom(
            name = "Player 1",
            availableOptions = listOf(PlayOption.PAPER, PlayOption.ROCK, PlayOption.SCISSORS)
        ).shouldBeRight()

        // when
        player.recordGame(RoundResult.DRAW)

        // then
        player.getResultStore().draws shouldBe 1
    }

    @Test
    fun `recordGame increments GameResult record when already exist`() {
        // given
        val player = Player.createFrom(
            name = "Player 1",
            availableOptions = listOf(PlayOption.PAPER, PlayOption.ROCK, PlayOption.SCISSORS)
        ).shouldBeRight()
        player.recordGame(RoundResult.DRAW)

        // when
        player.recordGame(RoundResult.DRAW)

        // then
        player.getResultStore().draws shouldBe 2
    }

    @Test
    fun `getResult returns result for all GameResults`() {
        // given
        val player = Player.createFrom(
            name = "Player 1",
            availableOptions = listOf(PlayOption.PAPER, PlayOption.ROCK, PlayOption.SCISSORS)
        ).shouldBeRight()
        player.recordGame(RoundResult.WIN)
        player.recordGame(RoundResult.DRAW)

        // when
        val actual = player.getResultStore()

        // then
        actual.wins shouldBe 1
        actual.draws shouldBe 1
        actual.losses shouldBe 0
    }
}
