package com.example.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.example.mareu.databinding.ActivityAddMeetingsBinding;
import com.example.mareu.di.Injection;
import com.example.mareu.model.Meeting;
import com.example.mareu.repository.MeetingRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Add a meeting in this project.
 */
public class AddMeetingsActivity extends AppCompatActivity implements  View.OnClickListener {

    // **************************************************
    // Fields
    // **************************************************
    private ActivityAddMeetingsBinding binding;
    private String selectedRoom;

    // **************************************************
    // Constants
    // **************************************************
    /** Contains the only one instence of the MeetingRepository */
    private final MeetingRepository mMeetingRepository = Injection.getMeetingRepository();

    // **************************************************
    // Protected methods
    // **************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    // **************************************************
    // Public methods
    // **************************************************
    /**
     * Overrides the onClick method of View.OnClickListener.
     * If the clicked view is the "Add Meeting" button, calls onSubmit method.
     * @param view The view that was clicked.
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

    // **************************************************
    // Private methods
    // **************************************************
    /**
     * Initializes the user interface of the AddMeetingsActivity by inflating the layout and setting it as the content view,
     * calling methods to initialize the ChoiceRooms, listenerShowDateAndTimePickerDialog and setButton.
     */
    private void initUI() {
        binding = ActivityAddMeetingsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ChoiceRooms();
        listenerShowDateAndTimePickerDialog();

        setButton();
    }

    /**
     * Sets listeners on the date and time picker widgets in the UI, which will display date and time picker dialogs
     * when clicked, and update their associated text fields with the chosen date/time.
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
     * Creates a date picker dialog and shows it when the user clicks on the corresponding button.
     * The dialog allows the user to select a date and updates the text of the date button with the selected date.
     * The minimum date that can be selected is set to the current date minus one second to prevent the user from selecting a past date.
     */
    private void showDatePickerDialog() {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, yearSelected, monthOfYear, dayOfMonth) -> {
            String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + yearSelected;
            binding.meetingDay.setText(date);
        }, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    /**
     * Creates a date picker dialog and shows it when the user clicks on the corresponding button.
     * The dialog allows the user to select a date and updates the text of the date button with the selected date.
     * The minimum date that can be selected is set to the current date minus one second to prevent the user from selecting a past date.
     */
    private void showTimePickerDialog(EditText editText) {
        final Calendar cldr = Calendar.getInstance();
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int minute = cldr.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minuteOfDay) -> {
            @SuppressLint("DefaultLocale") String time = String.format("%02d:%02d", hourOfDay, minuteOfDay);
            editText.setText(time);
        }, hour, minute, true);
        timePickerDialog.show();
    }

    /**
     * Sets up the spinner to allow the user to choose a meeting room.
     * The list of choices is defined in an array resource "choiceRooms".
     * When a room is selected, the "selectedRoom" variable is updated accordingly.
     */
    private void ChoiceRooms() {
        Spinner spinnerChoiceRoom = binding.meetingRooms;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.choiceRooms, R.layout.item_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChoiceRoom.setAdapter(adapter);

        spinnerChoiceRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedRoom = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // if one day i want to use it !
            }
        });
    }

    /** Set the click listener on the "Add Meeting" button to handle the click event. */
    private void setButton() {
        binding.buttonAddMeeting.setOnClickListener(this);
    }

    /**
     * Method called when the user clicks on the "Add Meeting" button. It retrieves the meeting details
     * entered by the user and creates a new meeting object, which is then added to the repository.
     * If any of the required fields are empty, an error message is displayed and the method returns
     * without adding the meeting. If the meeting is successfully added, a toast message is displayed
     * and the activity is finished.
     @throws ParseException if there is an error parsing the meeting date
     */
    private void onSubmit() throws ParseException {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat fmtOut = new SimpleDateFormat("dd/MM/yyyy");

        String meetingStart = Objects.requireNonNull(binding.meetingStart.getText()).toString();
        String meetingEnd = Objects.requireNonNull(binding.meetingEnd.getText()).toString();
        String meetingSubject = Objects.requireNonNull(binding.meetingSubject.getText()).toString();
        String meetingDate = Objects.requireNonNull(binding.meetingDay.getText()).toString();
        Date dateAuFormat = fmtOut.parse(meetingDate);

        String[] participants = Objects.requireNonNull(binding.meetingParticipants.getText()).toString().split(";");
        List<String> meetingEmailList = new ArrayList<>();
        for(String participant : participants) {
            String email = participant.trim();
            if(email.contains("@")) { // Vérification sommaire de l'adresse e-mail
                meetingEmailList.add(email);
            }
        }

        if (meetingDate.isEmpty()) {
            binding.meetingDay.setError(getString(R.string.chooce_day));
            return;
        }
        if (meetingStart.isEmpty()) {
            binding.meetingStart.setError(getString(R.string.start_time));
            return;
        }
        if (meetingEnd.isEmpty()) {
            binding.meetingEnd.setError(getString(R.string.end_time));
            return;
        }
        if (meetingSubject.isEmpty()) {
            binding.meetingSubject.setError(getString(R.string.subject));
            return;
        }
        if (meetingEmailList.isEmpty()) {
            binding.meetingParticipants.setError(getString(R.string.participants));
            return;
        }

        mMeetingRepository.addMeeting(new Meeting(selectedRoom, meetingStart, meetingEnd, meetingSubject, meetingEmailList, dateAuFormat));
        Toast.makeText(this, "Reunion reservé !", Toast.LENGTH_LONG).show();
        finish();
    }
}