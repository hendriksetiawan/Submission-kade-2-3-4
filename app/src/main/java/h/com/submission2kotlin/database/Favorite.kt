package h.com.submission2kotlin.database

data class Favorite(val id: Long?, val eventId:String?,
                    val teamAwayScore: String?,
                    val teamAway: String?,
                    val teamHomeScore: String?,
                    val eventDate: String?,
                    val teamHome: String?,
                    val idTeamHome: String?,
                    val idTeamAway: String?){
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val EVENT_DATE: String = "EVENT_DATE"
        const val TEAM_HOME: String = "TEAM_HOME"
        const val TEAM_AWAY: String = "TEAM_AWAY"
        const val TEAM_HOME_SCORE: String = "TEAM_HOME_SCORE"
        const val TEAM_AWAY_SCORE: String = "TEAM_AWAY_SCORE"
        const val TEAM_HOME_ID: String = "TEAM_HOME_ID"
        const val TEAM_AWAY_ID: String = "TEAM_AWAY_ID"
    }
}