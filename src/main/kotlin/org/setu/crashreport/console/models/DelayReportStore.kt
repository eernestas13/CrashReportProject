package org.setu.crashreport.console.models;

interface DelayReportStore {
    fun findAllDelay(): List<DelayReportModel>
    fun findOneDelay(id: Long): DelayReportModel?
    fun findOneDelayInt(delay: Int): DelayReportModel?
    fun createDelay(delayReport: DelayReportModel)
    fun updateDelay(delayReport: DelayReportModel)
    fun deleteDelay(delayReport: DelayReportModel)

}
