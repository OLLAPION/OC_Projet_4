package com.example.mareu.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.model.Reunion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ReunionAdapter extends RecyclerView.Adapter<ReunionAdapter.ViewHolder> {

    private final ArrayList<Reunion> reunions;

    public ReunionAdapter(ArrayList<Reunion> reunions) {
        this.reunions = reunions;
    }

    @NonNull
    @Override
    public ReunionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reunion, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Reunion reunion = reunions.get(position);
        viewHolder.displayReunion(reunion);
    }

    @Override
    public int getItemCount() {
        return reunions.size();
    }

    /**
     * ViewHolder dans l'adapter
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // public final TextView letter;
        public final TextView lieu;
        public final TextView heureCommencement;
        public final TextView sujet;
        // public final TextView participants;
        public final TextView date;

        public ViewHolder(@NonNull View view) {
            super(view);
            // letter = view.findViewById(R.id.letter);
            lieu = view.findViewById(R.id.lieu);
            heureCommencement = view.findViewById(R.id.heureCommencement);
            sujet = view.findViewById(R.id.sujet);
            // participants = view.findViewById(R.id.participants);
            date = view.findViewById(R.id.date);
        }

        public void displayReunion(Reunion reunion) {
            SimpleDateFormat fmtOut = new SimpleDateFormat("dd/MM/yyyy");

            // letter.setText(reunion.getReunionParticipants().charAt(0));
            lieu.setText(reunion.getReunionSalle());
            heureCommencement.setText(reunion.getReunionDebut());
            sujet.setText(reunion.getReunionSujet());
            // participants.setText(reunion.setReunionParticipants());
            date.setText(fmtOut.format(reunion.getReunionDate()));
        }
    }
}
