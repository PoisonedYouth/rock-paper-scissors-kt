package com.poisonedyouth.rps.domain.game

import com.poisonedyouth.rps.domain.player.Player
import com.poisonedyouth.rps.domain.player.RoundResultStore

interface Game {

    fun play(player1: Player, player2: Player, rounds: Int): GameResult
}

data class GameResult(
    val player1Result: Pair<String, RoundResultStore>,
    val player2Result: Pair<String, RoundResultStore>
)
