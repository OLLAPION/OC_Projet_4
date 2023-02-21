package com.example.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mareu.R;
import com.example.mareu.databinding.ActivityAddReunionBinding;
import com.example.mareu.di.Injection;
import com.example.mareu.model.Reunion;
import com.example.mareu.repository.ReunionRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddReunionActivity extends AppCompatActivity implements  View.OnClickListener {

    ActivityAddReunionBinding binding;
    private ReunionRepository mReunionRepository = Injection.getReunionRepository();
    private String selectedRoom;
    private DatePickerDialog datePickerDialog;

    /**
     * OnCreate methode
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    /**
     * init of methods used
     */
    private void initUI() {
        binding = ActivityAddReunionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ChoiceRooms();
        listenerShowDateAndTimePickerDialog();

        setButton();
    }

    /**
     * listener to show date and time picker dialog
     */
    private void listenerShowDateAndTimePickerDialog() {
        binding.meetingDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        binding.meetingStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(binding.meetingStart);
            }
        });
        binding.meetingEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(binding.meetingEnd);
            }
        });
    }

    /**
     * The dialog to show date picker
     */
    private void showDatePickerDialog() {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);

        datePickerDialog = new DatePickerDialog(this, (view, yearSelected, monthOfYear, dayOfMonth) -> {
            String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + yearSelected;
            binding.meetingDay.setText(date);
        }, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    /**
     * The dialog to show time picker
     */
    private void showTimePickerDialog(EditText editText) {
        final Calendar cldr = Calendar.getInstance();
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int minute = cldr.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minuteOfDay) -> {
            String time = String.format("%02d:%02d", hourOfDay, minuteOfDay);
            editText.setText(time);
        }, hour, minute, true);
        timePickerDialog.show();
    }

    /**
     * Spinner to choice the perfect room
     */
    private void ChoiceRooms() {
        // dois-je garder obligatoirement spinnerChoiceRoom ?
        Spinner spinnerChoiceRoom = binding.meetingRooms;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.choiceRooms, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChoiceRoom.setAdapter(adapter);

        spinnerChoiceRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedRoom = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Faire quelque chose si rien n'a été sélectionné ???
            }
        });

    }

    /**
     * Listener of button for adding a meeting
     */
    private void setButton() {
        binding.buttonAddMeeting.setOnClickListener(this);
    }

    /**
     * On click on the add button execute onSubmit
     */
    @Override
    public void onClick(View view) {
        if (view == binding.buttonAddMeeting) {
            try {
                onSubmit();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * in onSubmit retrieve information to add with check if it is empty
     */
    private void onSubmit() throws ParseException {
        SimpleDateFormat fmtOut = new SimpleDateFormat("dd/MM/yyyy");
        List<String> listParticipants = new ArrayList<>();

        String heureDebut = binding.meetingStart.getText().toString();
        String heureFin = binding.meetingEnd.getText().toString();
        String sujet = binding.meetingSubject.getText().toString();
        String date = binding.meetingDay.getText().toString();
        Date dateAuFormat = fmtOut.parse(date);
        String participant = binding.meetingParticipants.getText().toString().trim();
        listParticipants.add(participant);

        if (date.isEmpty()) {
            binding.meetingDay.setError("Choisissez un jour");
            return;
        }
        if (heureDebut.isEmpty()) {
            binding.meetingStart.setError("Choisissez une heure de commencement");
            return;
        }
        if (heureFin.isEmpty()) {
            binding.meetingEnd.setError("Choisissez une heure de fin");
            return;
        }
        if (sujet.isEmpty()) {
            binding.meetingSubject.setError("Choisissez un sujet");
            return;
        }
        if (participant.isEmpty()) {
            binding.meetingParticipants.setError("Ajoutez au moins deux participants");
            return;
        }

        mReunionRepository.addReunion(new Reunion(selectedRoom, heureDebut, heureFin, sujet, listParticipants, dateAuFormat));
        Toast.makeText(this, "Reunion reservé !", Toast.LENGTH_LONG).show();
        finish();
    }
}