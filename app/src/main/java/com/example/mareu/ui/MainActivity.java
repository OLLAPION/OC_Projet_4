package com.example.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityMainBinding binding;
    private List<Reunion> mReunionArrayList = new ArrayList<>();
    private final ReunionRepository mReunionRepository = Injection.getReunionRepository();


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
                roomDialog();
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
        mReunionArrayList.addAll(mReunionRepository.getReunion());
        binding.recyclerview.getAdapter().notifyDataSetChanged();
    }

    private void roomDialog() {

            // Je fais un Spinner pour la sélection de la salle
            Spinner spinner = new Spinner(this);

            // Je fais une liste de salles
            List<String> salles = Arrays.asList("Salle_01", "Salle_02", "Salle_03", "Salle_04", "Salle_05", "Salle_06", "Salle_07", "Salle_08", "Salle_09", "Salle_10");

            // Je fais un adaptateur pour le Spinner
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, salles);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            // J'ajoute un écouteur d'événements sur le Spinner
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // Je recupere la salle sélectionnée
                    String salleSelectionnee = (String) parent.getItemAtPosition(position);

                    // Je filtre la liste des réunions par salle
                    List<Reunion> reunionsFiltrees = mReunionRepository.getReunionsFilteredBySalle(salleSelectionnee);


                    // Je met à jour l'adaptateur de RecyclerView avec la liste filtrée
                    ReunionAdapter mAdapter = (ReunionAdapter) binding.recyclerview.getAdapter();
                    mAdapter.updateList(reunionsFiltrees);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Si aucune salle selectionné ne rien faire
                }
            });

            // Je fais un AlertDialog contenant le Spinner
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Filtrer par salle");
            builder.setView(spinner);
            builder.setPositiveButton("OK", null);
            builder.setNegativeButton("Annuler", null);
            builder.show();
        }


    private void dateDialog() {
        int selectedYear = 2023;
        int selectedMonth = 2;
        int selectedDayOfMonth = 1;

// Date Select Listener.
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                Calendar cal = Calendar.getInstance();
                cal.set(year, month, dayOfMonth);
                mReunionArrayList.clear();
                mReunionArrayList.addAll(mReunionRepository.getReunionsFilteredByDate(cal.getTime()));
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
        mReunionArrayList = new ArrayList<>(mReunionRepository.getReunion());
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
