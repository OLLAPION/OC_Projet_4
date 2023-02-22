package com.example.mareu.di;

import com.example.mareu.repository.ReunionRepository;
import com.example.mareu.service.DummyReunionApiService;
import com.example.mareu.service.ReunionApiService;

/**
 * Dependency injection to get an instance from the ReunionRepository
 */
public class Injection {

    private static ReunionRepository service;

    /**
     * Get an instance of @{@link ReunionRepository}
     *
     * @return
     */
    public static ReunionRepository getReunionRepository() {
        if (service == null) {
            service = new ReunionRepository(new DummyReunionApiService());
        }
        return service;
    }


    /**
     *Get a new instance on @{@link ReunionRepository} used only to test.
     *@return
     */
    public static ReunionRepository createReunionRepository() {
        return new ReunionRepository(new DummyReunionApiService());
    }
}