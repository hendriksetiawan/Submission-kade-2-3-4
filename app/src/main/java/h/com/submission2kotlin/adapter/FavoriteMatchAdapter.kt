package h.com.submission2kotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import h.com.submission2kotlin.R
import h.com.submission2kotlin.database.Favorite
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.text.SimpleDateFormat
import java.util.*

class FavoriteMatchAdapter(private val favorite:List<Favorite>,
                           private val listener:(Favorite) -> Unit)
    : RecyclerView.Adapter<FavoriteViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(EventUIs().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    override fun getItemCount(): Int = favorite.size
}

class EventUIs: AnkoComponent<ViewGroup>{
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            linearLayout{
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.VERTICAL

                textView{
                    id = R.id.tv_date
                    textSize = 16f
                } .lparams{
                    margin = dip(15)
                    gravity = Gravity.CENTER
                }

                linearLayout{
                    lparams(width = matchParent, height = wrapContent)
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER

                    textView{
                        id = R.id.tv_team_home
                        textSize = 16f
                    } .lparams{
                        margin = dip(8)
                    }
                    textView{
                        id = R.id.tv_home_score
                        textSize = 20f
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
                        id = R.id.tv_away_score
                        textSize = 20f
                    } .lparams{
                        margin = dip(8)
                    }
                    textView{
                        id = R.id.tv_team_away
                        textSize = 16f
                    } .lparams{
                        margin = dip(8)
                    }
                }
            }
        }
    }
}
class FavoriteViewHolder(view: View): RecyclerView.ViewHolder(view){
    private val dateEvent: TextView = view.find(R.id.tv_date)
    private val teamHome: TextView = view.find(R.id.tv_team_home)
    private val homeScore: TextView = view.find(R.id.tv_home_score)
    private val teamAway: TextView = view.find(R.id.tv_team_away)
    private val awayScore: TextView = view.find(R.id.tv_away_score)

    fun bindItem(favorite: Favorite, listener: (Favorite) -> Unit){

        val timeEvent = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .parse(favorite.eventDate)
        val dateEvents = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
                .format(timeEvent)

        dateEvent.text = dateEvents
        teamHome.text = favorite.teamHome
        homeScore.text = favorite.teamHomeScore
        teamAway.text = favorite.teamAway
        awayScore.text = favorite.teamAwayScore

        itemView.onClick { listener(favorite) }
    }
}