package h.com.submission2kotlin

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import h.com.submission2kotlin.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testAppBehaviour() {
        Thread.sleep(3000)
        onView(withId(list_past_event)).check(matches(isDisplayed()))
        onView(withId(list_past_event)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(list_past_event)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        Thread.sleep(3000)

        onView(withId(add_to_favorite)).perform(click())

        pressBack()
        Thread.sleep(3000)
        onView(withId(bottom__navigation)).check(matches(isDisplayed()))
        onView(withId(next_match)).perform(click())

        Thread.sleep(3000)
        onView(withId(list_next_match)).check(matches(isDisplayed()))
        onView(withId(list_next_match)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(list_next_match)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        Thread.sleep(3000)

        onView(withId(add_to_favorite)).perform(click())
        pressBack()

        Thread.sleep(3000)
        onView(withId(bottom__navigation)).check(matches(isDisplayed()))
        onView(withId(favorites)).perform(click())
    }
}