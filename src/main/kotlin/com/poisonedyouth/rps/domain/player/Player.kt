package com.poisonedyouth.rps.domain.player

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import com.poisonedyouth.rps.failure.Failure

class Player private constructor(
    val name: String,
    val availableOptions: List<PlayOption>
) {
    private var roundResultStore: RoundResultStore = RoundResultStore()

    fun getResultStore(): RoundResultStore = roundResultStore

    fun recordGame(roundResult: RoundResult) {
        roundResultStore = when (roundResult) {
            RoundResult.WIN -> roundResultStore.copy(
                wins = roundResultStore.wins.inc()
            )

            RoundResult.LOSS -> roundResultStore.copy(
                losses = roundResultStore.losses.inc()
            )

            RoundResult.DRAW -> roundResultStore.copy(
                draws = roundResultStore.draws.inc()
            )
        }
    }

    fun chooseOption(): PlayOption = availableOptions.shuffled().random()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        if (name != other.name) return false
        if (availableOptions != other.availableOptions) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + availableOptions.hashCode()
        return result
    }

    companion object {
        fun createFrom(
            name: String,
            availableOptions: List<PlayOption>
        ): Either<Failure, Player> =
            either {
                ensure(availableOptions.isNotEmpty()) {
                    Failure.ValidationFailure("Player requires at minimum one PlayOption.")
                }
                ensure(name.isNotEmpty()) {
                    Failure.ValidationFailure("Name must not be empty.")
                }
                Player(
                    name = name,
                    availableOptions = availableOptions
                )
            }
    }
}

data class RoundResultStore(
    val wins: Int = 0,
    val losses: Int = 0,
    val draws: Int = 0
)
