package com.hassan.notesapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hassan.notesapp.DAO.NotesDao;
import com.hassan.notesapp.Model.NotesEntity;


@Database(entities = {NotesEntity.class}, version = 1)
public abstract class NotesDatabase extends RoomDatabase {

    public abstract NotesDao notesDao();

    public static NotesDatabase INSTANCE;

    public static NotesDatabase getDatabaseInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    NotesDatabase.class,
                    "Notes_Database").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
