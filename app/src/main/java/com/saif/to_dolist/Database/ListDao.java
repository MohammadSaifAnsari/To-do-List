package com.saif.to_dolist.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ListDao {
    @Insert
    public void insert( NotesModel notesModel);
    @Update
    public void update( NotesModel notesModel);
    @Delete
    public void delete( NotesModel notesModel);
    @Query("SELECT * FROM TodoList")
    public LiveData<List<NotesModel>> getAllData();
}
