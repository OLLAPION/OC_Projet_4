package com.example.mareu.di;

import com.example.mareu.repository.ReunionRepository;
import com.example.mareu.service.DummyReunionApiService;

public class Injection {

    public static ReunionRepository createReunionRepository() {
        return new ReunionRepository(new DummyReunionApiService());
    }

}
