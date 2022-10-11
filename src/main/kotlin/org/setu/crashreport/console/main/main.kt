package org.setu.crashreport.console.main

import mu.KotlinLogging
import org.setu.crashreport.console.controllers.CrashReportController
import org.setu.crashreport.console.models.CrashReportJSONStore
//import org.setu.crashreport.console.models.CrashReportMemStore
import org.setu.crashreport.console.models.CrashReportModel
import org.setu.crashreport.console.views.CrashView

private val logger = KotlinLogging.logger {}

val crashReports = CrashReportJSONStore()
val crashView = CrashView()
val controller = CrashReportController()


fun main(args: Array<String>) {
    CrashReportController().start()
}
