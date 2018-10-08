package h.com.submission2kotlin.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.google.gson.Gson
import h.com.submission2kotlin.*
import h.com.submission2kotlin.adapter.MainAdapter

import h.com.submission2kotlin.api.ApiRepository
import h.com.submission2kotlin.model.Event
import h.com.submission2kotlin.presenter.MainPresenter
import h.com.submission2kotlin.view.MainView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout


/**
 * A simple [Fragment] subclass.
 *
 */
class NextMatchFragment : Fragment() , AnkoComponent<Context>, MainView {
    private var event: MutableList<Event> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = MainAdapter(event) {
            ctx.startActivity<DetailActivity>("idHome" to "${it.idHomeTown}", "idAway" to "${it.idAwayTown}", "idEvent" to "${it.eventId}")
        }
        listEvent.adapter = adapter
        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)
        presenter.getNextMatch("4328")
        swipeRefresh.onRefresh {
            presenter.getNextMatch("4328")
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listEvent = recyclerView {
                        id = R.id.list_next_match
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showEventList(data: List<Event>) {
        swipeRefresh.isRefreshing = false
        event.clear()
        event.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
