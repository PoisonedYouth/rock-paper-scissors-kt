package com.poisonedyouth.rps

import com.poisonedyouth.rps.application.GameFactory
import com.poisonedyouth.rps.application.GameType
import com.poisonedyouth.rps.application.TerminalGameExecutor
import com.poisonedyouth.rps.domain.game.game.GameInput
import com.poisonedyouth.rps.domain.player.PlayOption

fun main() {
    val game = GameFactory.getGame(GameType.DEFAULT)
    TerminalGameExecutor(game).execute(
        GameInput(
            player1Name = "Rock Player",
            player1AvailableOptions = listOf(PlayOption.ROCK),
            player2Name = "Random Player",
            player2AvailableOptions = listOf(PlayOption.ROCK, PlayOption.PAPER, PlayOption.SCISSORS)
        )
    )
}
