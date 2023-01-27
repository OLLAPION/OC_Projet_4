package com.example.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mareu.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private void initUI() {
        // obligatoire pour le view binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        afficheToast();
    }

    private void afficheToast() {
        // test du view binding, afficher dans un toast un texte ecrie par l'utilisateur
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getTextWhrite =binding.editText.getText().toString();
                Toast.makeText(MainActivity.this,getTextWhrite,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

}