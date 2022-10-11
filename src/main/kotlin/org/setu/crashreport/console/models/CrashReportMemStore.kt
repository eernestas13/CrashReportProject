//package org.setu.crashreport.console.models
//
//import mu.KotlinLogging
//
//private val logger = KotlinLogging.logger {}
//var lastId = 0L
//
//internal fun getId(): Long {
//    return lastId++
//}
//
//class CrashReportMemStore : CrashReportStore {
//
//    val crashReport = ArrayList<CrashReportModel>()
//
//    override fun findAll(): List<CrashReportModel> {
//        return crashReport
//    }
//
//    override fun findOne(id: Long) : CrashReportModel? {
//        var foundCrashReport: CrashReportModel? = crashReport.find { p -> p.id == id }
//        return foundCrashReport
//    }
//
//    override fun create(crashReport: CrashReportModel) {
//        crashReport.id = getId()
//        crashReport.add(crashReport)
//        logAll()
//    }
//
//    override fun update(crashReport: CrashReportModel) {
//        var foundCrashReport = findOne(crashReport.id!!)
//        if (foundCrashReport != null) {
//            foundCrashReport.title = crashReport.title
//            foundCrashReport.description = crashReport.description
//        }
//    }
//
//    override fun delete(crashReport: CrashReportModel) {
//        crashReport.remove(crashReport)
//    }
//
//    internal fun logAll() {
//        crashReport.forEach { logger.info("${it}") }
//    }
//}