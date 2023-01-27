package com.example.mareu.service;

import com.example.mareu.model.Reunion;

import java.util.List;

/**
 * Imitation d un Api
 */

public class DummyReunionApiService implements ReunionApiService{

    private List<Reunion> reunion = DummyReunionGenerator.generateReunion();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Reunion> getReunion() {
        return reunion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addReunion(Reunion reunion) {
        this.reunion.add(reunion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteReunion(Reunion reunion) {
        this.reunion.remove(reunion);
    }

}
