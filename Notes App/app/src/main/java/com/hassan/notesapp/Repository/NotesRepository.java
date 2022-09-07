package com.hassan.notesapp.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.hassan.notesapp.DAO.NotesDao;
import com.hassan.notesapp.Database.NotesDatabase;
import com.hassan.notesapp.Model.NotesEntity;

import java.util.List;

public class NotesRepository {


    public NotesDao notesDao;
    public LiveData<List<NotesEntity>> getAllNotes;
    public LiveData<List<NotesEntity>> hightolow;
    public LiveData<List<NotesEntity>> lowtohigh;


    public NotesRepository(Application application) {
        NotesDatabase database = NotesDatabase.getDatabaseInstance(application);
        notesDao = database.notesDao();
        getAllNotes = notesDao.getAllNotes();
        hightolow = notesDao.highToLow();
        lowtohigh = notesDao.lowToHigh();
    }

    public void insertNotes(NotesEntity notesEntity) {
        notesDao.insertNotes(notesEntity);
    }

    public void deleteNotes(int id) {
        notesDao.deleteNotes(id);
    }

    public void updateNotes(NotesEntity notesEntity) {
        notesDao.updateNotes(notesEntity);
    }
}
