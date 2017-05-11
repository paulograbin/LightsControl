package com.sap.lightsControl

import spark.Spark.get
import spark.Spark.port

class Main {

    fun start() {
        port(3000)

        get("/lights/:command", LightCommandRoute(LightDriver()));
    }
}

fun main(args: Array<String>) {
    Main().start()
}
