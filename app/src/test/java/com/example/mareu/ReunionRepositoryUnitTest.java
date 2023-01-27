package com.example.mareu;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.*;
import static com.example.mareu.service.DummyReunionGenerator.DUMMY_REUNION;

import com.example.mareu.di.Injection;
import com.example.mareu.model.Reunion;
import com.example.mareu.repository.ReunionRepository;

import java.util.List;

/**
 * Tests de ReunionRepository.
 */
@RunWith(JUnit4.class)
public class ReunionRepositoryUnitTest {

    private ReunionRepository reunionRepository;

    @Before
    public void setup() {
        reunionRepository = Injection.createReunionRepository();
    }

    @Test
    public void getReunionWithSuccess() {
        List<Reunion> usersActual = reunionRepository.getReunion();
        List<Reunion> usersExpected = DUMMY_REUNION;
        assertThat(usersActual, containsInAnyOrder(usersExpected.toArray()));
    }

    @Test
    public void addReunionWithSuccess() {
        Reunion reunionToAdd = reunionRepository.getReunion().get(0);
        reunionRepository.addReunion(reunionToAdd);
        assertTrue(reunionRepository.getReunion().contains(reunionToAdd));
    }

    @Test
    public void deleteReunionWithSuccess() {
        Reunion reunionToDelete = reunionRepository.getReunion().get(0);
        reunionRepository.deleteReunion(reunionToDelete);
        assertFalse(reunionRepository.getReunion().contains(reunionToDelete));
    }

}