package com.example.mareu.model;

import java.util.Date;
import java.util.List;

/**
 * model item meeting
 */
public class Meeting {

    /** meeting Identifier */
    private Integer id;

    /** meeting room */
    private String meetingRoom;

    /** meeting start time */
    private String meetingStart;

    /** meeting end time */
    private String meetingEnd;

    /** meeting subject */
    private String meetingSubject;

    /** meeting participant */
    private List<String> meetingParticipants;

    /** meeting day */
    private Date mMeetingDate;


    /**
     * Constructor second : to add a new meeting
     * @param meetingRoom
     * @param meetingStart
     * @param meetingEnd
     * @param meetingSubject
     * @param meetingParticipants
     * @param date
     */
    public Meeting(String meetingRoom, String meetingStart, String meetingEnd, String meetingSubject, List<String> meetingParticipants, Date date) {
        this.meetingRoom = meetingRoom;
        this.meetingStart = meetingStart;
        this.meetingEnd = meetingEnd;
        this.meetingSubject = meetingSubject;
        this.meetingParticipants = meetingParticipants;
        this.mMeetingDate = date;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(String meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public Date getMeetingDate() {
        return mMeetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
        mMeetingDate = meetingDate;
    }

    public String getMeetingStart() {
        return meetingStart;
    }

    public void setMeetingStart(String meetingStart) {
        this.meetingStart = meetingStart;
    }

    public String getMeetingEnd() {
        return meetingEnd;
    }

    public void setMeetingEnd(String meetingEnd) {
        this.meetingEnd = meetingEnd;
    }

    public String getMeetingSubject() {
        return meetingSubject;
    }

    public void setMeetingSubject(String meetingSubject) {
        this.meetingSubject = meetingSubject;
    }

    public List<String> getMeetingParticipants() {
        return meetingParticipants;
    }

    public void setMeetingParticipants(List<String> meetingParticipants) {
        this.meetingParticipants = meetingParticipants;
    }


    /**
     * Constructor
     * @param id
     * @param meetingRoom
     * @param meetingStart
     * @param meetingEnd
     * @param meetingSubject
     * @param meetingParticipants
     * @param date
     */
    public Meeting(Integer id, String meetingRoom, String meetingStart, String meetingEnd, String meetingSubject, List<String> meetingParticipants, Date date) {
        this.id = id;
        this.meetingRoom = meetingRoom;
        this.meetingStart = meetingStart;
        this.meetingEnd = meetingEnd;
        this.meetingSubject = meetingSubject;
        this.meetingParticipants = meetingParticipants;
        this.mMeetingDate = date;
    }
}