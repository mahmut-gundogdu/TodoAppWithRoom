package com.mahmutgundogdu.todoapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class TodoDetailActivity extends AppCompatActivity {

    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        appDatabase = AppDatabase.getInstance(this);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText name = findViewById(R.id.name);
                EditText description = findViewById(R.id.description);

                if (name.getText().length() == 0) {
                    Snackbar.make(view, "Title is required", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {

                    insert(name.getText().toString(), description.getText().toString());

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }

            }
        });

    }

    private void insert(String name, String description) {
        Todo todo = new Todo(name, description);

        new CreateTodoAsyncTask().execute(todo);
    }

    public void fab_click(View v) {


    }


    private class CreateTodoAsyncTask extends AsyncTask<Todo,Void,Void>{

        @Override
        protected Void doInBackground(Todo... todos) {
            appDatabase.todoModel().insert(todos[0]);

            return null;
        }
    }
}
