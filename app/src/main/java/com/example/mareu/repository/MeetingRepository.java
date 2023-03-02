package com.example.mareu.repository;

import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A class that acts as a repository for meetings, providing methods to interact with a MeetingApiService.
 */
public class MeetingRepository {

    private final MeetingApiService meetingApiService;

    /**
     * Constructs a MeetingRepository object.
     * @param meetingApiService the MeetingApiService to be used for meeting operations.
     */
    public MeetingRepository(MeetingApiService meetingApiService) {
        this.meetingApiService = meetingApiService;
    }

    /**
     * Returns a list of all meetings.
     * @return the list of meetings.
     */
    public List<Meeting> getMeetings() {
        return meetingApiService.getMeetings();
    }

    /**
     * Adds a new meeting to the repository.
     * @param meeting the meeting to be added.
     */
    public void addMeeting(Meeting meeting) {
        meetingApiService.addMeeting(meeting);
    }

    /**
     * Deletes a meeting from the repository.
     * @param meeting the meeting to be deleted.
     */
    public void deleteMeeting(Meeting meeting) {
        meetingApiService.deleteMeeting(meeting);
    }

    /**
     * Returns an ArrayList of meetings filtered by a specified date.
     * @param date the date to filter by.
     * @return the ArrayList of meetings that match the filter.
     */
    public ArrayList<Meeting> getMeetingFilteredByDate(Date date) {
        return meetingApiService.getMeetingFilteredByDate(date);
    }

    /**
     * Returns a list of meetings filtered by a specified room name.
     * @param participant the name of the room to filter by.
     * @return the list of meetings that match the filter.
     */
    public List<Meeting> getMeetingFilteredBySalle(String participant) {
        return meetingApiService.getMeetingFilteredBySalle(participant);
    }
}

