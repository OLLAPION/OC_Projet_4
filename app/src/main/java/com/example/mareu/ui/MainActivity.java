package com.example.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.appcompat.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.example.mareu.R;
import com.example.mareu.databinding.ActivityMainBinding;
import com.example.mareu.di.Injection;
import com.example.mareu.model.Meeting;
import com.example.mareu.repository.MeetingRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


/**
 * Entry point for this project.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // **************************************************
    // Fields
    // **************************************************
    private ActivityMainBinding binding;
    private List<Meeting> mMeetingArrayList = new ArrayList<>();

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

    @Override
    protected void onResume() {
        super.onResume();
        resetFilter();
    }

    // **************************************************
    // Public methods
    // **************************************************
    /**
     * {@inheritDoc}
     * Inflates the options menu from the specified XML resource and populates the menu provided as an argument.
     * @param menu The options menu to populate.
     * @return {@code true} to display the menu, {@code false} to not display it.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Called when a menu item is selected.
     * @param item The selected menu item.
     * @return True if the menu item selection is handled, false otherwise.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter_date:
                dateDialog();
                return true;
            case R.id.filter_rooms:
                roomDialog();
                return true;
            case R.id.filter_reset:
                resetFilter();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Called when a view has been clicked.
     * @param view The view that was clicked.
     */
    @Override
    public void onClick(View view) {
        if (view == binding.addMeeting) {
            startActivity(new Intent(this, AddMeetingsActivity.class));
        }
    }

    // **************************************************
    // Private methods
    // **************************************************
    /**
     * Initializes the interface of the main activity.
     * Inflates the layout using the generated binding class, sets the content view
     * and initializes the buttons and recycler view.
     */
    private void initUI() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setButton();
        initRecyclerView();
    }

    /**
     * Initializes the RecyclerView with a LinearLayoutManager and sets its adapter to a new MeetingAdapter.
     * The MeetingAdapter is initialized with an ArrayList of meetings.
     */
    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);

        MeetingAdapter mAdapter = new MeetingAdapter(mMeetingArrayList);
        binding.recyclerview.setAdapter(mAdapter);
    }

    /**
     * Sets up the click listener for the "add meeting" button.
     * When the button is clicked, the onClick() method will be called.
     */
    private void setButton() {
        binding.addMeeting.setOnClickListener(this);
    }

    /**
     * Resets the current filter applied to the meeting list and displays all meetings.
     * Clears the current meeting list and updates it with all meetings.
     * Notifies the RecyclerView's adapter that the data set has changed.
     */
    private void resetFilter() {
        mMeetingArrayList.clear();
        mMeetingArrayList.addAll(mMeetingRepository.getMeetings());
        binding.recyclerview.getAdapter().notifyDataSetChanged();
    }

    /**
     * Shows a dialog allowing the user to filter the meetings by room.
     * The method creates a spinner with the available rooms and sets an OnItemSelectedListener to
     * update the RecyclerView with the filtered meetings when the user selects a room.
     * The method uses an AlertDialog to display the spinner and the OK and Cancel buttons.
     */
    private void roomDialog() {
        Spinner spinner = new Spinner(this);
        List<String> salles = Arrays.asList("Salle_01", "Salle_02", "Salle_03", "Salle_04", "Salle_05", "Salle_06",
                "Salle_07", "Salle_08", "Salle_09", "Salle_10");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, salles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String roomSelected = (String) parent.getItemAtPosition(position);
                List<Meeting> meetingFiltered = mMeetingRepository.getMeetingFilteredBySalle(roomSelected);
                MeetingAdapter mAdapter = (MeetingAdapter) binding.recyclerview.getAdapter();
                mAdapter.updateList(meetingFiltered);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // if one day i want to use it !
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Filtrer par salle");
        builder.setView(spinner);
        builder.setPositiveButton("OK", null);
        builder.setNegativeButton("Annuler", null);
        builder.show();
    }

    /**
     * Displays a DatePickerDialog to filter meetings by date.
     * When a date is selected, the meeting list is updated with meetings that occur on that date.
     */
    private void dateDialog() {
        int selectedYear = 2023;
        int selectedMonth = 2;
        int selectedDayOfMonth = 1;
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                Calendar cal = Calendar.getInstance();
                cal.set(year, month, dayOfMonth);
                mMeetingArrayList.clear();
                mMeetingArrayList.addAll(mMeetingRepository.getMeetingFilteredByDate(cal.getTime()));
                binding.recyclerview.getAdapter().notifyDataSetChanged();
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);

        datePickerDialog.show();
    }
}
