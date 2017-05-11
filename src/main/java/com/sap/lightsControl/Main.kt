package com.sap.lightsControl

import spark.Spark.get
import spark.Spark.port

class Main {

    fun start(driverPath: String) {
        port(3000)

        val driver = LightDriver(driverPath)

        get("/lights/:command", LightCommandRoute(lightDriver = driver));
    }
}

fun main(args: Array<String>) {
    if (args.size != 1)
        error("Invalid parameters...");

    Main().start(args[0])
}
