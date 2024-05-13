package com.saif.to_dolist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.saif.to_dolist.Adapter.NoteListAdapter;
import com.saif.to_dolist.Database.NotesModel;
import com.saif.to_dolist.Model.ListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv1;
    FloatingActionButton floatingActionButton;
    private ListViewModel listViewModel;
    NoteListAdapter noteListAdapter = new NoteListAdapter();

    Toolbar toolbarMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        toolbarMain = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbarMain);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("All Lists");

        floatingActionButton = findViewById(R.id.add_floating);
        rv1 = findViewById(R.id.recycler_view);
        listViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.
                getInstance(this.getApplication())).get(ListViewModel.class);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InsertNoteActivity.class);
                intent.putExtra("noteType", "addNote");
                startActivityForResult(intent, 1);
            }
        });

        rv1.setLayoutManager(new LinearLayoutManager(this));
        rv1.setHasFixedSize(true);
        rv1.setAdapter(noteListAdapter);

        listViewModel.getAllData().observe(this, new Observer<List<NotesModel>>() {
            @Override
            public void onChanged(List<NotesModel> notesModels) {
                noteListAdapter.submitList(notesModels);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                if (direction == ItemTouchHelper.RIGHT) {
                    listViewModel.delete(noteListAdapter.getCurrentList().get(viewHolder.getAdapterPosition()));
                    Toast.makeText(MainActivity.this, "Note Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, InsertNoteActivity.class);
                    intent.putExtra("noteType", "update");
                    intent.putExtra("title", noteListAdapter.getCurrentList().get(viewHolder.getAdapterPosition()).getTitle());
                    intent.putExtra("description", noteListAdapter.getCurrentList().get(viewHolder.getAdapterPosition()).getDescription());
                    intent.putExtra("id", noteListAdapter.getCurrentList().get(viewHolder.getAdapterPosition()).getId());
                    startActivityForResult(intent, 3);
                    Toast.makeText(MainActivity.this, "Updating", Toast.LENGTH_SHORT).show();
                }

            }
        }).attachToRecyclerView(rv1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            String title = data.getStringExtra("title");
            String description = data.getStringExtra("description");
            NotesModel notesModel = new NotesModel(title, description);
            listViewModel.insert(notesModel);
            Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show();
        } else if (requestCode == 3) {
            String title = data.getStringExtra("title");
            String description = data.getStringExtra("description");
            NotesModel notesModel = new NotesModel(title, description);
            notesModel.setId(data.getIntExtra("id", 0));
            listViewModel.update(notesModel);
            noteListAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        //Exit app on back pressed
        Intent a = new Intent(Intent.ACTION_MAIN); //Launches home screen
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }
}