package com.example.mareu.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.databinding.ActivityAddReunionBinding;
import com.example.mareu.databinding.ActivityMainBinding;
import com.example.mareu.databinding.ItemReunionBinding;
import com.example.mareu.di.Injection;
import com.example.mareu.events.DeleteReunionEvent;
import com.example.mareu.model.Reunion;
import com.example.mareu.repository.ReunionRepository;
import com.example.mareu.service.ReunionApiService;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReunionAdapter extends RecyclerView.Adapter<ReunionAdapter.ViewHolder> {

    private List<Reunion> reunions;

    public ReunionAdapter(List<Reunion> reunionArrayList) {
        reunions = reunionArrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reunion, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Reunion reunion = reunions.get(position);
        holder.displayReunion(reunion);

        holder.mImageView.setImageResource(getGoodPhoto(reunion.getReunionSalle()));

        // Suppression d'une reunion
        /*
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        */
    }

    private int getGoodPhoto(String salleReunion) {
        switch (salleReunion) {
            case "Salle_01" :
                return R.drawable.green2_circle;
            case "Salle_02" :
                return R.drawable.circle;
            case "Salle_03" :
                return R.drawable.pink_circle;
            case "Salle_04" :
                return R.drawable.orange_circle;
            case "Salle_05" :
                return R.drawable.cyan_circle;
            case "Salle_06" :
                return R.drawable.green_circle;
            case "Salle_07" :
                return R.drawable.yellow_circle;
            case "Salle_08" :
                return R.drawable.blue_circle;
            case "Salle_09" :
                return R.drawable.red_circle;
            case "Salle_10" :
                return R.drawable.black_circle;
            default:
                return R.drawable.grey_circle;
        }
    }

    @Override
    public int getItemCount() {
        return reunions.size();
    }

    /**
     * ViewHolder dans l'adapter
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView lieu;
        public final TextView heureCommencement;
        public final TextView sujet;
        public final TextView participants;
        public final TextView date;
        public final ImageView deleteButton;
        ImageView mImageView;

        public ViewHolder(View view) {
            super(view);
            lieu = view.findViewById(R.id.lieu);
            heureCommencement = view.findViewById(R.id.heureCommencement);
            sujet = view.findViewById(R.id.sujet);
            participants = view.findViewById(R.id.participant);
            date = view.findViewById(R.id.date);
            deleteButton = itemView.findViewById(R.id.button);
            mImageView = itemView.findViewById(R.id.photo);
        }

        public void displayReunion(Reunion reunion) {
            SimpleDateFormat fmtOut = new SimpleDateFormat("dd/MM/yyyy");

            lieu.setText(reunion.getReunionSalle());
            heureCommencement.setText("-" + reunion.getReunionDebut() + "-");
            sujet.setText(reunion.getReunionSujet());
            //participants.setText(reunion.getReunionParticipants().get(0) + " ; " + reunion.getReunionParticipants().get(1));
            date.setText(fmtOut.format(reunion.getReunionDate()));
        }
    }

}
