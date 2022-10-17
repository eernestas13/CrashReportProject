package org.setu.crashreport.console.models

data class CrashReportModel(var id: Long = 0,
                            var road: String = "",
                            var title: String = "",
                            var description: String = "")
