package h.com.submission2kotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import h.com.submission2kotlin.R.id.*
import h.com.submission2kotlin.model.Event
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.*
import java.text.SimpleDateFormat
import java.util.*

class MainAdapter(private val event: List<Event>, private val listener:(Event) -> Unit):
        RecyclerView.Adapter<EventViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(EventUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bindItem(event[position], listener)
    }

    override fun getItemCount(): Int  = event.size
}

class EventUI: AnkoComponent<ViewGroup>{
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            linearLayout{
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.VERTICAL


                textView{
                    id = tv_date
                    textSize = 16f
                    text = "12-12-1018"
                } .lparams{
                    margin = dip(15)
                    gravity = Gravity.CENTER
                }

                linearLayout{
                    lparams(width = matchParent, height = wrapContent)
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER

                    textView{
                        id = tv_team_home
                        textSize = 16f
                        text = "Arsenal"
                    } .lparams{
                        margin = dip(8)
                    }
                    textView{
                        id = tv_home_score
                        textSize = 20f
                        text = "0"
                    } .lparams{
                        margin = dip(8)
                    }
                    textView{
                        textSize = 20f
                        text = "VS"
                    } .lparams{
                        margin = dip(8)
                    }
                    textView{
                        id = tv_away_score
                        textSize = 20f
                        text = "0"
                    } .lparams{
                        margin = dip(8)
                    }
                    textView{
                        id = tv_team_away
                        textSize = 16f
                        text = "Chelsea"
                    } .lparams{
                        margin = dip(8)
                    }
                }
            }
        }
    }
}

class EventViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer{
    private val dateEvent: TextView  = containerView.find(tv_date)
    private val teamHome: TextView = containerView.find(tv_team_home)
    private val homeScore: TextView = containerView.find(tv_home_score)
    private val teamAway: TextView = containerView.find(tv_team_away)
    private val awayScore: TextView = containerView.find(tv_away_score)


    fun bindItem(event: Event, listener: (Event) -> Unit){

        val timeEvent = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .parse(event.eventDate)
        val dateEvents = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
                .format(timeEvent)

        dateEvent.text = dateEvents
        teamHome.text = event.teamHome
        homeScore.text = event.teamHomeScore
        teamAway.text = event.teamAway
        awayScore.text = event.temAwayScore

        containerView.setOnClickListener { listener(event) }
    }
}