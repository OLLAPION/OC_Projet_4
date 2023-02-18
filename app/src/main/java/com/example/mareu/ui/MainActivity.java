package com.example.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.example.mareu.R;
import com.example.mareu.databinding.ActivityMainBinding;
import com.example.mareu.di.Injection;
import com.example.mareu.model.Reunion;
import com.example.mareu.repository.ReunionRepository;
import com.example.mareu.service.ReunionApiService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityMainBinding binding;
    private List<Reunion> mReunionArrayList = new ArrayList<>();
    private final ReunionApiService mReunionApiService = Injection.getReunionApiService();
    private List<String> salleList;


    private void initUI() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setButton();
        initRecyclerView();
    }

    private void initRecyclerView() {


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);

        ReunionAdapter mAdapter = new ReunionAdapter(mReunionArrayList);
        binding.recyclerview.setAdapter(mAdapter);
        /*
        // Set CustomAdapter as the adapter for RecyclerView.
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.recyclerview.getContext(),
                layoutManager.getOrientation());
        binding.recyclerview.addItemDecoration(dividerItemDecoration);
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter_date:
                dateDialog();
                return true;
            case R.id.filter_salle:
                salleDialog();
                return true;
            case R.id.filter_reset:
                resetFilter();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void resetFilter() {
        mReunionArrayList.clear();
        mReunionArrayList.addAll(mReunionApiService.getReunion());
        binding.recyclerview.getAdapter().notifyDataSetChanged();
    }

    private void salleDialog() {

            // Créer un Spinner pour la sélection de la salle
            Spinner spinner = new Spinner(this);

            // Créer une liste de salles
            List<String> salles = Arrays.asList("Salle_01", "Salle_02", "Salle_03", "Salle_04", "Salle_05", "Salle_06", "Salle_07", "Salle_08", "Salle_09", "Salle_10");

            // Créer un adaptateur pour le Spinner
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, salles);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            // Ajouter un écouteur d'événements sur le Spinner
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // Récupérer la salle sélectionnée
                    String salleSelectionnee = (String) parent.getItemAtPosition(position);

                    // Filtrer la liste des réunions par salle
                    List<Reunion> reunionsFiltrees = mReunionApiService.getReunionsFilteredBySalle(salleSelectionnee);

                    // Mettre à jour l'adaptateur de RecyclerView avec la liste filtrée
                    ReunionAdapter mAdapter = (ReunionAdapter) binding.recyclerview.getAdapter();
                    mAdapter.updateList(reunionsFiltrees);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Ne rien faire si aucune salle n'est sélectionnée
                }
            });

            // Créer un AlertDialog contenant le Spinner
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Filtrer par salle");
            builder.setView(spinner);
            builder.setPositiveButton("OK", null);
            builder.setNegativeButton("Annuler", null);
            builder.show();
        }


        /*
        // Récupérer la liste des salles disponibles
        // j'arrive pas à recuperer la salle de ma fausse bdd
        List<String> salles = Injection.getNewInstanceApiService().getReunionSalle();

        // Créer un tableau de chaînes à partir de la liste des salles
        String[] sallesArray = salles.toArray(new String[0]);

        // Créer la boîte de dialogue
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sélectionner une salle");
        builder.setItems(sallesArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Récupérer la salle sélectionnée
                String salleSelectionnee = salles.get(which);

                // Appliquer le filtre par salle
                List<Reunion> reunionsFiltrees = Injection.getReunionApiService().getReunionsFilteredBySalle(salleSelectionnee);
                // recuperer la liste avec l'update effectué
                ReunionAdapter adapter = new ReunionAdapter(new ArrayList<>());
                adapter.updateList(reunionsFiltrees);
            }
        });
        builder.show();
        */


    private void dateDialog() {
        int selectedYear = 2023;
        int selectedMonth = 1;
        int selectedDayOfMonth = 15;

// Date Select Listener.
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                Calendar cal = Calendar.getInstance();
                cal.set(year, month, dayOfMonth);
                mReunionArrayList.clear();
                mReunionArrayList.addAll(mReunionApiService.getReunionsFilteredByDate(cal.getTime()));
                binding.recyclerview.getAdapter().notifyDataSetChanged();
            }
        };


// Create DatePickerDialog (Spinner Mode):
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);

// Show
        datePickerDialog.show();
    }

    private void initData() {
        mReunionArrayList = new ArrayList<>(mReunionApiService.getReunion());
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        resetFilter();
    }

    private void setButton() {
        binding.addReunion.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.addReunion) {
            startActivity(new Intent(this, AddReunionActivity.class));
        }
    }

}
