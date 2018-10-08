package h.com.submission2kotlin.view

import h.com.submission2kotlin.model.Event
import h.com.submission2kotlin.model.Team

interface EventDetailView{
    fun showLoading()
    fun hideLoading()
    fun showEventDetail(data: List<Event>, home: List<Team>, away: List<Team>)
}