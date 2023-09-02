package com.poisonedyouth.rps.domain.storage

import com.poisonedyouth.rps.application.GameResultFileStorage
import com.poisonedyouth.rps.domain.game.GameResult
import com.poisonedyouth.rps.domain.player.RoundResultStore
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption

class GameResultFileStorageTest {

    @TempDir
    lateinit var tempDir: Path

    @Test
    fun `saveGameResult saves result to file when file not exists`() {
        // given
        val storageFilePath = tempDir.resolve("results.txt")
        val gameResultFileStorage = GameResultFileStorage(storageFilePath)

        val gameResult = GameResult(
            player1Result = "Player 1" to RoundResultStore(1, 1, 1),
            player2Result = "Player 2" to RoundResultStore(2, 2, 2)
        )

        // when
        gameResultFileStorage.saveGameResult(gameResult.toGameResultDto())

        // then
        val actual = Files.readAllLines(storageFilePath)
        actual[0] shouldBe "Player (Player 1):"
        actual[1] shouldBe "    WINS:1"
        actual[2] shouldBe "    DRAWS:1"
        actual[3] shouldBe "    LOSSES:1"
        actual[4] shouldBe "Player (Player 2):"
        actual[5] shouldBe "    WINS:2"
        actual[6] shouldBe "    DRAWS:2"
        actual[7] shouldBe "    LOSSES:2"
    }

    @Test
    fun `saveGameResult saves result to file when file already exists`() {
        // given
        val storageFilePath = tempDir.resolve("results.txt")
        Files.writeString(storageFilePath, "ExistingContent\n", StandardOpenOption.CREATE)

        val gameResultFileStorage = GameResultFileStorage(storageFilePath)

        val gameResult = GameResult(
            player1Result = "Player 1" to RoundResultStore(1, 1, 1),
            player2Result = "Player 2" to RoundResultStore(2, 2, 2)
        )

        // when
        gameResultFileStorage.saveGameResult(gameResult.toGameResultDto())

        // then
        val actual = Files.readAllLines(storageFilePath)
        actual[0] shouldBe "ExistingContent"
        actual[1] shouldBe "Player (Player 1):"
        actual[2] shouldBe "    WINS:1"
        actual[3] shouldBe "    DRAWS:1"
        actual[4] shouldBe "    LOSSES:1"
        actual[5] shouldBe "Player (Player 2):"
        actual[6] shouldBe "    WINS:2"
        actual[7] shouldBe "    DRAWS:2"
        actual[8] shouldBe "    LOSSES:2"
    }
}
