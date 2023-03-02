package com.example.mareu;

import static com.example.mareu.service.DummyMeetingGenerator.DUMMY_MEETING;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.*;

import com.example.mareu.di.Injection;
import com.example.mareu.model.Meeting;
import com.example.mareu.repository.MeetingRepository;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Tests of MeetingRepository.
 */
@RunWith(JUnit4.class)
public class MeetingRepositoryUnitTest {

    private MeetingRepository meetingRepository;

    @Before
    public void setup() {
        meetingRepository = Injection.createMeetingRepository();
    }

    @Test
    public void getMeetingWithSuccess() {
        List<Meeting> usersActual = meetingRepository.getMeetings();
        List<Meeting> usersExpected = DUMMY_MEETING;
        assertThat(usersActual, containsInAnyOrder(usersExpected.toArray()));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        List<Meeting> initialMeetings = meetingRepository.getMeetings();
        Meeting meetingToDelete = initialMeetings.get(0);
        meetingRepository.deleteMeeting(meetingToDelete);
        List<Meeting> updatedMeetings = meetingRepository.getMeetings();
        assertThat(updatedMeetings, not(hasItem(meetingToDelete)));
    }

    @Test
    public void addMeetingWithSuccess() {
        Meeting newMeeting = new Meeting(
                "Salle_01",
                "10:00",
                "11:00",
                "ProjetX",
                Arrays.asList("participant1@gmail.com", "participant2@gmail.com"),
                new Date(2023, 2, 13)
        );
        meetingRepository.addMeeting(newMeeting);
        List<Meeting> updatedMeetings = meetingRepository.getMeetings();
        assertThat(updatedMeetings, hasItem(newMeeting));
    }

    @Test
    public void filterByDate() throws ParseException {
        // The date of meeting = Date(1677757205000L))
        Meeting meeting = meetingRepository.getMeetings().get(0);

        List<Meeting> filteredMeetings = meetingRepository.getMeetingFilteredByDate(new Date(1677757205000L));

        assertEquals(1, filteredMeetings.size());
        assertEquals(meeting, filteredMeetings.get(0));
    }

    @Test
    public void filterByRoom() throws ParseException {
        // The room in reunion1 = Salle_06
        Meeting meeting = meetingRepository.getMeetings().get(0);

        List<Meeting> filteredMeeting = meetingRepository.getMeetingFilteredBySalle("Salle_06");

        // Have 2 meetings in the "Salle_06"
        assertEquals(2, filteredMeeting.size());
        assertEquals(meeting, filteredMeeting.get(0));
    }

}