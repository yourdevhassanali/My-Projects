package com.hassan.notesapp.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.hassan.notesapp.Model.NotesEntity;

import java.util.List;

@Dao
public interface NotesDao {

    @Query("SELECT * FROM Notes_Database")
    LiveData<List<NotesEntity>> getAllNotes();

    @Query("SELECT * FROM Notes_Database ORDER BY notes_priority DESC")
    LiveData<List<NotesEntity>> highToLow();

    @Query("SELECT * FROM Notes_Database ORDER BY notes_priority ASC")
    LiveData<List<NotesEntity>> lowToHigh();

    @Insert
    void insertNotes(NotesEntity... notesEntities);

    @Query("DELETE FROM Notes_Database WHERE id=:id")
    void deleteNotes(int id);

    @Update
    void updateNotes(NotesEntity notesEntities);


}
