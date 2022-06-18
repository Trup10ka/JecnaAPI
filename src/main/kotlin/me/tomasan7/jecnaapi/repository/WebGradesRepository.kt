package me.tomasan7.jecnaapi.repository

import io.ktor.http.*
import me.tomasan7.jecnaapi.data.grade.GradesPage
import me.tomasan7.jecnaapi.data.SchoolYear
import me.tomasan7.jecnaapi.parser.parsers.HtmlGradesParser
import me.tomasan7.jecnaapi.util.JecnaPeriodEncoder
import me.tomasan7.jecnaapi.util.JecnaPeriodEncoder.jecnaEncode
import me.tomasan7.jecnaapi.web.JecnaWebClient
import me.tomasan7.jecnaapi.web.append

/**
 * Retrieves [GradesPage] from the Ječná web.
 */
class WebGradesRepository(private val webClient: JecnaWebClient) : GradesRepository
{
    private val gradesParser = HtmlGradesParser()

    override suspend fun queryGradesPage() = gradesParser.parse(webClient.queryStringBody(WEB_PATH))

    override suspend fun queryGradesPage(schoolYear: SchoolYear, firstHalf: Boolean) =
        gradesParser.parse(webClient.queryStringBody(WEB_PATH, Parameters.build {
            append(schoolYear.jecnaEncode())
            append(JecnaPeriodEncoder.encodeSchoolYearHalf(firstHalf))
        }))

    companion object
    {
        private const val WEB_PATH = "/score/student"
    }
}