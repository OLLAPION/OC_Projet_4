package com.example.mareu.service;

import com.example.mareu.model.Reunion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * API client de la reunion
 */
public interface ReunionApiService {

    /**
     * Get all meeting
     * @return {@link List}
     */
    List<Reunion> getReunion();

    /**
     * add meeting
     * @param reunion
     */
    void addReunion(Reunion reunion);

    /**
     * delete meeting
     * @param reunion
     */
    void deleteReunion(Reunion reunion);

    /**
     * get meeting to filter by date
     * @param date
     */
    ArrayList<Reunion> getReunionsFilteredByDate(Date date);

    /**
     * get meeting to filter by room
     * @param participant
     */
    List<Reunion> getReunionsFilteredBySalle(String participant);
}
