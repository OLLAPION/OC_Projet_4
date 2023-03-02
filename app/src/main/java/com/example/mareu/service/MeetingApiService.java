package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * API client of meeting
 */
public interface MeetingApiService {

    /**
     * Get all meeting
     * @return {@link List}
     */
    List<Meeting> getMeetings();

    /**
     * add meeting
     * @param meeting
     */
    void addMeeting(Meeting meeting);

    /**
     * delete meeting
     * @param meeting
     */
    void deleteMeeting(Meeting meeting);

    /**
     * get meeting to filter by date
     * @param date
     */
    ArrayList<Meeting> getMeetingFilteredByDate(Date date);

    /**
     * get meeting to filter by room
     * @param participant
     */
    List<Meeting> getMeetingFilteredBySalle(String participant);
}
