package org.setu.crashreport.console.controllers

import mu.KotlinLogging
import org.setu.crashreport.console.models.CrashReportJSONStore
import org.setu.crashreport.console.models.CrashReportModel
import org.setu.crashreport.console.views.CrashView
import org.setu.crashreport.console.views.RESET
import org.setu.crashreport.console.views.YELLOW

class CrashReportController {

    private val crashReports = CrashReportJSONStore()
    private val crashView = CrashView()

    init {
        println("Crash Report Menu")
    }

    fun start() {
        //Functions calls after User Inputs
        var input: Int
        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                6 -> searchroad()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        println("Shutting Down Crash Report Console App")
    }



    private fun delete() {
        println("Enter Crash Report ID to Delete...")
        crashView.listCrashReports(crashReports)
        var searchId = crashView.getId()
        val aCrashReport = search(searchId)

        if(aCrashReport != null) {
            crashReports.delete(aCrashReport)
            println("Crash Report Deleted...")
            crashView.listCrashReports(crashReports)
        }
        else
            println("Crash Report Not Deleted...")
    }

    private fun menu() :Int { return crashView.menu() }

    private fun add(){
        var aCrashReport = CrashReportModel()

        if (crashView.addCrashReportData(aCrashReport))
            crashReports.create(aCrashReport)
        else
            println("Crash Report Not Added")
    }

    private fun list() {
        crashView.listCrashReports(crashReports)
    }

    private fun update() {

        crashView.listCrashReports(crashReports)
        var searchId = crashView.getId()
        val aCrashReport = search(searchId)

        if(aCrashReport != null) {
            if(crashView.updateCrashReportData(aCrashReport)) {
                crashReports.update(aCrashReport)
                crashView.showCrashReport(aCrashReport)
                println("Crash Report Updated : [ $aCrashReport ]")
            }
            else
                println("Crash Report Not Updated")
        }
        else
            println("Crash Report Not Updated...")
    }


    private fun search() {
        crashView.listCrashReports(crashReports)
        val aCrashReport = search(crashView.getId())!!
        crashView.showCrashReport(aCrashReport)
    }


    private fun search(id: Long): CrashReportModel? {
        return crashReports.findOne(id)
    }

    private fun searchroad() {
        crashView.listCrashReports(crashReports)
        println("Enter Road to Filter by : ")
        val userInput = readLine()!!
        val foundRoad = crashReports.findAll().filter { it.road == userInput }
        println("$YELLOW Roads Found : $RESET")
        for (found in foundRoad){
            println(found)
        }
       }


}