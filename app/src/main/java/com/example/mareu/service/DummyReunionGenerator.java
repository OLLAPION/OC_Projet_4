package com.example.mareu.service;

import com.example.mareu.model.Reunion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyReunionGenerator {

    static List<Reunion> generateReunion() {
        return new ArrayList<>(DUMMY_REUNION);
    }

    public static List<Reunion> DUMMY_REUNION = Arrays.asList(
            new Reunion(0, "Salle A", "24/01/2023", "10h30", "12h00", "Sujet 01", Arrays.asList("participant1@monadresse.fr", "participant2@monadresse.fr", "participant3@monadresse.fr"))
            );

}
