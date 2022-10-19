package org.setu.crashreport.console.models

data class CrashReportModel(var id: Long = 0,
                            var road: String = "",
                            var title: String = "",
                            var delay: DelayReportModel = DelayReportModel(),
                            var description: String = "")
