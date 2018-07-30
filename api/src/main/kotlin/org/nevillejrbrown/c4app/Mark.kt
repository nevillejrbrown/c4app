package org.nevillejrbrown.c4app

enum class Mark (val label:String) {
    P1("O"),
    P2("X"),
    EMPTY(" ");

    companion object {
        fun decode(thingToDecode:String) :Mark {
            if (thingToDecode.equals("O")) return Mark.P1
            if (thingToDecode.equals("X")) return Mark.P2
            else return Mark.EMPTY
        }
    }

    override fun toString(): String {
        return label
    }



}