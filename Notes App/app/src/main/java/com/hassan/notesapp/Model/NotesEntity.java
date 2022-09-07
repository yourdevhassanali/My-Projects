package com.hassan.notesapp.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes_Database")
public class NotesEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "notes_title")
    public String notesTitle;
    @ColumnInfo(name = "notes_subtitle")
    public String notesSubTitle;
    @ColumnInfo(name = "notes_date")
    public String notesDate;
    @ColumnInfo(name = "notes_priority")
    public String notesPriority;
    @ColumnInfo(name = "notes_data")
    public String notesData;

}
