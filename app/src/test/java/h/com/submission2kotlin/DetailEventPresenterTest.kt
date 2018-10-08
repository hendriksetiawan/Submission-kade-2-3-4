package h.com.submission2kotlin

import com.google.gson.Gson
import h.com.submission2kotlin.api.ApiRepository
import h.com.submission2kotlin.api.TheSportDBApi
import h.com.submission2kotlin.model.Event
import h.com.submission2kotlin.model.Team
import h.com.submission2kotlin.presenter.EventDetailPresenter
import h.com.submission2kotlin.view.EventDetailView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class DetailEventPresenterTest{
    @Mock
    private lateinit var view: EventDetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: EventDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = EventDetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetNextEvent() {
        val events: MutableList<Event> = mutableListOf()
        val home: MutableList<Team> = mutableListOf()
        val away: MutableList<Team> = mutableListOf()
        val response = EventResponse(events)
        val homeResponse = TeamResponse(home)
        val awayResponse = TeamResponse(away)
        val idEvent = "526916"
        val idHomeTeam = "134778"
        val idAwayTeam = "133613"

        `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getDetailEvent(idEvent)),
                EventResponse::class.java)).thenReturn(response)
        `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getHomeBadge(idHomeTeam)),
                TeamResponse::class.java)).thenReturn(homeResponse)
        `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getAwayBadge(idAwayTeam)),
                TeamResponse::class.java)).thenReturn(awayResponse)

        presenter.getEventDetail(idEvent, idHomeTeam, idAwayTeam)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showEventDetail(events, home, away)
        Mockito.verify(view).hideLoading()
    }

}