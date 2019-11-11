package com.mahmutgundogdu.todoapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MainViewModel viewModel;
    private AppDatabase appDatabase;

    List<Todo> itemList = new ArrayList<>();
    ArrayAdapter<Todo> arrayAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = findViewById(R.id.listView);
        itemList.add(new Todo("HandCoded", ""));
        arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), TodoDetailActivity.class);

                int idOfEntity = itemList.get(position).id;
                i.putExtra("id", idOfEntity);
                startActivity(i);
            }
        });

        ///   viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        appDatabase = AppDatabase.getInstance(this);
        new GetTodoListAsyncTask().execute();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), TodoDetailActivity.class);
                startActivity(i);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class GetTodoListAsyncTask extends AsyncTask<Void, Void, List<Todo>> {
        @Override
        protected List<Todo> doInBackground(Void... voids) {
            List<Todo> todos = appDatabase.todoModel().getAll();

            return todos;
        }

        @Override
        protected void onPostExecute(List<Todo> todos) {
            super.onPostExecute(todos);
            arrayAdapter.clear();
            arrayAdapter.addAll(todos);
            arrayAdapter.notifyDataSetChanged();
            itemList = todos;
        }
        //
//        @Override
//        protected Void doInBackground(Void... voids) {
//
//            List<Todo> todos = appDatabase.todoModel().getAll();
//
//            ListView listView = findViewById(R.id.listView);
//
//
//            ArrayAdapter<Todo> arrayAdapter = new ArrayAdapter<Todo>
//                    (getApplicationContext(), android.R.layout.simple_list_item_1, ulkeler);
//
//            listView.setAdapter(arrayAdapter);
//
//            return null;
//        }


    }
}
