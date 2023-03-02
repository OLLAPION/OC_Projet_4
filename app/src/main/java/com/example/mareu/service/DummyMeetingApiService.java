package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/** Fake Api */
public class DummyMeetingApiService implements MeetingApiService{

    /** The list of meetings used by this service. */
    private List<Meeting> meeting = DummyMeetingGenerator.generateMeeting();

    /** {@inheritDoc} */
    @Override
    public List<Meeting> getMeetings() {
        return meeting;
    }

    /** {@inheritDoc} */
    @Override
    public void addMeeting(Meeting meeting) {
        this.meeting.add(meeting);
    }

    /** {@inheritDoc} */
    @Override
    public void deleteMeeting(Meeting meeting) {
        this.meeting.remove(meeting);
    }

    /** {@inheritDoc} */
    @Override
    public ArrayList<Meeting> getMeetingFilteredByDate(Date date) {
        ArrayList<Meeting> result = new ArrayList<>();

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date);
        for (int i = 0; i < meeting.size(); i++) {
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(meeting.get(i).getMeetingDate());
            boolean sameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                    cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
            if (sameDay) result.add(meeting.get(i));

        }
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public List<Meeting> getMeetingFilteredBySalle(String salle) {
        List<Meeting> filteredMeeting = new ArrayList<>();
        for (Meeting meeting : meeting) {
            if (meeting.getMeetingRoom().equals(salle)) {
                filteredMeeting.add(meeting);
            }
        }
        return filteredMeeting;
    }
}