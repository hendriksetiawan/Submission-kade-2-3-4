package h.com.submission2kotlin

import com.google.gson.Gson
import h.com.submission2kotlin.api.ApiRepository
import h.com.submission2kotlin.api.TheSportDBApi
import h.com.submission2kotlin.model.Event
import h.com.submission2kotlin.presenter.MainPresenter
import h.com.submission2kotlin.view.MainView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class NextEventPresenterTest{
    @Mock
    private lateinit var view: MainView

    @Mock
    private lateinit var gson: Gson

    @Mock
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MainPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetNextEvent() {
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)
        val leagueId = "4328"

        `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getNextEvent(leagueId)),
                EventResponse::class.java)).thenReturn(response)

        presenter.getNextMatch(leagueId)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showEventList(events)
        Mockito.verify(view).hideLoading()
    }
}