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
import android.widget.DatePicker;

import com.example.mareu.R;
import com.example.mareu.databinding.ActivityMainBinding;
import com.example.mareu.di.Injection;
import com.example.mareu.model.Reunion;
import com.example.mareu.repository.ReunionRepository;
import com.example.mareu.service.ReunionApiService;

import java.util.ArrayList;
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
                ReunionAdapter.updateList(reunionsFiltrees);
            }
        });
        builder.show();
        */

    }


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
                // pour la clear ?
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
