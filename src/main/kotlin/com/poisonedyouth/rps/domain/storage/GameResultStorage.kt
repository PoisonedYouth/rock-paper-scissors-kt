package com.poisonedyouth.rps.domain.storage

import arrow.core.Either
import com.poisonedyouth.rps.domain.game.GameResult
import com.poisonedyouth.rps.failure.Failure

interface GameResultStorage {

    fun saveGameResult(gameResult: GameResult): Either<Failure, Unit>
}
