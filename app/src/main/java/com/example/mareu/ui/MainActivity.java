package com.example.mareu;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mareu.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // obligatoire pour le view binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // test du view binding, afficher dans un toast un texte ecrie par l'utilisateur
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getTextWhrite =binding.editText.getText().toString();
                Toast.makeText(MainActivity.this,getTextWhrite,Toast.LENGTH_LONG).show();
            }
        });
    }
}