package com.sap.lightsControl

import java.lang.ProcessBuilder.Redirect.INHERIT


class LightDriver {

    val sudoCommand = "sudo"
    val driverCommand = "clewarecontrol"
    val driverFlag = "-as"

    val redLightPosition = "0"
    val yellowLightPosition = "1"
    val greenLightPosition = "2"


    fun applyState(state: State) {
        if(!state.isValid())
            RuntimeException("Invalid state")

        executeCommand(state)
    }

    fun executeCommand(state: State) {

        val builder = ProcessBuilder(sudoCommand, driverCommand,
                "-d", "902435",
                driverFlag, redLightPosition, state.getRed(),
                driverFlag, yellowLightPosition, state.getYellow(),
                driverFlag, greenLightPosition, state.getGreen())
                .redirectOutput(INHERIT)
                .redirectError(INHERIT)

        builder.start()

        println(builder.command())
    }
}