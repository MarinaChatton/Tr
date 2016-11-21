package com.chatton.marina.calculator;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void shouldDisplayAdditionResultOnClickEqual(){
        onView(withId(R.id.num5)).perform(click());
        onView(withId(R.id.operator_plus)).perform(click());
        onView(withId(R.id.num4)).perform(click());
        onView(withId(R.id.equal)).perform(click());
        //check displayed result
        onView(withId(R.id.display)).check(matches(withText("9")));
    }

    @Test
    public void shouldDisplaySubstractionResultOnClickPlus(){
        onView(withId(R.id.num1)).perform(click());
        onView(withId(R.id.num5)).perform(click());
        onView(withId(R.id.decimal_separator)).perform(click());
        onView(withId(R.id.num5)).perform(click());
        onView(withId(R.id.operator_min)).perform(click());
        onView(withId(R.id.num5)).perform(click());
        onView(withId(R.id.decimal_separator)).perform(click());
        onView(withId(R.id.num5)).perform(click());
        onView(withId(R.id.operator_plus)).perform(click());
        //check displayed result
        onView(withId(R.id.display)).check(matches(withText("10")));
    }

    @Test
    public void shouldResetDisplay(){
        onView(withId(R.id.num5)).perform(click());
        onView(withId(R.id.clear)).perform(click());
        onView(withId(R.id.display)).check(matches(withText("0")));
    }

    @Test
    public void shouldDisplayError(){
        onView(withId(R.id.num1)).perform(click());
        onView(withId(R.id.operator_div)).perform(click());
        onView(withId(R.id.num0)).perform(click());
        onView(withId(R.id.operator_mult)).perform(click());
        onView(withId(R.id.display)).check(matches(withText("Error")));
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.chatton.marina.calculator", appContext.getPackageName());
    }
}
