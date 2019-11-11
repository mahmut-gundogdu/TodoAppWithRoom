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

    Integer id = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        appDatabase = AppDatabase.getInstance(this);

        Intent intent = getIntent();
        boolean isIdExists = intent.hasExtra("id");
        if (isIdExists) {
            id = intent.getIntExtra("id", 0);
            new GetTodoAsyncTask().execute(id);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText name = findViewById(R.id.name);
                EditText description = findViewById(R.id.description);

                if (name.getText().length() == 0) {
                    Snackbar.make(view, "Title is required", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {

                    Todo todo = new Todo(name.getText().toString(), description.getText().toString());

                    if (id == null) {
                        new CreateTodoAsyncTask().execute(todo);
                    } else {
                        todo.id = id;
                        new UpdateTodoAsync().execute(todo);
                    }

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }

            }
        });

    }

    public void fab_click(View v) {


    }


    private class CreateTodoAsyncTask extends AsyncTask<Todo, Void, Void> {

        @Override
        protected Void doInBackground(Todo... todos) {
            appDatabase.todoModel().insert(todos[0]);

            return null;
        }
    }

    private class GetTodoAsyncTask extends AsyncTask<Integer, Void, Todo> {

        @Override
        protected Todo doInBackground(Integer... ints) {

            Integer id = ints[0];
            Todo todo = appDatabase.todoModel().get(id);

            return todo;
        }


        @Override
        protected void onPostExecute(Todo todo) {
            super.onPostExecute(todo);
            EditText name = findViewById(R.id.name);
            EditText description = findViewById(R.id.description);
            name.setText(todo.title);
            description.setText(todo.description);
        }
    }

    private class UpdateTodoAsync extends AsyncTask<Todo, Void, Void> {

        @Override
        protected Void doInBackground(Todo... todos) {

            Todo todo = todos[0];
            appDatabase.todoModel().update(todo);
            return null;
        }
    }
}
