package h.com.submission2kotlin.presenter

import com.google.gson.Gson
import h.com.submission2kotlin.CoroutineContextProvider
import h.com.submission2kotlin.EventResponse
import h.com.submission2kotlin.view.MainView
import h.com.submission2kotlin.api.ApiRepository
import h.com.submission2kotlin.api.TheSportDBApi
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson,
                    private val context: CoroutineContextProvider = CoroutineContextProvider()){
    fun getPastMatch(pastMatch: String?){
        view.showLoading()

        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getPastEvents(pastMatch)),
                        EventResponse::class.java
                )
            }
            view.showEventList(data.await().events)
            view.hideLoading()
        }
    }
    fun getPastMatchs(pastMatch: String?){
        view.showLoading()

        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getPastEvents(pastMatch)),
                        EventResponse::class.java
                )
            }
            view.showEventList(data.await().events)
            view.hideLoading()
        }
    }

    fun getNextMatch(NextMatch: String?){
        view.showLoading()
        async(context.main){
            val dataNextMatch = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getNextEvent(NextMatch)),
                        EventResponse::class.java
                )
            }
            view.showEventList(dataNextMatch.await().events)
            view.hideLoading()
        }
    }
}