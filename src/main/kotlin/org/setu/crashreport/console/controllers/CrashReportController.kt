package org.setu.crashreport.console.controllers

import mu.KotlinLogging
import org.setu.crashreport.console.models.CrashReportJSONStore
import org.setu.crashreport.console.models.CrashReportModel
import org.setu.crashreport.console.views.CrashView

class CrashReportController {

    val crashReports = CrashReportJSONStore()
    val crashView = CrashView()
    val logger = KotlinLogging.logger {}

    init {
        println("Crash Report Kotlin App Version 3.0")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                6 -> filter()
                7 -> searchroad()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        println("Shutting Down Crash Report Console App")
    }

    fun filter() {
        println("Enter to filter")
        crashView.listCrashReports(crashReports)
        var searchRoad = crashView.getRoad()
        val aCrashReport = searchroad(searchRoad)

    }

    fun delete() {
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

    fun menu() :Int { return crashView.menu() }

    fun add(){
        println("here")

        var aCrashReport = CrashReportModel()

        if (crashView.addCrashReportData(aCrashReport))
            crashReports.create(aCrashReport)
        else
            println("Crash Report Not Added")
    }

    fun list() {
        crashView.listCrashReports(crashReports)
    }

    fun update() {

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

    fun search() {
        val aCrashReport = search(crashView.getId())!!
        crashView.showCrashReport(aCrashReport)
    }


    fun search(id: Long) : CrashReportModel? {
        var foundCrashReport = crashReports.findOne(id)
        return foundCrashReport
    }

    fun searchroad() {
        crashView.listCrashReports(crashReports)
        val aCrashReport = searchroad(crashView.getRoad())!!
        crashView.showCrashReport(aCrashReport)
    }


    fun searchroad(road: String) : CrashReportModel? {
        var foundCrashReport = crashReports.findOneRoad(road)
        return foundCrashReport
    }

}