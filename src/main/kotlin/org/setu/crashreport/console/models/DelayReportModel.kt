package org.setu.crashreport.console.models

data class DelayReportModel(var id: Long = 0,
                            var delay: Int = 0,
                            var road: String = "",
                            var title: String = "")