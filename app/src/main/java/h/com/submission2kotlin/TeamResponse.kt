package h.com.submission2kotlin

import com.google.gson.annotations.SerializedName
import h.com.submission2kotlin.model.Team

data class TeamResponse(
        @SerializedName("teams")
        val team: List<Team>
)