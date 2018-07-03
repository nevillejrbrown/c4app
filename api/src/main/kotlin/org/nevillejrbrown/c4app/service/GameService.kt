package org.nevillejrbrown.c4app.service

import org.nevillejrbrown.c4app.Game
import org.springframework.stereotype.Service

@Service
interface GameService {
    fun createGame(): Game
    fun getAllGames(): List<Game>
//
//    fun playMove(gameId:Int, colNum:Int, mark:Mark):GameResult
//
    fun removeGame(gameId:Long)

    fun getGame(gameId:Long):Game
}