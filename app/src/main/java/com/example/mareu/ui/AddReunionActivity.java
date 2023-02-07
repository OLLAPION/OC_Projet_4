package com.example.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mareu.databinding.ActivityAddReunionBinding;
import com.example.mareu.di.Injection;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.ReunionApiService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddReunionActivity extends AppCompatActivity implements  View.OnClickListener {

    ActivityAddReunionBinding binding;
    

    private ReunionApiService mReunionApiService = Injection.getReunionApiService();

    private void initUI() {
        binding = ActivityAddReunionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setButton();
        getSupportActionBar().setTitle("Nouvelle Reunion");
    }

    private void setButton() {
        binding.button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.button) {
            try {
                onSubmit();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }



    private void onSubmit() throws ParseException {
        SimpleDateFormat fmtOut = new SimpleDateFormat("dd/MM/yyyy");
        List<String> listParticipants = new ArrayList<>();
        
        String lieu = binding.recuperationLieu.getText().toString();
        String jour = binding.recuperationJour.getText().toString();
        String heureDebut = binding.recuperationHeureDebut.getText().toString();
        String heureFin = binding.recuperationHeureFin.getText().toString();
        String sujet = binding.recuperationSujet.getText().toString();
        String date = binding.recuperationJour.getText().toString();
        Date dateAuFormat = fmtOut.parse(date);
        String participant = binding.recuperationParticipants.getText().toString();
        listParticipants.add(participant);
        // actuellement l'appli crash après avoir ajouté la reunion
        // dans reunion :
        /*
        public List<String> getReunionParticipants(String participant) {
            return reunionParticipants;
         */

        // layout : texteinputlayout
        if (lieu.isEmpty()) {
            binding.recuperationLieu.setError("Choisissez une Salle");
            return;
        }
        if (jour.isEmpty()) {
            binding.recuperationJour.setError("Choisissez un jour");
            return;
        }
        if (heureDebut.isEmpty()) {
            binding.recuperationHeureDebut.setError("Choisissez une heure de commencement");
            return;
        }
        if (heureFin.isEmpty()) {
            binding.recuperationHeureFin.setError("Choisissez une heure de fin");
            return;
        }
        if (sujet.isEmpty()) {
            binding.recuperationSujet.setError("Choisissez un sujet");
            return;
        }

        if (date.isEmpty()) {
            binding.recuperationJour.setError("Choisissez un jour");
            return;
        }

        if (participant.isEmpty()) {
            binding.recuperationParticipants.setError("Ajoutez au moins deux participants");
            return;
        }
        

        mReunionApiService.addReunion(new Reunion(lieu, heureDebut, heureFin, sujet, listParticipants, dateAuFormat));
        Toast.makeText(this, "Reunion reservé !", Toast.LENGTH_SHORT).show();
        finish();

    }
}