package com.poisonedyouth.rps.application

import arrow.core.Either
import arrow.core.raise.either
import com.poisonedyouth.rps.domain.storage.GameResultDto
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
    override fun saveGameResult(gameResult: GameResultDto): Either<Failure, Unit> = either {
        // Need to wrap in eval - block because of potential IOExceptions.
        eval {
            if (storageFilePath.notExists()) {
                Files.createFile(storageFilePath)
            }
            Files.write(storageFilePath, gameResult.formatResultForStorage(), StandardOpenOption.APPEND)
        }
    }

    private fun GameResultDto.formatResultForStorage(): List<String> = listOf(
        """
        Player (${this.player1Name}):
            WINS:${this.player1Wins}
            DRAWS:${this.player1Draws}
            LOSSES:${this.player1Losses}
        """.trimIndent(),
        """
        Player (${this.player2Name}):
            WINS:${this.player2Wins}
            DRAWS:${this.player2Draws}
            LOSSES:${this.player2Losses}
        """.trimIndent()
    )
}
