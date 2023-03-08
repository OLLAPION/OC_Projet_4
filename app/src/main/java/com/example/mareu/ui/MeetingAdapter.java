package com.example.mareu.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mareu.R;
import com.example.mareu.databinding.ItemMeetingBinding;
import com.example.mareu.model.Meeting;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Adapter for displaying a list of meetings in a RecyclerView.
 */
public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.ViewHolder> {

    // **************************************************
    // Fields
    // **************************************************
    private List<Meeting> mMeetings;
    /**
     * Constructs a MeetingAdapter with the given list of meetings.
     * @param meetingArrayList The list of meetings to display.
     */
    public MeetingAdapter(List<Meeting> meetingArrayList) {
        this.mMeetings = meetingArrayList;
    }

    // **************************************************
    // Private methods
    // **************************************************
    private int getGoodColors(String meetingRoom) {
        switch (meetingRoom) {
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

    // **************************************************
    // Public methods
    // **************************************************
    /**
     * Creates and returns a new ViewHolder for a meeting item view.
     * @param parent The parent ViewGroup in which to create the ViewHolder.
     * @param viewType The type of view to create.
     * @return The new ViewHolder.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemMeetingBinding binding = ItemMeetingBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    /**
     * Binds a ViewHolder to a meeting item in the list.
     * @param holder The ViewHolder to bind.
     * @param position The position of the meeting item in the list.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);
        holder.displayMeeting(meeting);

        holder.binding.picture.setImageResource(getGoodColors(meeting.getMeetingRoom()));
        holder.binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMeeting(holder.getBindingAdapterPosition());
            }
        });
    }

    /**
     * Returns the number of meetings in the list.
     * @return The number of meetings in the list.
     */
    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    /**
     * Deletes the meeting at the given position from the list and notifies the adapter of the change.
     * @param position The position of the meeting to delete.
     */
    public void deleteMeeting(int position) {
        mMeetings.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mMeetings.size());
    }

    /**
     * Updates the list of meetings to display and notifies the adapter of the change.
     * @param meeting The new list of meetings to display.
     */
    public void updateList(List<Meeting> meeting) {
        this.mMeetings.clear();
        this.mMeetings.addAll(meeting);
        notifyDataSetChanged();
    }

    /**
     * ViewHolder for a meeting item in the list.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemMeetingBinding binding;

        /**
         * Constructs a new ViewHolder for a meeting item view.
         * @param binding The binding for the meeting item view.
         */
        public ViewHolder(ItemMeetingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        /**
         * Displays the given meeting in the item view.
         * @param meeting The meeting to display.
         */
        public void displayMeeting(Meeting meeting) {
            SimpleDateFormat fmtOut = new SimpleDateFormat("dd/MM/yyyy");

            binding.room.setText(meeting.getMeetingRoom());
            binding.timeToStart.setText("-" + meeting.getMeetingStart() + "-");
            binding.subject.setText(meeting.getMeetingSubject());
            binding.date.setText(fmtOut.format(meeting.getMeetingDate()));

            List<String> meetingParticipants = meeting.getMeetingParticipants();
            StringBuilder builder = new StringBuilder();
            for (String participant : meetingParticipants) {
                builder.append(participant);
                builder.append("; ");
            }
            String participants = builder.toString().trim();
            if (participants.endsWith(";")) {
                participants = participants.substring(0, participants.length() - 1);
            }
            binding.participants.setText(participants);
        }
    }
}
