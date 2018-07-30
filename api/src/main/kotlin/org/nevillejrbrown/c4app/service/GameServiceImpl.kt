package org.nevillejrbrown.c4app.service

import org.nevillejrbrown.c4app.Game
import org.nevillejrbrown.c4app.GameResult
import org.nevillejrbrown.c4app.GameState
import org.nevillejrbrown.c4app.Mark
import org.nevillejrbrown.c4app.repository.GameRepository
import org.springframework.stereotype.Service

@Service
class GameServiceImpl(val gameRepository: GameRepository) : GameService {
    override fun getAllGames(): List<Game> {
        return gameRepository.findAll().map { Game(it) }
    }

    override fun playMove(gameId: Long, colNum: Int, mark: Mark): GameResult {
        val game:Game = Game(gameRepository.getOne(gameId))
        val result:GameResult = game.addCounter(colNum, mark)
        gameRepository.saveAndFlush(game.gameState)
        return result
    }

    override fun removeGame(gameId: Long) {
        gameRepository.deleteById(gameId)
    }

    override fun getGame(gameId: Long):Game {
        return Game(gameRepository.getOne(gameId))
    }

    override fun createGame(): Game {
        val game: Game = Game(GameState.createInitialisedGameState(6,7))
        println("In service; creating game in repository")
        game.gameState = gameRepository.saveAndFlush(game.gameState)
        return game
    }
}