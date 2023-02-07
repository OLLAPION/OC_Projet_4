package com.example.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
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
        // Associe le menu.xml à la main
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
        salleList.add("Salle_01");
        salleList.add("Salle_02");
        salleList.add("Salle_03");
        salleList.add("Salle_04");
        salleList.add("Salle_05");
        salleList.add("Salle_06");
        salleList.add("Salle_07");
        salleList.add("Salle_08");
        salleList.add("Salle_09");
        salleList.add("Salle_10");
        //salleList.show;
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