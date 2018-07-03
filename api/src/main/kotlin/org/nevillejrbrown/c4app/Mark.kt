package org.nevillejrbrown.c4app

enum class Mark (val label:String) {
    P1("o"),
    P2("*"),
    EMPTY(" ");

    companion object {
        fun decode(thingToDecode:String) :Mark {
            if (thingToDecode.equals("o")) return Mark.P1
            if (thingToDecode.equals("*")) return Mark.P1
            else return Mark.EMPTY
        }
    }

    override fun toString(): String {
        return label
    }



}