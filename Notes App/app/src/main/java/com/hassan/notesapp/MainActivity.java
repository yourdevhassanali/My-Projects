package com.hassan.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hassan.notesapp.Activity.InsertNotesActivity;
import com.hassan.notesapp.Adapter.NotesAdapter;
import com.hassan.notesapp.Model.NotesEntity;
import com.hassan.notesapp.ViewModel.NotesViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newNotesBtn;
    NotesViewModel notesViewModel;
    RecyclerView notesRecyclerView;
    NotesAdapter notesAdapter;
    TextView nofilter, hightolow, lowtohigh,noNotesYet;
    List<NotesEntity> filterNotesAllList;
    int totalItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newNotesBtn = findViewById(R.id.newNotesBtn);
        notesRecyclerView = findViewById(R.id.notesRecyclerView);
        nofilter = findViewById(R.id.noFilter);
        hightolow = findViewById(R.id.hightolowFilter);
        lowtohigh = findViewById(R.id.lowtohighFilter);
        noNotesYet = findViewById(R.id.noNotesYet);

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        nofilter.setBackgroundResource(R.drawable.filter_selected_shape);

        nofilter.setOnClickListener(v -> {
            loadData(0);
            hightolow.setBackgroundResource(R.drawable.filter_unshape);
            lowtohigh.setBackgroundResource(R.drawable.filter_unshape);
            nofilter.setBackgroundResource(R.drawable.filter_selected_shape);
        });
        hightolow.setOnClickListener(v -> {
            loadData(1);
            hightolow.setBackgroundResource(R.drawable.filter_selected_shape);
            lowtohigh.setBackgroundResource(R.drawable.filter_unshape);
            nofilter.setBackgroundResource(R.drawable.filter_unshape);

        });
        lowtohigh.setOnClickListener(v -> {
            loadData(2);
            hightolow.setBackgroundResource(R.drawable.filter_unshape);
            lowtohigh.setBackgroundResource(R.drawable.filter_selected_shape);
            nofilter.setBackgroundResource(R.drawable.filter_unshape);

        });

        newNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InsertNotesActivity.class));
            }
        });

        notesViewModel.getAllNotes.observe(this, new Observer<List<NotesEntity>>() {
            @Override
            public void onChanged(List<NotesEntity> notesEntities) {
                setAdapter(notesEntities);
                if(notesEntities.isEmpty())
                {
                    noNotesYet.setVisibility(View.VISIBLE);
                }
                else {
                    noNotesYet.setVisibility(View.GONE);
                }
                filterNotesAllList = notesEntities;
            }
        });


    }//on create

    private void loadData(int i) {

        if (i == 0) {
            notesViewModel.getAllNotes.observe(this, new Observer<List<NotesEntity>>() {
                @Override
                public void onChanged(List<NotesEntity> notesEntities) {
                    setAdapter(notesEntities);
                    filterNotesAllList = notesEntities;
                }
            });
        } else if (i == 1) {
            notesViewModel.hightolow.observe(this, new Observer<List<NotesEntity>>() {
                @Override
                public void onChanged(List<NotesEntity> notesEntities) {
                    setAdapter(notesEntities);
                    filterNotesAllList = notesEntities;
                }
            });
        } else if (i == 2) {
            notesViewModel.lowtohigh.observe(this, new Observer<List<NotesEntity>>() {
                @Override
                public void onChanged(List<NotesEntity> notesEntities) {
                    setAdapter(notesEntities);
                    filterNotesAllList = notesEntities;
                }
            });
        }
    }


    public void setAdapter(List<NotesEntity> notesEntities) {

        notesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        notesAdapter = new NotesAdapter(MainActivity.this, notesEntities);
        notesRecyclerView.setAdapter(notesAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.search_notes, menu);

        MenuItem menuItem = menu.findItem(R.id.app_bar_search);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Notes...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                notesFilter(newText);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    private void notesFilter(String newText) {
        ArrayList<NotesEntity> filterNames = new ArrayList<>();

        for (NotesEntity notesEntity : this.filterNotesAllList) {
            if (notesEntity.notesTitle.contains(newText) || notesEntity.notesSubTitle.contains(newText)) {
                filterNames.add(notesEntity);
            }
        }
        this.notesAdapter.searchNotes(filterNames);
    }
}//end