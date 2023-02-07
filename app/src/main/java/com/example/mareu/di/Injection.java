package com.example.mareu.di;

import com.example.mareu.repository.ReunionRepository;
import com.example.mareu.service.DummyReunionApiService;
import com.example.mareu.service.ReunionApiService;

/**
 * Injection de dependance pour aller chercher une instance des services
 */
public class Injection {


     /**
     *cherche toujours une nouvelle instance de @{@link ReunionRepository}. Utilisable pour les tests.
     *@return
     */
    public static ReunionRepository createReunionRepository() {
        // ne pas retourner une nouvelle reunion
        return new ReunionRepository(new DummyReunionApiService());
    }

    private static final ReunionApiService service = new DummyReunionApiService();

    /**
     * Get an instance on @{@link ReunionApiService}
     * @return
     */
    public static ReunionApiService getReunionApiService() {
        return service;
    }

    /**
     * Get always a new instance on @{@link ReunionApiService}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static ReunionApiService getNewInstanceApiService() {
        return new DummyReunionApiService();
    }



}