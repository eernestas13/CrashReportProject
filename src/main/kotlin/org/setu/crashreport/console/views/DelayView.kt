package org.setu.crashreport.console.views
import org.setu.crashreport.console.models.DelayReportJSONStore
import org.setu.crashreport.console.models.DelayReportModel



class DelayView {

    fun menuDelay(): Int {
        //Displays Delay Menu Options to User
        var option: Int
        var input: String?

        println("----------------------------")
        println("$GREEN| 1. Add Delay Report      |$RESET")
        println("$YELLOW| 2. Update Delay Report   |$RESET")
        println("| 3. List All Delay Report |")
        println("$RED| 4. Delete Delay Report   |$RESET")
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

    //Lists All the Stored Delay Reports
    fun listDelayReports(delayReports: DelayReportJSONStore) {
        println("List All Delay Reports")
        println()
        delayReports.logAll()
        println()
    }
    //Lists All the Stored Delay Reports
    fun showDelayReport(delayReport: DelayReportModel) {
        if (delayReport != null)
            println("Crash Report Details [ $delayReport ]")
        else
            println("Crash Report Not Found...")
    }

    //Adds a Delay Report through User Inputs
    fun addDelayReportData(delayReport: DelayReportModel): Boolean {
        println()
       //'enter id to add delay'
        print("Enter an average Delay (mins) : ")
        try {
            delayReport.delay = readLine()!!.toIntOrNull()!!
        }catch (e:NullPointerException){
            print("Please enter a numeric value for average Delay (mins) : ")
            delayReport.delay = readLine()!!.toIntOrNull()!!
        }
        print("Enter a Road : ")
        delayReport.road = readLine()!!
        print("Enter a Title : ")
        delayReport.title = readLine()!!


        return delayReport.delay.toString().isNotEmpty() && delayReport.title.isNotEmpty() && delayReport.road.isNotEmpty()
    }

    //Updates a Delay Report through User Inputs
    fun updateDelayReportData(delayReport: DelayReportModel): Boolean {

        var tempDelay: Int
        val tempTitle: String?
        val tempRoad: String?


        if (delayReport != null) {
            //'enter id to add delay'
            print("Enter a new average Delay for (mins) [ " + delayReport.delay + " ] : ")
            tempDelay = try {
                readLine()!!.toIntOrNull()!!
            }catch (e:NullPointerException){
                print("Please enter a numeric value for new average Delay (mins) : ")
                readLine()!!.toIntOrNull()!!
            }
            print("Enter a new Title for [ " + delayReport.title + " ] : ")
            tempTitle = readLine()!!
            print("Enter a new Road for [ " + delayReport.road + " ] : ")
            tempRoad = readLine()!!

            if (!tempDelay.toString().isNullOrEmpty() && !tempTitle.isNullOrEmpty() && !tempRoad.isNullOrEmpty() ){
                delayReport.delay = tempDelay
                delayReport.title = tempTitle
                delayReport.road = tempRoad
                return true
            }
        }
        return false
    }


    //Function to Get ID of a Delay Report
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
}