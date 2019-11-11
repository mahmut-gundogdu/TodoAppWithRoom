package com.mahmutgundogdu.todoapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Todo {
    public Todo(@NonNull String title, String description) {
        this.title = title;
        this.description = description;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String title;

    public String description;

    @NonNull
    @Override
    public String toString() {
        return title;
    }
}
