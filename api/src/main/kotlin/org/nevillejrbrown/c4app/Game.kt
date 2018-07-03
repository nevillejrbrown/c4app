package org.nevillejrbrown.c4app

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

class InvalidMoveException(msg: String) : Exception(msg)
data class Move(val colNum: Int, val mark: Mark)

@Entity
data class GameState( //val result: GameResult,
        val whosTurnItIs: Mark,
        val pieces: Array<Array<Mark>>,
        val numRows: Int,
        val numCols: Int,
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var gameId: Long = 0) {

    companion object Factory{
        fun createInitialisedGameState(numRows: Int, numCols: Int):GameState {
            return GameState(
                    Mark.P1,
                    Array(numRows, { _ -> Array(numCols, { _ -> Mark.EMPTY }) }),
                    numRows,
                    numCols
            )
        }
    }


}

data class Position(val rowNum: Int, val colNum: Int) {
    fun isValidPositionOnBoard(game: Game): Boolean {
        return rowNum >= 0 &&
                rowNum < game.gameState.numRows &&
                colNum >= 0 &&
                colNum < game.gameState.numCols
    }
}

//class Game(var numRows: Int, var numCols: Int, var id: Long = 0) {
class Game(var gameState: GameState) {

    companion object {
        val winningRowSize = 4
    }


    fun isRoomInColumn(colNum: Int): Boolean {
        return gameState.pieces[colNum].contains(Mark.EMPTY)
    }

    fun addCounter(colNum: Int, mark: Mark): GameResult {
        if (colNum >= gameState.numCols || colNum < 0)
            throw InvalidMoveException("There's no column in position $colNum")
        if (!isRoomInColumn(colNum))
            throw InvalidMoveException("There's no room in column $colNum")

        val firstEmptySpotIndex: Int = gameState.pieces[colNum].indexOf(Mark.EMPTY)
        gameState.pieces[colNum][firstEmptySpotIndex] = mark

        if ((1 + howManyOfThisMarkInADirection(mark, 0,
                Position(firstEmptySpotIndex, colNum), ::goSouth) >= Game.winningRowSize) ||
                (1 + howManyOfThisMarkInTwoDirections(mark, 0,
                        Position(firstEmptySpotIndex, colNum), ::goEast, ::goWest) >= Game.winningRowSize) ||
                (1 + howManyOfThisMarkInTwoDirections(mark, 0,
                        Position(firstEmptySpotIndex, colNum), ::goNorthEast, ::goSouthWest) >= Game.winningRowSize)
                ||
                (1 + howManyOfThisMarkInTwoDirections(mark, 0,
                        Position(firstEmptySpotIndex, colNum), ::goNorthWest, ::goSouthEast) >= Game.winningRowSize)
                )
            return GameResult(GameStateName.WON, mark)
        else return GameResult(GameStateName.IN_PLAY, null)
    }

    fun getMarkAtPosition(position: Position): Mark {
        return gameState.pieces[position.colNum][position.rowNum]
    }

    private fun goSouth(position: Position): Position {
        return Position(position.rowNum - 1, position.colNum)
    }

    private fun goEast(position: Position): Position {
        return Position(position.rowNum, position.colNum - 1)
    }

    private fun goWest(position: Position): Position {
        return Position(position.rowNum, position.colNum + 1)
    }

    private fun goNorthWest(position: Position): Position {
        return Position(position.rowNum + 1, position.colNum - 1)
    }

    private fun goNorthEast(position: Position): Position {
        return Position(position.rowNum + 1, position.colNum + 1)
    }

    private fun goSouthWest(position: Position): Position {
        return Position(position.rowNum - 1, position.colNum - 1)
    }

    private fun goSouthEast(position: Position): Position {
        return Position(position.rowNum - 1, position.colNum + 1)
    }

    private fun howManyOfThisMarkInTwoDirections(mark: Mark,
                                                 numberFound: Int,
                                                 previousPosition: Position,
                                                 positionModifier1: (Position) -> Position,
                                                 positionModifier2: (Position) -> Position): Int {
        return howManyOfThisMarkInADirection(mark, numberFound, previousPosition, positionModifier1) +
                howManyOfThisMarkInADirection(mark, numberFound, previousPosition, positionModifier2)

    }

    // Moves position by the position modifier and sees if it's the specified mark.  Then recursively
    // keeps looking until it's done this a specified number of times or it's found a different mark, or
    // gets to an invalid mark (i.e. goes off an edge of the board.
    private fun howManyOfThisMarkInADirection(mark: Mark,
                                              numberFound: Int,
                                              previousPosition: Position,
                                              positionModifier: (Position) -> Position): Int {
        val newPosition: Position = positionModifier(previousPosition)
        if (newPosition.isValidPositionOnBoard(this)) {
            if (this.getMarkAtPosition(newPosition) == mark) {
                return (howManyOfThisMarkInADirection(mark, numberFound + 1, newPosition, positionModifier))
            } else return numberFound
        } else return numberFound
    }
}