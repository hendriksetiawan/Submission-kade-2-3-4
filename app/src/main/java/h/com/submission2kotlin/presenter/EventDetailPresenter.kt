package h.com.submission2kotlin.presenter

import com.google.gson.Gson
import h.com.submission2kotlin.CoroutineContextProvider
import h.com.submission2kotlin.view.EventDetailView
import h.com.submission2kotlin.EventResponse
import h.com.submission2kotlin.TeamResponse
import h.com.submission2kotlin.api.ApiRepository
import h.com.submission2kotlin.api.TheSportDBApi
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class EventDetailPresenter(private val view: EventDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getEventDetail(idEvent: String?, idHomeTeam: String?, idAwayTeam: String?) {
        view.showLoading()
        async(context.main) {
            val eventDetail = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getDetailEvent(idEvent)),
                        EventResponse::class.java)
            }
            val badgeHome = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getHomeBadge(idHomeTeam)),
                        TeamResponse::class.java)
            }
            val badgeAway = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getAwayBadge(idAwayTeam)),
                        TeamResponse::class.java)
            }
            view.showEventDetail(eventDetail.await().events, badgeHome.await().team,
                    badgeAway.await().team)
            view.hideLoading()
        }
    }
}