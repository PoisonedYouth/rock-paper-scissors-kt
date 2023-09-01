package com.poisonedyouth.rps.application

import arrow.core.Either
import arrow.core.raise.either
import com.poisonedyouth.rps.domain.game.GameResult
import com.poisonedyouth.rps.domain.storage.GameResultStorage
import com.poisonedyouth.rps.failure.Failure
import com.poisonedyouth.rps.failure.eval
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import kotlin.io.path.notExists

class GameResultFileStorage(
    private val storageFilePath: Path
) : GameResultStorage {
    override fun saveGameResult(gameResult: GameResult): Either<Failure, Unit> = either {
        // Need to wrap in eval - block because of potential IOExceptions.
        eval {
            if (storageFilePath.notExists()) {
                Files.createFile(storageFilePath)
            }
            Files.write(storageFilePath, gameResult.formatResultForStorage(), StandardOpenOption.APPEND)
        }
    }

    private fun GameResult.formatResultForStorage(): List<String> = listOf(
        """
        Player (${this.player1Result.first}):
            WINS:${this.player1Result.second.wins}
            DRAWS:${this.player1Result.second.draws}
            LOSSES:${this.player1Result.second.losses}
        """.trimIndent(),
        """
        Player (${this.player2Result.first}):
            WINS:${this.player2Result.second.wins}
            DRAWS:${this.player2Result.second.draws}
            LOSSES:${this.player2Result.second.losses}
        """.trimIndent()
    )
}
