package com.poisonedyouth.rps

import com.poisonedyouth.rps.application.GameFactory
import com.poisonedyouth.rps.application.GameResultFileStorage
import com.poisonedyouth.rps.application.GameType
import com.poisonedyouth.rps.application.TerminalGameExecutor
import com.poisonedyouth.rps.domain.game.GameInput
import com.poisonedyouth.rps.domain.player.PlayOption
import java.nio.file.Paths

fun main() {
    val game = GameFactory.getGame(GameType.DEFAULT)
    val gameResultStorage = GameResultFileStorage(Paths.get("./results.txt"))
    TerminalGameExecutor(
        game = game,
        gameResultStorage = gameResultStorage
    ).execute(
        GameInput(
            player1Name = "Rock Player",
            player1AvailableOptions = listOf(PlayOption.ROCK),
            player2Name = "Random Player",
            player2AvailableOptions = listOf(PlayOption.ROCK, PlayOption.PAPER, PlayOption.SCISSORS)
        )
    )
}
