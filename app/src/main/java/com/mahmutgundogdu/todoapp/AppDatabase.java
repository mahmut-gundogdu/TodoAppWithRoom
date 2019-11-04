package com.mahmutgundogdu.todoapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Todo.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {
    public static final String DB_NAME = "todoDb";
    private static AppDatabase instance;

    public abstract TodoDao todoModel();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                     .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
