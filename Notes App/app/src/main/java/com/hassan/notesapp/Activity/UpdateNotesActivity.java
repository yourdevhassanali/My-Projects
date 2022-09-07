package com.hassan.notesapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.hassan.notesapp.Model.NotesEntity;
import com.hassan.notesapp.R;
import com.hassan.notesapp.ViewModel.NotesViewModel;
import com.hassan.notesapp.databinding.ActivityUpdateNotesBinding;

import java.util.Date;

public class UpdateNotesActivity extends AppCompatActivity {

    ActivityUpdateNotesBinding binding;
    String priority = "1";
    String stitle, ssubtitle, snotes, spriority;
    int iid;
    NotesViewModel notesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);


        iid = getIntent().getIntExtra("id", 0);
        stitle = getIntent().getStringExtra("title");
        ssubtitle = getIntent().getStringExtra("subtitle");
        snotes = getIntent().getStringExtra("notesdata");
        spriority = getIntent().getStringExtra("priority");


        binding.updateTitle.setText(stitle);
        binding.updateSubtitle.setText(ssubtitle);
        binding.updatenotesData.setText(snotes);

        switch (spriority) {
            case "1":
                binding.greenPriority.setImageResource(R.drawable.ic_done);
                break;
            case "2":
                binding.yellowPriority.setImageResource(R.drawable.ic_done);
                break;
            case "3":
                binding.redPriority.setImageResource(R.drawable.ic_done);
                break;
        }

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


        binding.updateNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = binding.updateTitle.getText().toString();
                String subTitle = binding.updateSubtitle.getText().toString();
                String notesData = binding.updatenotesData.getText().toString();

                createNotes(title, subTitle, notesData);

            }
        });

    }//on create

    private void createNotes(String title, String subTitle, String notesData) {

        Date date = new Date();
        CharSequence charSequence = DateFormat.format("MMMM d,yyyy", date.getTime());

        NotesEntity updateNote = new NotesEntity();

        updateNote.id = iid;
        updateNote.notesTitle = title;
        updateNote.notesSubTitle = subTitle;
        updateNote.notesData = notesData;
        updateNote.notesDate = charSequence.toString();
        updateNote.notesPriority = priority;


        notesViewModel.updateNote(updateNote);

        Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.delete) {

            BottomSheetDialog sheetDialog = new BottomSheetDialog(UpdateNotesActivity.this,R.style.BottomSheetStyle);
            View view = LayoutInflater.from(UpdateNotesActivity.this)
                    .inflate(R.layout.delete_bottom_sheet, (LinearLayout) findViewById(R.id.bottomSheet));
            sheetDialog.setContentView(view);
            sheetDialog.show();

            TextView yes_delete, no_delete;

            yes_delete = view.findViewById(R.id.delete_Yes);
            no_delete = view.findViewById(R.id.delete_No);

            yes_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    notesViewModel.deleteNote(iid);
                    finish();
                }
            });

            no_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sheetDialog.dismiss();
                }
            });


        }
        return true;
    }
}//end