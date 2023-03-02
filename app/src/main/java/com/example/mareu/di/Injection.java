package com.example.mareu.di;

import com.example.mareu.repository.MeetingRepository;
import com.example.mareu.service.DummyMeetingApiService;

/**
 * Dependency injection to get an instance from the MeetingRepository
 */
public class Injection {

    private static MeetingRepository service;

    /**
     * Return a singleton instance of @{@link MeetingRepository}
     * used in the app and test instrumented
     * @return an instance of MeetingRepository
     */
    public static MeetingRepository getMeetingRepository() {
        if (service == null) {
            service = new MeetingRepository(new DummyMeetingApiService());
        }
        return service;
    }

    /**
     *Get a new instance on @{@link MeetingRepository}
     * used only in the unit test.
     *@return A new instance of the MeetingRepository
     */
    public static MeetingRepository createMeetingRepository() {
        return new MeetingRepository(new DummyMeetingApiService());
    }
}