package com.saif.to_dolist.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepo {
    private ListDao listDao;
    private LiveData<List<NotesModel>> noteList;

    public  NoteRepo (Application application){
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        listDao = noteDatabase.listDao();
        noteList = listDao.getAllData();
    }

    public  void insertData(NotesModel notesModel){
        new InsertTask(listDao).execute(notesModel);
    }
    public  void updateData(NotesModel notesModel){
        new UpdateTask(listDao).execute(notesModel);
    }
    public  void deleteData(NotesModel notesModel){
        new DeleteTask(listDao).execute(notesModel);
    }

    public LiveData<List<NotesModel>> getAllData(){
        return noteList;
    }
    private static class InsertTask extends AsyncTask<NotesModel,Void,Void>{

        private ListDao listDao;

        public InsertTask(ListDao listDao) {
            this.listDao = listDao;
        }

        @Override
        protected Void doInBackground(NotesModel... notesModels) {
            listDao.insert(notesModels[0]);
            return null;
        }
    }
    private static class UpdateTask extends AsyncTask<NotesModel,Void,Void>{

        private ListDao listDao;

        public UpdateTask(ListDao listDao) {
            this.listDao = listDao;
        }

        @Override
        protected Void doInBackground(NotesModel... notesModels) {
            listDao.update(notesModels[0]);
            return null;
        }
    }
    private static class DeleteTask extends AsyncTask<NotesModel,Void,Void>{

        private ListDao listDao;

        public DeleteTask(ListDao listDao) {
            this.listDao = listDao;
        }

        @Override
        protected Void doInBackground(NotesModel... notesModels) {
            listDao.delete(notesModels[0]);
            return null;
        }
    }
}
