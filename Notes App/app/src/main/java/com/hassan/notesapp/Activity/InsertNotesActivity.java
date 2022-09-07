package com.hassan.notesapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import com.hassan.notesapp.Model.NotesEntity;
import com.hassan.notesapp.R;
import com.hassan.notesapp.ViewModel.NotesViewModel;
import com.hassan.notesapp.databinding.ActivityInsertNotesBinding;

import java.util.Date;

public class InsertNotesActivity extends AppCompatActivity {

    ActivityInsertNotesBinding binding;
    String title, subTitle, notesData;
    NotesViewModel notesViewModel;
    String priority = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        binding.greenPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.greenPriority.setImageResource(R.drawable.ic_done);
                binding.yellowPriority.setImageResource(0);
                binding.redPriority.setImageResource(0);
                priority = "1";
            }
        });
        binding.yellowPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.yellowPriority.setImageResource(R.drawable.ic_done);
                binding.greenPriority.setImageResource(0);
                binding.redPriority.setImageResource(0);
                priority = "2";
            }
        });
        binding.redPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.redPriority.setImageResource(R.drawable.ic_done);
                binding.yellowPriority.setImageResource(0);
                binding.greenPriority.setImageResource(0);
                priority = "3";
            }
        });


        binding.doneNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title = binding.notesTitle.getText().toString();
                subTitle = binding.notesSubtitle.getText().toString();
                notesData = binding.notesData.getText().toString();

                createNotes(title, subTitle, notesData);
            }
        });


    }//on Create

    private void createNotes(String title, String subTitle, String notesData) {

        NotesEntity notesEntity = new NotesEntity();
        Date date = new Date();
        CharSequence charSequence = DateFormat.format("MMMM d,yyyy", date.getTime());

        notesEntity.notesTitle = title;
        notesEntity.notesSubTitle = subTitle;
        notesEntity.notesData = notesData;
        notesEntity.notesDate = charSequence.toString();
        notesEntity.notesPriority = priority;

        notesViewModel.insertNote(notesEntity);

        Toast.makeText(this, "Note Created", Toast.LENGTH_SHORT).show();
        finish();

    }
}//end