package com.poisonedyouth.rps.domain.storage

import arrow.core.Either
import com.poisonedyouth.rps.domain.game.GameResult
import com.poisonedyouth.rps.failure.Failure

interface GameResultStorage {

    fun saveGameResult(gameResult: GameResultDto): Either<Failure, Unit>
}

data class GameResultDto(
    val player1Name: String,
    val player1Wins: Int,
    val player1Draws: Int,
    val player1Losses: Int,
    val player2Name: String,
    val player2Wins: Int,
    val player2Draws: Int,
    val player2Losses: Int
)

fun GameResult.toGameResultDto() = GameResultDto(
    player1Name = this.player1Result.first,
    player1Wins = this.player1Result.second.wins,
    player1Draws = this.player1Result.second.draws,
    player1Losses = this.player1Result.second.losses,
    player2Name = this.player2Result.first,
    player2Wins = this.player2Result.second.wins,
    player2Draws = this.player2Result.second.draws,
    player2Losses = this.player2Result.second.losses
)
