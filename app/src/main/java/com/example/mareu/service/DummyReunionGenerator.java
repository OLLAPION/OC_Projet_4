package com.example.mareu.service;

import com.example.mareu.model.Reunion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public abstract class DummyReunionGenerator {

    static List<Reunion> generateReunion() {
        return new ArrayList<>(DUMMY_REUNION);
    }

    public static List<Reunion> DUMMY_REUNION = Arrays.asList(

            new Reunion(0, "Salle_06",  "10h30", "12h00", "Sujet 01", Arrays.asList("participant1@monadresse.fr", "participant2@monadresse.fr", "participant3@monadresse.fr"), new Date(1676557205000L)),
            new Reunion(1, "Salle_02",  "9h30", "10h30", "Sujet 02", Arrays.asList("participant1@monadresse.fr", "participant2@monadresse.fr", "participant3@monadresse.fr"), new Date(1676657205000L)),
            new Reunion(2, "Salle_03",  "14h00", "18h00", "Sujet 03", Arrays.asList("participant1@monadresse.fr", "participant2@monadresse.fr"), new Date(1676757205000L)),
            new Reunion(3, "Salle_06",  "13h30", "15h00", "Sujet 04", Arrays.asList("participant1@monadresse.fr", "participant2@monadresse.fr", "participant3@monadresse.fr", "participant4@monadresse.fr"), new Date(1676657205000L))

    );


}
