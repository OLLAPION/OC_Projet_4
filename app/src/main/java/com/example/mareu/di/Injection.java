package com.example.mareu.di;

import com.example.mareu.repository.ReunionRepository;
import com.example.mareu.service.DummyReunionApiService;
import com.example.mareu.service.ReunionApiService;

/**
 * Injection de dependance pour aller chercher une instance des services
 */
public class Injection {

    /**
     * cherche toujours une nouvelle instance de @{@link ReunionRepository}. Utilisable pour les tests.
     * @return
     */
    public static ReunionRepository createReunionRepository() {
        return new ReunionRepository(new DummyReunionApiService());
    }

    private static final ReunionApiService service = new DummyReunionApiService();

    /**
     * chercher une instance de  @{@link ReunionApiService}
     * @return
     */
    public static ReunionApiService getReunionApiService() {
        return service;
    }


}