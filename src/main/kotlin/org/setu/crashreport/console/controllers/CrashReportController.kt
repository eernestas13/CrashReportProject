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
        logger.info { "Launching Crash Report Console App" }
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
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Crash Report Console App" }
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
        var aCrashReport = CrashReportModel()

        if (crashView.addCrashReportData(aCrashReport))
            crashReports.create(aCrashReport)
        else
            logger.info("Crash Report Not Added")
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
                logger.info("Crash Report Updated : [ $aCrashReport ]")
            }
            else
                logger.info("Crash Report Not Updated")
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

    fun dummyData() {
        crashReports.create(CrashReportModel(title = "New York New York", description = "So Good They Named It Twice"))
        crashReports.create(CrashReportModel(title= "Ring of Kerry", description = "Some place in the Kingdom"))
        crashReports.create(CrashReportModel(title = "Waterford City", description = "You get great Blaas Here!!"))
    }
}