package com.poisonedyouth.rps.domain.player

enum class PlayOption {
    ROCK,
    PAPER,
    SCISSORS;

    fun evaluate(playOption: PlayOption): RoundResult =
        when {
            this@PlayOption == playOption -> RoundResult.DRAW
            this@PlayOption == ROCK && playOption == PAPER -> RoundResult.LOSS
            this@PlayOption == ROCK && playOption == SCISSORS -> RoundResult.WIN
            this@PlayOption == SCISSORS && playOption == ROCK -> RoundResult.LOSS
            this@PlayOption == SCISSORS && playOption == PAPER -> RoundResult.WIN
            this@PlayOption == PAPER && playOption == ROCK -> RoundResult.WIN
            else -> RoundResult.LOSS
        }
}
