package com.example.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mareu.databinding.ActivityMainBinding;
import com.example.mareu.di.Injection;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.ReunionApiService;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityMainBinding binding;
    private ArrayList<Reunion> mReunionArrayList = new ArrayList<>();
    private ReunionApiService mReunionApiService = Injection.getReunionApiService();


    private void initUI() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initRecyclerView();
        setButton();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);
        ReunionAdapter mAdapter = new ReunionAdapter(mReunionArrayList);
        binding.recyclerview.setAdapter(mAdapter);
    }

    private void initData() {
        new ArrayList<>(mReunionApiService.getReunion());
        //mReunionArrayList new ArrayList<>(mReunionApiService.getReunion());
    }

    // je ne comprends pas pourquoi mettre ça s'il faut rajouter un OnClick
    private void setButton() {
        binding.addReunion.setOnClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initUI();
    }


    @Override
    public void onClick(View view) {
        if (view == binding.addReunion) {
            startActivity(new Intent(this, AddReunionActivity.class));
        }
    }

}