package com.sap.lightsControl

class State(private var yellow: Int, private var red: Int, private var green: Int) {

    fun getRed(): String {
        return red.toString()
    }

    fun getYellow(): String {
        return yellow.toString()
    }

    fun getGreen(): String {
        return green.toString()
    }

    fun isValid(): Boolean {
        if(red != 0 && red != 1)
            return false

        if(yellow != 0 && yellow != 1)
            return false

        if(green != 0 && green != 1)
            return false

        return true
    }
}