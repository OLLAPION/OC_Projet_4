package com.example.mareu;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import androidx.recyclerview.widget.RecyclerView;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import androidx.test.espresso.matcher.ViewMatchers;
import org.hamcrest.TypeSafeMatcher;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;

import static java.util.regex.Pattern.matches;

import com.example.mareu.di.Injection;
import com.example.mareu.model.Meeting;
import com.example.mareu.repository.MeetingRepository;
import com.example.mareu.ui.MainActivity;
import com.example.mareu.ui.MeetingAdapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MeetingRepositoryInstrumentedTest {

    private MeetingRepository meetingRepository;

    @Before
    public void setup() {
        meetingRepository = Injection.getMeetingRepository();
    }

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = getInstrumentation().getTargetContext();
        assertEquals("com.example.mareu", appContext.getPackageName());
    }

    @Test
    public void addMeetingWithSuccess() throws ParseException {
        onView(withId(R.id.add_meeting)).perform(click());
        onView(withText("Choisir une Salle de reunion")).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Salle_01"))).perform(click());
        onView(withId(R.id.meetingDay)).perform(typeText("03/01/2023"), closeSoftKeyboard());
        onView(withId(R.id.meetingStart)).perform(typeText("14:30"), closeSoftKeyboard());
        onView(withId(R.id.meetingEnd)).perform(typeText("15:30"), closeSoftKeyboard());
        onView(withId(R.id.meetingSubject)).perform(typeText("Test"), closeSoftKeyboard());
        onView(withId(R.id.meetingParticipants)).perform(typeText("participant4@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.buttonAddMeeting)).perform(click());

        List<Meeting> meetings = meetingRepository.getMeetings();
        Meeting addedMeeting = meetings.get(meetings.size() - 1);

        assertEquals("Test", addedMeeting.getMeetingSubject());
        assertEquals("Salle_01", addedMeeting.getMeetingRoom());
        assertEquals(Arrays.asList("participant4@gmail.com"), addedMeeting.getMeetingParticipants());
        assertEquals("14:30", addedMeeting.getMeetingStart());
        assertEquals("15:30", addedMeeting.getMeetingEnd());
        String dateString = "03/01/2023";
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = format.parse(dateString);
        assertEquals(date, addedMeeting.getMeetingDate());
    }

    @Test
    public void deleteMeetingWithSuccess() {
        onView(withId(R.id.add_meeting)).perform(click());
        onView(withText("Choisir une Salle de reunion")).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Salle_05"))).perform(click());
        onView(withId(R.id.meetingDay)).perform(typeText("03/01/2023"), closeSoftKeyboard());
        onView(withId(R.id.meetingStart)).perform(typeText("15:00"), closeSoftKeyboard());
        onView(withId(R.id.meetingEnd)).perform(typeText("18:00"), closeSoftKeyboard());
        onView(withId(R.id.meetingSubject)).perform(typeText("Projet_04"), closeSoftKeyboard());
        onView(withId(R.id.meetingParticipants)).perform(typeText("email11@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.buttonAddMeeting)).perform(click());

        List<Meeting> meetingsBeforeDelete = meetingRepository.getMeetings();
        Meeting newMeeting = meetingsBeforeDelete.get(meetingsBeforeDelete.size() - 1);
        meetingRepository.deleteMeeting(newMeeting);
        List<Meeting> meetingsAfterDelete = meetingRepository.getMeetings();

        assertNotEquals(meetingsBeforeDelete.size() -1, meetingsAfterDelete.size());
    }



    @Test
    public void filterReunionByMeetingRoom() {
        String selectedMeetingRoom = "Salle_08";

        Meeting newMeeting = new Meeting(
                selectedMeetingRoom,
                "10:00",
                "11:00",
                "Sujet",
                Arrays.asList("email1@gmail.com", "email2@gmail.com"),
                new Date(2023, 2, 13)
        );
        meetingRepository.addMeeting(newMeeting);

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Filtrer par salle")).perform(click());
        onView(withText("Salle_01")).perform(click());
        onView(withText(selectedMeetingRoom)).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withText("OK")).perform(click());

        List<Meeting> displayedMeetings = meetingRepository.getMeetings();
        Meeting selectedMeeting = displayedMeetings.get(displayedMeetings.indexOf(newMeeting));
        assertThat(selectedMeeting.getMeetingRoom(), is(selectedMeetingRoom));

        for (Meeting meeting : displayedMeetings) {
            if (meeting != selectedMeeting) {
                assertThat(meeting.getMeetingRoom(), not(selectedMeetingRoom));
            }
        }
    }


    @Test
    public void filterMeetingsByDate() {
        Date selectedMeetingDate = new Date(2023, 3, 1);

        Meeting newMeeting = new Meeting(
                "Salle_10",
                "8:00",
                "12:00",
                "Sujet_10",
                Arrays.asList("email5@gmail.com", "email7@gmail.com"),
                selectedMeetingDate
        );
        meetingRepository.addMeeting(newMeeting);

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Filtrer par date")).perform(click());
        onView(withText("OK")).perform(click());

        List<Meeting> displayedMeetings = meetingRepository.getMeetings();
        Meeting selectedMeeting = displayedMeetings.get(displayedMeetings.indexOf(newMeeting));
        assertThat(selectedMeeting.getMeetingDate(), is(selectedMeetingDate));

        for (Meeting meeting : displayedMeetings) {
            if (meeting != selectedMeeting) {
                assertThat(meeting.getMeetingDate(), not(selectedMeetingDate));
            }
        }
    }
}