package org.nevillejrbrown.c4app

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TestGame {

    @Test
    fun `Test Add a Piece`() {
        val board = Game(GameState.createInitialisedGameState(6, 7))
        board.addCounter(1, Mark.X)
        board.addCounter(1, Mark.O)

        assertTrue(board.getMarkAtPosition(Position(1, 1)) == Mark.O)

    }


    @Test
    fun `Test room in column with room`() {
        val board = Game(GameState.createInitialisedGameState(3, 3))
        board.addCounter(2, Mark.X)
        board.addCounter(2, Mark.O)
        assertTrue(board.isRoomInColumn(2))
    }

    @Test
    fun `Test room in column with NO room`() {
        val board = Game(GameState.createInitialisedGameState(3, 3))
        board.addCounter(2, Mark.X)
        board.addCounter(2, Mark.O)
        board.addCounter(2, Mark.X)
        assertFalse(board.isRoomInColumn(2))
    }

    @Test
    fun `Test adding move to full row throws exception`() {
        val board = Game(GameState.createInitialisedGameState(3, 3))
        board.addCounter(2, Mark.X)
        board.addCounter(2, Mark.O)
        board.addCounter(2, Mark.X)
        assertFailsWith(InvalidMoveException::class) {
            board.addCounter(2, Mark.O)
        }
    }

    @Test
    fun `Test adding move to non existent row throws exception`() {
        val board = Game(GameState.createInitialisedGameState(3, 3))
        assertFailsWith(InvalidMoveException::class) {
            board.addCounter(3, Mark.X)
        }
    }

    @Test
    fun `Test getResult spots vertical four in a row`() {
        val board = Game(GameState.createInitialisedGameState(6, 7))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(3, Mark.X))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(2, Mark.O))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(3, Mark.X))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(2, Mark.O))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(3, Mark.X))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(2, Mark.O))
        assertEquals(GameResult(GameStateName.WON, Mark.X), board.addCounter(3, Mark.X))
    }

    @Test
    fun `Test getResult spots horizontal four in a row`() {
        val board = Game(GameState.createInitialisedGameState(6, 7))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(0, Mark.X))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(0, Mark.O))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(1, Mark.X))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(1, Mark.O))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(2, Mark.X))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(2, Mark.O))
        assertEquals(GameResult(GameStateName.WON, Mark.X), board.addCounter(3, Mark.X))
    }

    @Test
    fun `Test getResult spots horizontal four in a row when adding middle counter`() {
        val board = Game(GameState.createInitialisedGameState(6, 7))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(0, Mark.X))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(0, Mark.O))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(2, Mark.X))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(2, Mark.O))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(3, Mark.X))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(3, Mark.O))
        assertEquals(GameResult(GameStateName.WON, Mark.X), board.addCounter(1, Mark.X))
    }

    @Test
    fun `Test getResult spots leading diagonal`() {
        val board = Game(GameState.createInitialisedGameState(6, 7))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(3, Mark.X))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(0, Mark.O))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(1, Mark.X))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(1, Mark.O))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(2, Mark.X))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(3, Mark.O))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(2, Mark.X))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(2, Mark.O))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(3, Mark.X))
        assertEquals(GameResult(GameStateName.WON, Mark.O), board.addCounter(3, Mark.O))
    }

    @Test
    fun `Test getResult spots trailing diagonal`() {
        val board = Game(GameState.createInitialisedGameState(6, 7))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(0, Mark.X))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(3, Mark.O))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(2, Mark.X))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(2, Mark.O))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(1, Mark.X))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(0, Mark.O))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(1, Mark.X))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(1, Mark.O))
        assertEquals(GameResult(GameStateName.IN_PLAY, null), board.addCounter(0, Mark.X))
        assertEquals(GameResult(GameStateName.WON, Mark.O), board.addCounter(0, Mark.O))
    }

    @Test(expected = GameStateException::class)
    fun `Test play move with same counter twice consecutively throws error`() {
        val board = Game(GameState.createInitialisedGameState(6, 7))
        board.addCounter(1, Mark.X)
        board.addCounter(1, Mark.X)
    }
}