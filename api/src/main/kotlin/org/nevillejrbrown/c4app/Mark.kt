package org.nevillejrbrown.c4app

import org.omg.CORBA.Object

enum class Mark (val label:String) {
    O("O"),
    X("X"),
    EMPTY(" ");

    companion object {
        fun decode(thingToDecode:String) :Mark {
            if (thingToDecode.equals("O")) return Mark.O
            if (thingToDecode.equals("X")) return Mark.X
            else return Mark.EMPTY
        }
    }

    override fun toString(): String {
        return label
    }




}