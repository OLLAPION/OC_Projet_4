package com.example.mareu.di;

import com.example.mareu.repository.ReunionRepository;
import com.example.mareu.service.DummyReunionApiService;
import com.example.mareu.service.ReunionApiService;

/**
 * Dependency injection to get an instance from the ReunionRepository
 */
public class Injection {


     /**
     *Get a new instance on @{@link ReunionRepository}.
     *@return
     */
    public static ReunionRepository createReunionRepository() {
        return new ReunionRepository(new DummyReunionApiService());
    }

    private static ReunionRepository service = new ReunionRepository(new DummyReunionApiService());

    /**
     * Get an instance on @{@link ReunionRepository}
     * @return
     */
    public static ReunionRepository getReunionRepository() {
        return service;
    }

}