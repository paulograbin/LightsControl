package com.sap.lightsControl

import java.io.BufferedReader

class LightDriver(driverPath: String) {

    val driverPath = driverPath
    val driverFileName = "clewarecontrol"
    val driverFlag = "-as"

    val redLightPosition = "0";
    val yellowLightPosition = "1";
    val greenLightPosition = "2";


    fun applyState(state: State) {
        if(!state.isValid())
            RuntimeException("Invalid state")

        executeCommand(state);
    }

    fun executeCommand(state: State) {
        val builder = ProcessBuilder(driverPath + driverFileName,
                "-d", "902435",
                driverFlag, redLightPosition, state.getRed(),
                driverFlag, yellowLightPosition, state.getYellow(),
                driverFlag, greenLightPosition, state.getGreen());

        println(builder.command())


        val pr = builder.start();

        val allText = pr.inputStream.bufferedReader().use { BufferedReader::readLine }

        System.out.println(allText)
    }
}