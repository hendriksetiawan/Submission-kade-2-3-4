package h.com.submission2kotlin.Team

import android.util.Log
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

object TeamBadgeApi{
    fun getTeamBadge(teamUrl: String): String{
        val tbUrl: URL
        var resultString = ""

        try{
            tbUrl = URL(teamUrl)

            val connTeamBadge = tbUrl.openConnection() as HttpURLConnection
            connTeamBadge.requestMethod = "GET"
            connTeamBadge.doInput = true
            connTeamBadge.doOutput = true

            val response = connTeamBadge.responseCode
            Log.i("responseCode: ", response.toString() + "")

            resultString = if (response == HttpsURLConnection.HTTP_OK){
                val textResponse = connTeamBadge.inputStream.bufferedReader().use(BufferedReader::readText)
                textResponse
            } else {
                ""
            }

        } catch (e: Exception){
            resultString = ""
            e.printStackTrace()
        }
        return resultString
    }
}