package h.com.submission2kotlin.api

import android.net.Uri
import h.com.submission2kotlin.BuildConfig

object TheSportDBApi{

    fun getPastEvents(pastEvent: String?): String{
        return BuildConfig.BASE_URL +"api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventspastleague.php?id=" + pastEvent
    }

    fun getHomeBadge(idHome: String?) : String {
        return BuildConfig.BASE_URL +"api/v1/json/${BuildConfig.TSDB_API_KEY}"+ "/lookupteam.php?id=" + idHome
    }

    fun getAwayBadge(idAway: String?) : String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php?id="+idAway
    }

    fun getNextEvent(nextEvent: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventsnextleague.php?id="+nextEvent
    }

    fun getDetailEvent(detailEvent: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupevent.php?id="+detailEvent
    }
}