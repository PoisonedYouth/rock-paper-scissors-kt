package com.poisonedyouth.rps.domain.game

import com.poisonedyouth.rps.domain.player.Player
import com.poisonedyouth.rps.domain.player.RoundResult

object DefaultGame : Game {

    override fun play(player1: Player, player2: Player, rounds: Int): GameResult {
        repeat(rounds) {
            playRound(player1, player2)
        }

        return GameResult(
            player1Result = player1.name to player1.getResultStore(),
            player2Result = player2.name to player2.getResultStore()
        )
    }

    private fun playRound(player1: Player, player2: Player) {
        val playOption1 = player1.chooseOption()
        val playOption2 = player2.chooseOption()
        when (playOption1.evaluate(playOption2)) {
            RoundResult.WIN -> {
                player1.recordGame(RoundResult.WIN)
                player2.recordGame(RoundResult.LOSS)
            }

            RoundResult.LOSS -> {
                player1.recordGame(RoundResult.LOSS)
                player2.recordGame(RoundResult.WIN)
            }

            RoundResult.DRAW -> {
                player1.recordGame(RoundResult.DRAW)
                player2.recordGame(RoundResult.DRAW)
            }
        }
    }
}
