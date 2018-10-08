package h.com.submission2kotlin.view

import h.com.submission2kotlin.model.Event


interface MainView{
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Event>)
}