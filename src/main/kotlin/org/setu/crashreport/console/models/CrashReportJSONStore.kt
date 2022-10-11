package org.setu.crashreport.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging
import org.setu.crashreport.console.helpers.exists
import org.setu.crashreport.console.helpers.read
import org.setu.crashreport.console.helpers.write

import org.setu.crashreport.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "crashReport.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<CrashReportModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class CrashReportJSONStore : CrashReportStore {

    var crashReports = mutableListOf<CrashReportModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<CrashReportModel> {
        return crashReports
    }

    override fun findOne(id: Long) : CrashReportModel? {
        var foundCrashReport: CrashReportModel? = crashReports.find { p -> p.id == id }
        return foundCrashReport
    }

    override fun create(crashReport: CrashReportModel) {
        crashReport.id = generateRandomId()
        crashReports.add(crashReport)
        serialize()
    }

    override fun update(crashReport: CrashReportModel) {
        var foundCrashReport = findOne(crashReport.id!!)
        if (foundCrashReport != null) {
            foundCrashReport.title = crashReport.title
            foundCrashReport.description = crashReport.description
        }
        serialize()
    }

    override fun delete(crashReport: CrashReportModel) {
        crashReports.remove(crashReport)
        serialize()
    }

    internal fun logAll() {
        crashReports.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(crashReports, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        crashReports = Gson().fromJson(jsonString, listType)
    }
}