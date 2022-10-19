package org.setu.crashreport.console.controllers;
import org.setu.crashreport.console.models.DelayReportJSONStore
import org.setu.crashreport.console.models.DelayReportModel
import org.setu.crashreport.console.views.DelayView

class DelayReportController {
    private val delayReports = DelayReportJSONStore()
    private val delayView = DelayView()

    init {
        println("Delay Report opened")
    }

    fun startDelay() {
        //Functions calls after User Inputs
        var input: Int
        do {
            input = menu()
            when (input) {
                1 -> addDelay()
                2 -> updateDelay()
                3 -> listDelay()
                4 -> deleteDelay()
                        -1 -> println("Exiting Delays")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        println("Shutting Down Crash Report Console App")
    }

    private fun deleteDelay() {
        println("Enter Delay Report ID to Delete...")
        delayView.listDelayReports(delayReports)
        var searchId = delayView.getId()
        val aDelayReport = searchDelay(searchId)

        if(aDelayReport != null) {
            delayReports.deleteDelay(aDelayReport)
            println("Delay Report Deleted...")
            delayView.listDelayReports(delayReports)
        }
        else
            println("Delay Report Not Deleted...")
    }

    private fun menu() :Int { return delayView.menuDelay() }

    private fun addDelay(){

        var aDelayReport = DelayReportModel()
        if (delayView.addDelayReportData(aDelayReport))
            delayReports.createDelay(aDelayReport)
        else
            println("Crash Report Not Added")
    }

    private fun listDelay() {
        delayView.listDelayReports(delayReports)
    }

    private fun updateDelay() {
        delayView.listDelayReports(delayReports)
        var searchId = delayView.getId()
        val aDelayReport = searchDelay(searchId)

        if(aDelayReport != null) {
            if(delayView.updateDelayReportData(aDelayReport)) {
                delayReports.updateDelay(aDelayReport)
                delayView.showDelayReport(aDelayReport)
                println("Crash Report Updated : [ $aDelayReport ]")
            }
            else
                println("Crash Report Not Updated")
        }
        else
            println("Crash Report Not Updated...")
    }


    private fun searchDelay(id: Long): DelayReportModel? {
        return delayReports.findOneDelay(id)
    }

}
