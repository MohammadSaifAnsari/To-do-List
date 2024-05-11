package com.saif.to_dolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.saif.to_dolist.Database.NotesModel;
import com.saif.to_dolist.Model.ListViewModel;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv1;
    FloatingActionButton floatingActionButton;
    private ListViewModel listViewModel;
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

        floatingActionButton = findViewById(R.id.add_floating);
        rv1 = findViewById(R.id.recycler_view);
        listViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.
                getInstance(this.getApplication())).get(ListViewModel.class);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,InsertNoteActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            String title = data.getStringExtra("title");
            String description = data.getStringExtra("description");
            NotesModel notesModel = new NotesModel(title,description);
            listViewModel.insert(notesModel);
            Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show();
        }
    }
}