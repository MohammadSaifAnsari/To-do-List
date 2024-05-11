package com.saif.to_dolist.Model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.saif.to_dolist.Database.NoteRepo;
import com.saif.to_dolist.Database.NotesModel;

import java.util.List;

public class ListViewModel extends AndroidViewModel {

    private NoteRepo noteRepo;
    private LiveData<List<NotesModel>> noteList;

    public ListViewModel(@NonNull Application application) {
        super(application);
        noteRepo = new NoteRepo(application);
        noteList = noteRepo.getAllData();
    }
    public void insert(NotesModel notesModel){
        noteRepo.insertData(notesModel);
    }
    public void update(NotesModel notesModel){
        noteRepo.updateData(notesModel);
    }
    public void delete(NotesModel notesModel){
        noteRepo.deleteData(notesModel);
    }

    public LiveData<List<NotesModel>> getAllData() {
        return noteList;
    }
}
