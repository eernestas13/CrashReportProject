package org.setu.crashreport.console.views

class MainView {

    fun menuMain(): Int {

        //Displays Main Menu Options for User
        var option: Int
        var input: String?

        println("----------------------------")
        println("$GREEN| 1. Open Crash Report Menu |$RESET")
        println("$YELLOW| 2. Open Delay Report Menu |$RESET")
        println("$BLACK|-1. Exit System            |$RESET")
        println("---------------------------")

        println()
        print(GREEN_BRIGHT +"Enter Option : " + RESET)
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }
}