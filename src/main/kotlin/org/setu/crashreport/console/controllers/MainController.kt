package org.setu.crashreport.console.controllers;
import org.setu.crashreport.console.views.DelayView
import org.setu.crashreport.console.views.MainView

public class MainController {
    private val mainView = MainView()


    fun startMain() {
        //Functions calls after User Inputs
        var input: Int
        do {
            input = menu()
            when (input) {
                1 -> CrashReportController().start()
                2 -> DelayReportController().startDelay()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        println("Shutting Down Crash Report Console App")
    }

    private fun menu() :Int { return mainView.menuMain() }

}
