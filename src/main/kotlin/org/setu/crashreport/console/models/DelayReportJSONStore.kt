package org.setu.crashreport.console.models
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.setu.crashreport.console.helpers.exists
import org.setu.crashreport.console.helpers.read
import org.setu.crashreport.console.helpers.write

const val JSON_FILE_DELAYS = "delayReport.json"
val gsonBuilderDelays = GsonBuilder().setPrettyPrinting().create()
val listTypeDelays = object : TypeToken<java.util.ArrayList<DelayReportModel>>() {}.type

class DelayReportJSONStore : DelayReportStore {
    var delayReports = mutableListOf<DelayReportModel>()

    init {
        if (exists(JSON_FILE_DELAYS)) {
            deserialize()
        }
    }

    override fun findAllDelay(): MutableList<DelayReportModel> {
        return delayReports
    }
    override fun findOneDelay(id: Long): DelayReportModel? {
        return delayReports.find { p -> p.id == id }
    }

    override fun findOneDelayInt(delay: Int): DelayReportModel? {
        return delayReports.find { p -> p.delay == delay }
    }

    override fun createDelay(delayReport: DelayReportModel) {
        delayReport.id = generateRandomId()
//        if (crashReport.id == crashReport.id) {
//            crashReport.id++
//        }

        // crashReport.id = crashReport.id.plus(1)

        //crashReport.id = crashReport.id++
//        when (crashReport.id) {
//            is Long -> crashReport.id++
//        }
        delayReports.add(delayReport)
        serialize()
    }

    override fun updateDelay(delayReport: DelayReportModel) {
        var foundDelayReport = findOneDelay(delayReport.id!!)
        if (foundDelayReport != null) {
            foundDelayReport.delay = delayReport.delay
            foundDelayReport.road = delayReport.road
            foundDelayReport.title = delayReport.title
        }
        serialize()
    }
    override fun deleteDelay(delayReport: DelayReportModel) {
        delayReports.remove(delayReport)
        serialize()
    }
    internal fun logAll() {
        delayReports.forEach { println("${it}") }
    }
    private fun serialize() {
        val jsonString = gsonBuilderDelays.toJson(delayReports, listTypeDelays)
        write(JSON_FILE_DELAYS, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE_DELAYS)
        delayReports = Gson().fromJson(jsonString, listTypeDelays)
    }

}