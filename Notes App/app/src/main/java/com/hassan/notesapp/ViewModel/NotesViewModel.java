package com.hassan.notesapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.hassan.notesapp.DAO.NotesDao;
import com.hassan.notesapp.Model.NotesEntity;
import com.hassan.notesapp.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository repository;
    public LiveData<List<NotesEntity>> getAllNotes;
    public LiveData<List<NotesEntity>> hightolow;
    public LiveData<List<NotesEntity>> lowtohigh;


    public NotesViewModel(@NonNull Application application) {
        super(application);

        repository = new NotesRepository(application);
        getAllNotes = repository.getAllNotes;
        hightolow = repository.hightolow;
        lowtohigh = repository.lowtohigh;

    }

    public void insertNote(NotesEntity notesEntity){
        repository.insertNotes(notesEntity);
    }
    public void deleteNote(int id){
        repository.deleteNotes(id);
    }
    public void updateNote(NotesEntity notesEntity){
        repository.updateNotes(notesEntity);
    }

}

