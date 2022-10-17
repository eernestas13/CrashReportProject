package org.setu.crashreport.console.views

import org.setu.crashreport.console.models.CrashReportJSONStore
import org.setu.crashreport.console.models.CrashReportModel

//
//class MainView : View("Admin Main Menu") {
//    override val root = vbox(20) {
//
//    }
//}
//

    val BLACK = "\u001b[0;30m" // BLACK

    val RED = "\u001b[0;31m" // RED

    val GREEN = "\u001b[0;32m" // GREEN

    val YELLOW = "\u001b[0;33m" // YELLOW

    val BLUE = "\u001b[0;34m" // BLUE

    val PURPLE = "\u001b[0;35m" // PURPLE

    val CYAN = "\u001b[0;36m" // CYAN

    val WHITE = "\u001b[0;37m" // WHITE

    val RESET = "\u001B[0m" // RESet

    const val GREEN_BRIGHT = "\u001b[0;92m" // GREEN


class CrashView {



    fun menu(): Int {

        var option: Int
        var input: String?

       // println("MAIN MENU")
        println("----------------------------")
        println(GREEN + "| 1. Add Crash Report      |" + RESET)
        println(YELLOW + "| 2. Update Crash Report   |" + RESET)
        println("| 3. List All Crash Report |")
        println(BLUE + "| 4. Search Crash Report   |"+ RESET)
        println(RED + "| 5. Delete Crash Report   |"+ RESET)
        println("| 6. Filter |")
        println(BLUE +"| 7. Search Road Report    |"+ RESET)
        println(BLACK + "| -1. Exit |"+ RESET)
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

    fun listCrashReports(crashReports: CrashReportJSONStore) {
        println("List All Crash Reports")
        println()
        crashReports.logAll()
        println()
    }

    fun showCrashReport(crashReport: CrashReportModel) {
        if (crashReport != null)
            println("Crash Report Details [ $crashReport ]")
        else
            println("Crash Report Not Found...")
    }

    fun addCrashReportData(crashReport: CrashReportModel): Boolean {


        println()
        print("Enter a Road : ")
        crashReport.road = readLine()!!
        print("Enter an average Delay (mins) : ")
        try {
            crashReport.delay = readLine()!!.toIntOrNull()!!
        }catch (e:NullPointerException){
            print("Please enter a numeric value for average Delay (mins) : ")
            crashReport.delay = readLine()!!.toIntOrNull()!!
        }
        print("Enter a Title : ")
        crashReport.title = readLine()!!
        print("Enter a Description : ")
        crashReport.description = readLine()!!

        return crashReport.road.isNotEmpty() && crashReport.delay.toString()
            .isNotEmpty() && crashReport.title.isNotEmpty() && crashReport.description.isNotEmpty()
    }

    fun updateCrashReportData(crashReport: CrashReportModel): Boolean {

        val tempRoad: String?
        var tempDelay: Int
        val tempTitle: String?
        val tempDescription: String?

        if (crashReport != null) {
            print("Enter a new Road for [ " + crashReport.road + " ] : ")
            tempRoad = readLine()!!
            print("Enter a new average Delay for (mins) [ " + crashReport.delay + " ] : ")
            try {
                tempDelay = readLine()!!.toIntOrNull()!!
            }catch (e:NullPointerException){
                print("Please enter a numeric value for new average Delay (mins) : ")
                tempDelay = readLine()!!.toIntOrNull()!!
             }
            print("Enter a new Title for [ " + crashReport.title + " ] : ")
            tempTitle = readLine()!!
            print("Enter a new Description for [ " + crashReport.description + " ] : ")
            tempDescription = readLine()!!

            if (!tempRoad.isNullOrEmpty() && !tempDelay.toString().isNullOrEmpty() && !tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
                crashReport.road = tempRoad
                crashReport.delay = tempDelay
                crashReport.title = tempTitle
                crashReport.description = tempDescription
                return true
            }
        }
        return false
    }

    fun getId(): Long {
        var strId: String? // String to hold user input
        var searchId: Long // Long to hold converted id
        print("Enter ID : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }

    fun getRoad(): String {
        var strRoad: String?
        var searchRoad: String

        print("Enter Road (exact) : ")
        strRoad = readLine()!!
        searchRoad = if (strRoad != null && strRoad.isNotEmpty())
            strRoad.toString()
        else
            println("Invalid").toString()
        return searchRoad
    }
}