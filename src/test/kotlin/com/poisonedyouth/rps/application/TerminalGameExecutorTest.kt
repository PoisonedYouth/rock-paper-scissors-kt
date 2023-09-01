package com.poisonedyouth.rps.application

import com.poisonedyouth.rps.domain.game.Game
import com.poisonedyouth.rps.domain.game.GameResult
import com.poisonedyouth.rps.domain.game.game.GameInput
import com.poisonedyouth.rps.domain.player.PlayOption
import com.poisonedyouth.rps.domain.player.Player
import com.poisonedyouth.rps.domain.player.RoundResultStore
import io.kotest.assertions.arrow.core.shouldBeRight
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class TerminalGameExecutorTest {

    private val game: Game = mock()
    private val gameExecutor: TerminalGameExecutor = TerminalGameExecutor(game)

    @Test
    fun `execute does not play game when player1 input is invalid`() {
        // given
        val gameInput = GameInput(
            player1Name = "",
            player1AvailableOptions = listOf(PlayOption.ROCK),
            player2Name = "Random Player",
            player2AvailableOptions = listOf(PlayOption.ROCK, PlayOption.PAPER, PlayOption.SCISSORS)
        )

        // when
        gameExecutor.execute(gameInput)

        // then
        verify(game, never()).play(any(), any(), any())
    }

    @Test
    fun `execute does not play game when player2 input is invalid`() {
        // given
        val gameInput = GameInput(
            player1Name = "Rock Player",
            player1AvailableOptions = listOf(PlayOption.ROCK),
            player2Name = "Random Player",
            player2AvailableOptions = emptyList()
        )

        // when
        gameExecutor.execute(gameInput)

        // then
        verify(game, never()).play(any(), any(), any())
    }

    @Test
    fun `execute plays game when input is valid`() {
        // given
        val gameInput = GameInput(
            player1Name = "Rock Player",
            player1AvailableOptions = listOf(PlayOption.ROCK),
            player2Name = "Random Player",
            player2AvailableOptions = listOf(PlayOption.ROCK, PlayOption.PAPER, PlayOption.SCISSORS)
        )

        whenever(game.play(any(), any(), any())).thenReturn(
            GameResult(
                player1Result = "Rock Player" to RoundResultStore(),
                player2Result = "Random Player" to RoundResultStore()
            )
        )

        // when
        gameExecutor.execute(gameInput)

        // then
        verify(game).play(
            player1 = Player.createFrom(
                name = gameInput.player1Name,
                availableOptions = gameInput.player1AvailableOptions
            ).shouldBeRight(),
            player2 = Player.createFrom(
                name = gameInput.player2Name,
                availableOptions = gameInput.player2AvailableOptions
            ).shouldBeRight(),
            rounds = ROUNDS
        )
    }
}
