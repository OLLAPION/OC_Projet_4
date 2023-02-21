package com.example.mareu;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.*;

import com.example.mareu.model.Reunion;
import com.example.mareu.repository.ReunionRepository;
import com.example.mareu.service.DummyReunionApiService;
import com.example.mareu.ui.MainActivity;

import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
// ReunionRepositoryInstrumentedTest
public class ExampleInstrumentedTest {

    private ReunionRepository reunionRepository;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.mareu", appContext.getPackageName());
    }

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    /*
    @Test
    public void addReunion() {
        onView(withId(R.id.add_reunion)).perform(click());
        onView(withId(R.id.textSujet)).perform(typeText("Test"), closeSoftKeyboard());
        onView(withId(R.id.choixSalle)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Salle_01"))).perform(click());
        onView(withId(R.id.recuperationParticipants)).perform(typeText("participant1@gmail.com, participant2@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.recuperationJour)).perform(typeText("13/02/2023"), closeSoftKeyboard());
        onView(withId(R.id.recuperationHeureDebut)).perform(typeText("14:30"), closeSoftKeyboard());
        onView(withId(R.id.recuperationHeureFin)).perform(typeText("15:30"), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());
        onView(withText("Reunion reservé !")).inRoot(withDecorView(not(is(mActivityTestRule.getScenario().getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

     */

    @Test
    public void deleteReunionWithSuccess() {
        ReunionRepository reunionRepository = new ReunionRepository(new DummyReunionApiService());
        List<Reunion> initialReunions = reunionRepository.getReunion();
        Reunion reunionToDelete = initialReunions.get(0);
        reunionRepository.deleteReunion(reunionToDelete);
        List<Reunion> updatedReunions = reunionRepository.getReunion();
        assertThat(updatedReunions, not(hasItem(reunionToDelete)));
    }
}