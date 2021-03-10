package com.ashishbharam.TaskCalenderNote;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ashishbharam.TaskCalenderNote.adapter.DaysRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DaysRecyclerViewAdapter.OnDayClickListener, AdapterView.OnItemSelectedListener {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private DaysRecyclerViewAdapter adapter;
    private ArrayList<String> daysList = new ArrayList<String>();
    Spinner monthSpinner, yearSpinner;
    Button submit;
    String selected_month;
    String selected_year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewofDays);
        monthSpinner = findViewById(R.id.spinnerMonth);
        yearSpinner = findViewById(R.id.spinnerYear);
        submit = findViewById(R.id.btnSubmit);

        monthSpinner.setOnItemSelectedListener(this);
        yearSpinner.setOnItemSelectedListener(this);


        List<String> months30daysList = Arrays.asList("April", "June", "September", "November");

        submit.setOnClickListener(v -> {

            listClear();

            if (selected_month.equals("Select") || selected_year.equals("Select")) {
                Toast.makeText(this, "Select month & year", Toast.LENGTH_SHORT).show();
                return;
            }

            int numberOfDays = 30;
            int year = Integer.parseInt(selected_year);

            if (months30daysList.contains(selected_month)) {
                numberOfDays = 30;
            } else if (selected_month.equals("February")) {
                numberOfDays = isLeapYear(year) ? 29 : 28;
            }else {
                numberOfDays = 31;
            }

            for (int i = 1; i <= numberOfDays; i++) {
                daysList.add("" + i);
            }

            initRecyclerView();
            adapter.notifyDataSetChanged();
        });

    }

    private void listClear() {
        daysList.clear();
        adapter = new DaysRecyclerViewAdapter(this, daysList);
        adapter.notifyDataSetChanged();
    }

    private boolean isLeapYear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new GridLayoutManager(this, 7));
        adapter = new DaysRecyclerViewAdapter(this, daysList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {

            case R.id.spinnerMonth:

                selected_month = monthSpinner.getSelectedItem().toString();
                break;

            case R.id.spinnerYear:
                selected_year = yearSpinner.getSelectedItem().toString();
                Log.d(TAG, "onItemSelected: " + selected_year);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onDayClick(int position) {
        Intent intent = new Intent(MainActivity.this, NoteActivity.class);
        position = position + 1;
        intent.putExtra("day", "" + position);
        intent.putExtra("year", selected_year);
        intent.putExtra("month", selected_month);
        startActivity(intent);

    }
}