package com.example.mareu.repository;

import com.example.mareu.model.Reunion;
import com.example.mareu.service.ReunionApiService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReunionRepository {


    private final ReunionApiService reunionApiService;

    public ReunionRepository(ReunionApiService reunionApiService) {
        this.reunionApiService = reunionApiService;
    }


    public List<Reunion> getReunion() {
        return reunionApiService.getReunion();
    }

    public void addReunion(Reunion reunion) {
        reunionApiService.addReunion(reunion);
    }

    public void deleteReunion(Reunion reunion) {
        reunionApiService.deleteReunion(reunion);
    }

    public ArrayList<Reunion> getReunionsFilteredByDate(Date date) {
        return reunionApiService.getReunionsFilteredByDate(date);
    }

    public List<Reunion> getReunionsFilteredBySalle(String participant) {
        return reunionApiService.getReunionsFilteredBySalle(participant);
    }
}
