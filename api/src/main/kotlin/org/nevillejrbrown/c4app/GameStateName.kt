package org.nevillejrbrown.c4app

data class GameResult(val state: GameStateName, val winner:Mark?)

enum class GameStateName(stateLabel:String ) {
    IN_PLAY("Still playing"),
    WON("Won"),
    DRAWN("Drawn")
}