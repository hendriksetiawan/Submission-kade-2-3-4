package h.com.submission2kotlin.model

import com.google.gson.annotations.SerializedName

data class Event(
        @SerializedName("idEvent")
        var eventId: String? = null,

        @SerializedName("intHomeScore")
        var teamHomeScore: String?=null,

        @SerializedName("intAwayScore")
        var temAwayScore: String?=null,

        @SerializedName("strHomeTeam")
        var teamHome: String?=null,

        @SerializedName("strAwayTeam")
        var teamAway: String?=null,

        @SerializedName("dateEvent")
        var eventDate: String?=null,

        @SerializedName("strHomeGoalDetails")
        var homeGoalsDetails: String? = null,

        @SerializedName("strHomeRedCards")
        var homeRedCards: String? = null,

        @SerializedName("strHomeYellowCards")
        var homeYellowCards: String? = null,

        @SerializedName("strHomeLineupGoalkeeper")
        var homeGoalKeeper: String? = null,

        @SerializedName("strHomeLineupDefense")
        var homeDefense: String? = null,

        @SerializedName("strHomeLineupMidfield")
        var homeMidField: String? = null,

        @SerializedName("strHomeLineupForward")
        var homeForward: String? = null,

        @SerializedName("strHomeLineupSubstitutes")
        var homeSubtitutes: String? = null,

        @SerializedName("strAwayRedCards")
        var awayRedCards: String? = null,

        @SerializedName("strAwayYellowCards")
        var awayYellowCards: String? = null,

        @SerializedName("strAwayGoalDetails")
        var awayGoalDetails: String? = null,

        @SerializedName("strAwayLineupGoalkeeper")
        var awayGoalKeeper: String? = null,

        @SerializedName("strAwayLineupDefense")
        var awayDefense: String? = null,

        @SerializedName("strAwayLineupMidfield")
        var awayMidField: String? = null,

        @SerializedName("strAwayLineupForward")
        var awayForward: String? = null,

        @SerializedName("strAwayLineupSubstitutes")
        var awaySubtitutes: String? = null,

        @SerializedName("intHomeShots")
        var homeShots: String? = null,

        @SerializedName("intAwayShots")
        var awayShots: String? = null,

        @SerializedName("idHomeTeam")
        var idHomeTown: String? = null,

        @SerializedName("idAwayTeam")
        var idAwayTown: String? = null,

        @SerializedName("strTime")
        var eventTime: String? = null
)