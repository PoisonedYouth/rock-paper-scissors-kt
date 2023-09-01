package com.poisonedyouth.rps.application

import com.poisonedyouth.rps.domain.game.DefaultGame
import com.poisonedyouth.rps.domain.game.Game
import com.poisonedyouth.rps.domain.game.SpecialGame

object GameFactory {
    fun getGame(type: GameType): Game = when (type) {
        GameType.SPECIAL -> SpecialGame
        GameType.DEFAULT -> DefaultGame
    }
}

enum class GameType {
    DEFAULT,
    SPECIAL
}
