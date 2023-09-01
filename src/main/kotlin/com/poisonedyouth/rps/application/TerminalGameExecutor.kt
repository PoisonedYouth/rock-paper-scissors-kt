package com.poisonedyouth.rps.application

import arrow.core.getOrElse
import com.poisonedyouth.rps.domain.game.Game
import com.poisonedyouth.rps.domain.game.game.GameExecutor
import com.poisonedyouth.rps.domain.game.game.GameInput
import com.poisonedyouth.rps.domain.player.Player
import com.poisonedyouth.rps.domain.player.RoundResultStore
import com.poisonedyouth.rps.domain.storage.GameResultStorage
import org.slf4j.Logger
import org.slf4j.LoggerFactory

private val logger: Logger = LoggerFactory.getLogger(TerminalGameExecutor::class.java)

const val ROUNDS = 100

class TerminalGameExecutor(
    private val game: Game,
    private val gameResultStorage: GameResultStorage
) : GameExecutor {

    override fun execute(gameInput: GameInput) {
        val player1 = Player.createFrom(
            name = gameInput.player1Name,
            availableOptions = gameInput.player1AvailableOptions
        ).getOrElse {
            logger.error("Failed to create player 1 because '${it.message}'")
            return
        }
        val player2 = Player.createFrom(
            name = gameInput.player2Name,
            availableOptions = gameInput.player2AvailableOptions
        ).getOrElse {
            logger.error("Failed to create player 2 because '${it.message}'")
            return
        }

        val gameResult = game.play(player1, player2, ROUNDS)
        gameResultStorage.saveGameResult(gameResult).onLeft {
            logger.warn("Unable to store game result to storage because of '${it.message}'.")
        }
        logger.info(gameResult.player1Result.createResultString())
        logger.info(gameResult.player2Result.createResultString())
    }

    private fun Pair<String, RoundResultStore>.createResultString(): String {
        return """
        Player (${this.first}):
            WINS:${this.second.wins}
            DRAWS:${this.second.draws}
            LOSSES:${this.second.losses}
        """.trimIndent()
    }
}
