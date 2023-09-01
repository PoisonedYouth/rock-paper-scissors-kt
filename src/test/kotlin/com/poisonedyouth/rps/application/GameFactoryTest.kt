package com.poisonedyouth.rps.application

import com.poisonedyouth.rps.domain.game.DefaultGame
import com.poisonedyouth.rps.domain.game.SpecialGame
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.junit.jupiter.api.Test

class GameFactoryTest {

    @Test
    fun `getGame returns SpecialGame for type special`() {
        // given
        val type = GameType.SPECIAL

        // when
        val actual = GameFactory.getGame(type)

        // then
        actual shouldBeSameInstanceAs SpecialGame
    }

    @Test
    fun `getGame returns DefaultGame for type default`() {
        // given
        val type = GameType.DEFAULT

        // when
        val actual = GameFactory.getGame(type)

        // then
        actual shouldBeSameInstanceAs DefaultGame
    }
}
