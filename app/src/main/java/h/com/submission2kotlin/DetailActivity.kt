package h.com.submission2kotlin

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteConstraintException
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.gson.Gson
import h.com.submission2kotlin.R.drawable.ic_add_to_favorite
import h.com.submission2kotlin.R.drawable.ic_added_to_favorite
import h.com.submission2kotlin.R.id.add_to_favorite
import h.com.submission2kotlin.R.menu.detail_menu
import h.com.submission2kotlin.Team.TeamBadgeApi
import h.com.submission2kotlin.api.ApiRepository
import h.com.submission2kotlin.database.Favorite
import h.com.submission2kotlin.database.database
import h.com.submission2kotlin.model.Event
import h.com.submission2kotlin.model.Team
import h.com.submission2kotlin.presenter.EventDetailPresenter
import h.com.submission2kotlin.view.EventDetailView
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity(), EventDetailView {
    private lateinit var presenter: EventDetailPresenter
    private lateinit var idEvent: String
    private var idHome: String? = null
    private var idAway: String? = null

    private lateinit var events: Event

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //show back button and set custom title on action bar
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = "Match Detail"
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowHomeEnabled(true)
        }



        //get value from MainActivity
        val intent = intent

        idEvent = intent.getStringExtra("idEvent")
        idHome = intent.getStringExtra("idHome")
        idAway = intent.getStringExtra("idAway")

        favoriteState()
        val request = ApiRepository()
        val gson = Gson()
        presenter = EventDetailPresenter(this, request, gson)
        presenter.getEventDetail(idEvent, idHome, idAway)
    }

    //show back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }



    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showEventDetail(data: List<Event>, home: List<Team>, away: List<Team>) {
        events = Event(data[0].eventId,
                data[0].eventDate,
                data[0].teamHome,
                data[0].teamAway,
                data[0].teamHomeScore,
                data[0].temAwayScore,
                data[0].idHomeTown,
                data[0].idAwayTown)

        val timeEvent = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .parse(data[0].eventDate)
        val dateEvent = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
                .format(timeEvent)

        tvDate.text = dateEvent
        tvHomeTeam.text = data[0].teamHome
        tvHomeScore.text = data[0].teamHomeScore
        tvhomeGoals.text = data[0].homeGoalsDetails
        tvHomeShots.text = data[0].homeShots
        tvHomeRedCards.text = data[0].homeRedCards
        tvHomeYellowCards.text = data[0].homeYellowCards
        tvHomeGoalKeeper.text = data[0].homeGoalKeeper
        tvHomeDefense.text = data[0].homeDefense
        tvHomeMidfield.text = data[0].homeMidField
        tvHomeForward.text = data[0].homeForward
        tvHomeSubtitutes.text = data[0].homeSubtitutes

        tvAwayTeam.text = data[0].teamAway
        tvAwayScore.text = data[0].temAwayScore
        tvAwayGoals.text = data[0].awayGoalDetails
        tvAwayShots.text = data[0].awayShots
        tvAwayRedCards.text = data[0].awayRedCards
        tvAwayYellowCards.text = data[0].awayYellowCards
        tvAwayGoalKeeper.text = data[0].awayGoalKeeper
        tvAwayDefense.text = data[0].awayDefense
        tvAwayMidfield.text = data[0].awayMidField
        tvAwayForward.text = data[0].awayForward
        tvAwaySubtitutes.text = data[0].awaySubtitutes

        Glide.with(this).load(home[0].teamBadge).into(imgTeamHome)
        Glide.with(this).load(away[0].teamBadge).into(imgTeamAway)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.EVENT_ID to events.eventId,
                        Favorite.EVENT_DATE to events.eventDate,
                        Favorite.TEAM_HOME to events.teamHome,
                        Favorite.TEAM_AWAY to events.teamAway,
                        Favorite.TEAM_HOME_SCORE to events.teamHomeScore,
                        Favorite.TEAM_AWAY_SCORE to events.temAwayScore,
                        Favorite.TEAM_HOME_ID to "${idHome}",
                        Favorite.TEAM_AWAY_ID to "${idAway}")
            }
            toast("Added to Favorite").show()
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(EVENT_ID = {idEvent})", "idEvent" to idEvent)
            }
            toast("Removed to favorite")
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorite)
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(EVENT_ID = {id})",
                            "id" to idEvent)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

}