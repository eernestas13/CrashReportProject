package org.setu.crashreport.console.views

import org.setu.crashreport.console.main.crashView
import org.setu.crashreport.console.main.delayReports
import org.setu.crashreport.console.models.CrashReportJSONStore
import org.setu.crashreport.console.models.CrashReportModel
import org.setu.crashreport.console.models.DelayReportJSONStore
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String

const val BLACK = "\u001b[0;30m" // BLACK

    const val RED = "\u001b[0;31m" // RED

    const val GREEN = "\u001b[0;32m" // GREEN

    const val YELLOW = "\u001b[0;33m" // YELLOW

    const val BLUE = "\u001b[0;34m" // BLUE

    const val RESET = "\u001B[0m" // RESet

    const val GREEN_BRIGHT = "\u001b[0;92m" // GREEN

    val listDelayReports = DelayReportJSONStore()



class CrashView {



    fun menu(): Int {

        //Displays Crash Menu Options to User
        var option: Int
        var input: String?

        println("----------------------------")
        println("$GREEN| 1. Add Crash Report      |$RESET")
        println("$YELLOW| 2. Update Crash Report   |$RESET")
        println("| 3. List All Crash Report |")
        println("$BLUE| 4. Search Crash Report   |$RESET")
        println("$RED| 5. Delete Crash Report   |$RESET")
        println("| 6. Filter Crash Reports  |")
        println("$BLACK| -1. Back to Main Menu    |$RESET")
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

    //Lists All the Stored Crash Reports
    fun listCrashReports(crashReports: CrashReportJSONStore) {
        println("List All Delay Reports")
        println()
        crashReports.logAll()
        println()
    }
    //Lists All the Stored Delay Reports
    private fun listDelayReports(delayReports: DelayReportJSONStore) {
        println("List All Delay Reports")
        println()
        delayReports.logAll()
        println()
    }

    //Lists All the Stored Crash Reports
    fun showCrashReport(crashReport: CrashReportModel) {
        if (crashReport != null)
            println("Crash Report Details [ $crashReport ]")
        else
            println("Crash Report Not Found...")
    }

    //Adds a Crash Report through User Inputs
    fun addCrashReportData(crashReport: CrashReportModel): Boolean {
        println()
        println("Enter a Road : ")
        crashReport.road = readLine()!!
        println("Enter a Title : ")
        crashReport.title = readLine()!!
        println("Enter a Description : ")
        crashReport.description = readLine()!!
        println("Would you like to add a Delay Report?")
        println("")
        println("Enter 1 for Yes")
        println("Enter 2 for No")

        var userinput = readLine()!!.toInt()
        if (userinput == 1) {
            //list all delay reports
            crashView.listDelayReports(delayReports)
            crashReport.delay = delayReports.findOneDelay(getId())!!
        }
        else if (userinput == 2) {
            return crashReport.road.isNotEmpty() && crashReport.title.isNotEmpty() && crashReport.description.isNotEmpty()
        }
        return crashReport.road.isNotEmpty() && crashReport.title.isNotEmpty() && crashReport.description.isNotEmpty() && crashReport.delay.toString()
            .isNotEmpty()
    }

    //Updates a Crash Report through User Inputs
    fun updateCrashReportData(crashReport: CrashReportModel): Boolean {

        val tempRoad: String?
        val tempTitle: String?
        val tempDescription: String?

        if (crashReport != null) {
            print("Enter a new Road for [ " + crashReport.road + " ] : ")
            tempRoad = readLine()!!
            print("Enter a new Title for [ " + crashReport.title + " ] : ")
            tempTitle = readLine()!!
            print("Enter a new Description for [ " + crashReport.description + " ] : ")
            tempDescription = readLine()!!

            if (!tempRoad.isNullOrEmpty()  && !tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
                crashReport.road = tempRoad
                crashReport.title = tempTitle
                crashReport.description = tempDescription
                return true
            }
        }
        return false
    }

    //Function to Get ID of a Crash Report
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

    //Function to Get Road of a Crash Report
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


