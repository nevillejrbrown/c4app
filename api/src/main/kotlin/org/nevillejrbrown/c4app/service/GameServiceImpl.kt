package org.nevillejrbrown.c4app.service

import org.nevillejrbrown.c4app.Game
import org.nevillejrbrown.c4app.GameState
import org.nevillejrbrown.c4app.repository.GameRepository
import org.springframework.stereotype.Service

@Service
class GameServiceImpl(val gameRepository: GameRepository) : GameService {
    override fun getAllGames(): List<Game> {
        return gameRepository.findAll().map { Game(it) }
    }
//
//    override fun playMove(gameId: Int, colNum: Int, mark:Mark): GameResult {
//        val game:Game = gameRepository.getGame(gameId)
//        val result:GameResult = game.addCounter(colNum, mark)
//        gameRepository.saveGame(game)
//        return result
//    }
//
    override fun removeGame(gameId: Long) {
        gameRepository.deleteById(gameId)
    }

    override fun getGame(gameId: Long):Game {
        return Game(gameRepository.getOne(gameId))
    }

    override fun createGame(): Game {
        val game: Game = Game(GameState.createInitialisedGameState(2,2))
        println("In service; creating game in repository")
        game.gameState = gameRepository.saveAndFlush(game.gameState)
        return game
    }
}