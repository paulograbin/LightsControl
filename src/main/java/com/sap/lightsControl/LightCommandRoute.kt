package com.sap.lightsControl

import org.eclipse.jetty.http.HttpStatus
import spark.Request
import spark.Response
import spark.Route


class LightCommandRoute(val lightDriver: LightDriver) : Route {

    override fun handle(req: Request?, res: Response?): String {
        println()
        println("Header: " + req?.headers())
        println("Request from " + req?.host())
        println("URL " + req?.url())

        val commandParam = req!!.params(":command")

        if(commandParam!!.length != 3) {
            return sendErrorResponse(res)
        }

        val state = extractLightsState(commandParam.split(""))

        if(!state.isValid())
            return sendErrorResponse(res)

        activateLights(state)

        return sendSucessfulResponse(res)
    }

    private fun activateLights(state: State) {
        lightDriver.applyState(state)
    }

    private fun extractLightsState(lights: List<String>?): State {
        val red = Integer.valueOf(lights?.get(1))
        val yellow = Integer.valueOf(lights?.get(2))
        val green = Integer.valueOf(lights?.get(3))

        return State(red = red, yellow = yellow, green = green)
    }


    private fun sendSucessfulResponse(res: Response?): String {
        res!!.status(HttpStatus.OK_200)
        return "OK \n"
    }

    private fun sendErrorResponse(res: Response?): String {
        res!!.status(HttpStatus.NOT_FOUND_404)
        return "NOK \n"
    }

}

