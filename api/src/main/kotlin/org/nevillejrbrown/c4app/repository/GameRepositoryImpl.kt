package org.nevillejrbrown.c4app.repository

class NotFoundException(val msg: String) : Exception(msg)

//@Repository
//class GameRepositoryImpl {
//    val games: MutableMap<Int, Game> = HashMap<Int, Game>()
//
//    fun getGame(gameId: Int): Game {
//        return games.get(gameId) ?: throw NotFoundException("Can't find game with id $gameId")
//    }
//
//    fun createGame(numRows: Int, numCols: Int): Int {
//        println("Creating game")
//        val maxGameKey: Int = games.keys.max() ?: 0
//        games.put(maxGameKey + 1, Game(maxGameKey + 1, 6, 7))
//        return maxGameKey
//    }
//
//    fun getGames(): List<Game> {
//        return ArrayList<Game>(games.values)
//    }
//
//
//    fun removeGame(gameId: Int) {
//        games.remove(gameId)
//    }
//
//
//    fun saveGame(game: Game) {
//        games.replace(game.id, game)
//    }
//}