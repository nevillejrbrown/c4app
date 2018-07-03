package org.nevillejrbrown.c4app.controller


import org.nevillejrbrown.c4app.Game
import org.nevillejrbrown.c4app.GameResult
import org.nevillejrbrown.c4app.GameState
import org.nevillejrbrown.c4app.Mark
import org.nevillejrbrown.c4app.service.GameService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

data class MoveRequest(var colNum: Int?, var mark: String?) {
    constructor() : this(null, null) {}
}


@RestController
@RequestMapping("api/games")
class GameController(val gameService: GameService) {


 @RequestMapping(value = "/", method = [RequestMethod.GET])
    fun listGames(): List<GameState> {
        return gameService.getAllGames().map { it.gameState };
    }

    @RequestMapping(value = "{id}", method = [RequestMethod.GET])
    fun getGameContents(@PathVariable id: Long): GameState {
        return gameService.getGame(id).gameState
    }


    @RequestMapping(value = "/", method = [RequestMethod.POST])
    fun createGame(): ResponseEntity<GameState> {
        println("Creating game")
        return ResponseEntity( gameService.createGame().gameState, HttpStatus.CREATED)
    }

    @RequestMapping(value = "{id}", method = [RequestMethod.DELETE])
    fun deleteGame(@PathVariable id: Long) {
        gameService.removeGame(id)
    }
//
//    @RequestMapping(value = "/{id}/move", method = [RequestMethod.PATCH])
//    fun makeMove(@PathVariable id: Int, @RequestBody moveRequest: MoveRequest): GameResult {
//        println("id=$id colnum=$moveRequest.colNum mark=$moveRequest.mark")
//
//        return gameService.playMove(id, moveRequest.colNum ?: -1, Mark.decode(moveRequest.mark ?: " "))
//    }
//

}