package com.sap.lightsControl

import spark.Request
import spark.Response
import spark.Route


class LightCommandRoute(val lightDriver: LightDriver) : Route {

    override fun handle(req: Request?, res: Response?): String {
        println("Header: " + req?.headers())
        println("Request from " + req?.host())
        println("URL " + req?.url())

        var commandParam = req!!.params(":command")

        if(commandParam!!.length != 3) {
            res!!.status(404)
            return "NOK \n"
        }

        var state = extractLightsState(commandParam?.split(""))

        if(state.isValid())
            activateLights(state);

        res!!.status(200)
        return "OK \n";
    }

    private fun activateLights(state: State) {
        lightDriver.applyState(state);
    }

    private fun extractLightsState(lights: List<String>?): State {
        var red = Integer.valueOf(lights?.get(1))
        var yellow = Integer.valueOf(lights?.get(2))
        var green = Integer.valueOf(lights?.get(3))

        return State(red = red, yellow = yellow, green = green)
    }
}

