package com.saif.to_dolist.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = NotesModel.class,version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private  static  NoteDatabase instanse;
    public  abstract ListDao listDao();
    public static synchronized NoteDatabase getInstance(Context context){
        if (instanse == null){
            instanse = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class,"note_database")
                    .fallbackToDestructiveMigration().build();
        }
        return instanse;
    }
}
