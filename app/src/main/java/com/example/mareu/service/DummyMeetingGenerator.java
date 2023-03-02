package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public abstract class DummyMeetingGenerator {

    static List<Meeting> generateMeeting() {
        return new ArrayList<>(DUMMY_MEETING);
    }

    public static List<Meeting> DUMMY_MEETING = Arrays.asList(

            new Meeting(0, "Salle_06",  "10h30", "12h00", "Sujet 01", Arrays.asList("participant1@monadresse.fr", "participant2@monadresse.fr", "participant3@monadresse.fr"), new Date(1677757205000L)),
            new Meeting(1, "Salle_02",  "9h30", "10h30", "Sujet 02", Arrays.asList("participant1@monadresse.fr", "participant2@monadresse.fr", "participant3@monadresse.fr"), new Date(1677857205000L)),
            new Meeting(2, "Salle_03",  "14h00", "18h00", "Sujet 03", Arrays.asList("participant1@monadresse.fr", "participant2@monadresse.fr"), new Date(1677957205000L)),
            new Meeting(3, "Salle_06",  "13h30", "15h00", "Sujet 04", Arrays.asList("participant1@monadresse.fr", "participant2@monadresse.fr", "participant3@monadresse.fr", "participant4@monadresse.fr"), new Date(1677857205000L))
    );
}
