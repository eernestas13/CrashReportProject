package org.setu.crashreport.console.main

import mu.KotlinLogging
import org.setu.crashreport.console.controllers.CrashReportController
import org.setu.crashreport.console.controllers.DelayReportController
import org.setu.crashreport.console.controllers.MainController
import org.setu.crashreport.console.models.CrashReportJSONStore
import org.setu.crashreport.console.models.DelayReportJSONStore
import org.setu.crashreport.console.models.CrashReportModel
import org.setu.crashreport.console.models.DelayReportModel
import org.setu.crashreport.console.views.CrashView
import org.setu.crashreport.console.views.DelayView


private val logger = KotlinLogging.logger {}

val crashReports = CrashReportJSONStore()
val crashView = CrashView()
val controller = CrashReportController()

val delayReports = DelayReportJSONStore()
val delayView = DelayView()
val delayController = DelayReportController()


fun main(args: Array<String>) {
    MainController().startMain()
}
