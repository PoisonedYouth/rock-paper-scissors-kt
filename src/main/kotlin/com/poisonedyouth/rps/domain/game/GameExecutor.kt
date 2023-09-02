package com.poisonedyouth.rps.domain.game

import com.poisonedyouth.rps.domain.player.PlayOption

interface GameExecutor {

    fun execute(gameInput: GameInput)
}

data class GameInput(
    val player1Name: String,
    val player1AvailableOptions: List<PlayOption>,
    val player2Name: String,
    val player2AvailableOptions: List<PlayOption>
)
