package com.example.mareu.service;

import com.example.mareu.model.Reunion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    @Override
    public ArrayList<Reunion> getReunionsFilteredByDate(Date date) {
        ArrayList<Reunion> result = new ArrayList<>();

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date);
        for (int i = 0; i < reunion.size(); i++) {
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(reunion.get(i).getReunionDate());
            boolean sameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                    cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
            if (sameDay) result.add(reunion.get(i));

        }
        return result;
    }

    @Override
    public List<Reunion> getReunionsFilteredBySalle(String salle) {
        List<Reunion> filteredReunions = new ArrayList<>();
        for (Reunion reunion : reunion) {
            if (reunion.getReunionSalle().equals(salle)) {
                filteredReunions.add(reunion);
            }
        }
        return filteredReunions;
    }


}