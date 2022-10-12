package org.setu.crashreport.console.models

interface CrashReportStore {
    fun findAll(): List<CrashReportModel>
    fun findOne(id: Long): CrashReportModel?
    fun create(crashReport: CrashReportModel)
    fun update(crashReport: CrashReportModel)
    fun delete(crashReport: CrashReportModel)

}