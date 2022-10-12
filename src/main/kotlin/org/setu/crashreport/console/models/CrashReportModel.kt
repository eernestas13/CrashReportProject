package org.setu.crashreport.console.models

data class CrashReportModel(var id: Long = 0,
                            var road: String = "",
                            var delay: Int = 0,
                            var title: String = "",
                            var description: String = "")
