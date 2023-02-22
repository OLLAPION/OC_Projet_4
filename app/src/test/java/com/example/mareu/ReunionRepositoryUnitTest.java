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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Filter;

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

    @Test
    public void filterByDate() throws ParseException {
        // La date de la reunion1 = Date(1677757205000L))
        Reunion reunion1 = reunionRepository.getReunion().get(0);

        List<Reunion> filteredReunions = reunionRepository.getReunionsFilteredByDate(new Date(1677757205000L));

        assertEquals(1, filteredReunions.size());
        assertEquals(reunion1, filteredReunions.get(0));
    }

    @Test
    public void filterByRoom() throws ParseException {
        // La salle de la reunion1 = Salle_06
        Reunion reunion1 = reunionRepository.getReunion().get(0);

        List<Reunion> filteredReunions = reunionRepository.getReunionsFilteredBySalle("Salle_06");

        // Il y a 2 reunions qui se feront dans la Salle_06
        assertEquals(2, filteredReunions.size());
        assertEquals(reunion1, filteredReunions.get(0));
    }

}