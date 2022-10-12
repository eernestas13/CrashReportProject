package org.setu.crashreport.console.views

import org.setu.crashreport.console.models.CrashReportJSONStore
import org.setu.crashreport.console.models.CrashReportModel

import tornadofx.*
//
//class CrashView : View("Crash Report") {
//    override val root: HBox = hbox {
//        label(title) {
//            addClass(Styles.heading)
//        }
//    }
//}





class CrashView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("MAIN MENU")
        println(" 1. Add Crash Report")
        println(" 2. Update Crash Report")
        println(" 3. List All Crash Report")
        println(" 4. Search Crash Report")
        println(" 5. Delete Crash Report")
        println("-1. Exit")
        println()
        print("Enter Option : ")
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

    fun showCrashReport(crashReport : CrashReportModel) {
        if(crashReport != null)
            println("Crash Report Details [ $crashReport ]")
        else
            println("Crash Report Not Found...")
    }

    fun addCrashReportData(crashReport : CrashReportModel) : Boolean {



        println()
        print("Enter a Road : ")
        crashReport.road = readLine()!!
        print("Enter an average Delay (mins) : ")
        //NullPointer - Fix, allow user only Int input
        crashReport.delay = readLine()!!.toIntOrNull()!!
        print("Enter a Title : ")
        crashReport.title = readLine()!!
        print("Enter a Description : ")
        crashReport.description = readLine()!!

        return crashReport.road.isNotEmpty() && crashReport.delay.toString().isNotEmpty() && crashReport.title.isNotEmpty() && crashReport.description.isNotEmpty()
    }

    fun updateCrashReportData(crashReport : CrashReportModel) : Boolean {

        val tempRoad: String?
       // val tempDelay: Int?
        val tempTitle: String?
        val tempDescription: String?

        if (crashReport != null) {
            print("Enter a new Road for [ " + crashReport.road + " ] : ")
            tempRoad = readLine()!!
          //  print("Enter an average Delay for [ " + crashReport.delay + " ] : ")
          //  tempDelay = readLine()!!.toIntOrNull()
            print("Enter a new Title for [ " + crashReport.title + " ] : ")
            tempTitle = readLine()!!
            print("Enter a new Description for [ " + crashReport.description + " ] : ")
            tempDescription = readLine()!!

            if (!tempRoad.isNullOrEmpty() && /*!tempDelay.toString().isNullOrEmpty() && */!tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
                crashReport.road = tempRoad
           //     crashReport.delay = tempDelay
                crashReport.title = tempTitle
                crashReport.description = tempDescription
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to Search/Update : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}