package com.example.mareu.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mareu.R;
import com.example.mareu.databinding.ItemReunionBinding;
import com.example.mareu.di.Injection;
import com.example.mareu.model.Reunion;
import com.example.mareu.repository.ReunionRepository;
import com.example.mareu.service.ReunionApiService;

import java.text.SimpleDateFormat;
import java.util.List;

public class ReunionAdapter extends RecyclerView.Adapter<ReunionAdapter.ViewHolder> {

    // changer "reunions" pour "mReunions" ...
    private List<Reunion> reunions;

    public ReunionAdapter(List<Reunion> reunionArrayList) {
        this.reunions = reunionArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemReunionBinding binding = ItemReunionBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reunion reunion = reunions.get(position);
        holder.displayReunion(reunion);

        holder.binding.picture.setImageResource(getGoodPhoto(reunion.getReunionSalle()));
        holder.binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteReunion(holder.getBindingAdapterPosition());
            }
        });

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

    public void deleteReunion(int position) {
        reunions.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, reunions.size());
    }

    public void updateList(List<Reunion> reunions) {
        this.reunions.clear();
        this.reunions.addAll(reunions);
        notifyDataSetChanged();
    }

    /**
     * ViewHolder dans l'adapter
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemReunionBinding binding;

        public ViewHolder(ItemReunionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void displayReunion(Reunion reunion) {
            SimpleDateFormat fmtOut = new SimpleDateFormat("dd/MM/yyyy");

            binding.room.setText(reunion.getReunionSalle());
            binding.timeToStart.setText("-" + reunion.getReunionDebut() + "-");
            binding.subject.setText(reunion.getReunionSujet());
            binding.date.setText(fmtOut.format(reunion.getReunionDate()));

            List<String> reunionParticipants = reunion.getReunionParticipants();
            StringBuilder builder = new StringBuilder();
            for (String participant : reunionParticipants) {
                builder.append(participant);
                builder.append(", ");
            }
            String participants = builder.toString().trim();
            if (participants.endsWith(",")) {
                participants = participants.substring(0, participants.length() - 1);
            }
            binding.participants.setText(participants);
        }
    }
}
