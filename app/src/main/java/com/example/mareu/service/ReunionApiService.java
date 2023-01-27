package com.example.mareu.service;

import com.example.mareu.model.Reunion;

import java.util.List;

/**
 * API client de la reunion
 */
public interface ReunionApiService {

    /**
     * Obtenir toutes les reunions
     * @return {@link List}
     */
    List<Reunion> getReunion();

    /**
     * Cree une reunion
     * @param reunion
     */
    void addReunion(Reunion reunion);

    /**
     * supprime une reunion
     * @param reunion
     */
    void deleteReunion(Reunion reunion);
}
