package com.ashishbharam.TaskCalenderNote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {

    EditText note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        note = findViewById(R.id.etNote);
        Button save = findViewById(R.id.btnSave);


        String day = getIntent().getExtras().getString("day");
        String month = getIntent().getExtras().getString("month");
        String year = getIntent().getExtras().getString("year");

        String noteDate = day+"/"+month+"/"+year;

        setNote(noteDate);

        save.setOnClickListener(v -> {

            SharedPrefManager.getInstance(this).saveNoteInfo(noteDate,note.getText().toString());

            Toast.makeText(this, "Saved :"+noteDate, Toast.LENGTH_SHORT).show();
        });
    }

    private void setNote(String noteDate) {
        String storedNote = SharedPrefManager.getInstance(this).getNoteInfo(noteDate);
        Log.d("SP", "onCreate: "+storedNote);

        if(storedNote != null){
            note.setText(storedNote);
        }
    }
}