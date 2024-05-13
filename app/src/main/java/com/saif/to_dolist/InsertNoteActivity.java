package com.saif.to_dolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.saif.to_dolist.Adapter.NoteListAdapter;

import java.util.Objects;

public class InsertNoteActivity extends AppCompatActivity {

    FloatingActionButton insertBut;
    EditText insTitle,insDes;
    Toolbar toolbarInsert;
    ImageView imageBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_insert_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        insertBut = findViewById(R.id.insertButton);
        insTitle = findViewById(R.id.insertTitle);
        insDes = findViewById(R.id.insertDescription);
        toolbarInsert = findViewById(R.id.toolbarInsert);
        imageBack = findViewById(R.id.back_img);


        setSupportActionBar(toolbarInsert);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);

        String type = getIntent().getStringExtra("noteType");
        if (type.equals("update")){
            getSupportActionBar().setTitle("Update");
            insTitle.setText(getIntent().getStringExtra("title"));
            insDes.setText(getIntent().getStringExtra("description"));
            int id = getIntent().getIntExtra("id",0);
            insertBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("title",insTitle.getText().toString());
                    intent.putExtra("description",insDes.getText().toString());
                    intent.putExtra("id",id);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
            imageBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("title",insTitle.getText().toString());
                    intent.putExtra("description",insDes.getText().toString());
                    intent.putExtra("id",id);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
        }else {
            getSupportActionBar().setTitle("New Task");
            insertBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("title",insTitle.getText().toString());
                    intent.putExtra("description",insDes.getText().toString());
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
            imageBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("title",insTitle.getText().toString());
                    intent.putExtra("description",insDes.getText().toString());
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(InsertNoteActivity.this,MainActivity.class));
    }
}