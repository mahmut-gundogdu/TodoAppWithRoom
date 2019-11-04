package com.mahmutgundogdu.todoapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert()
    void insert(Todo entity);


    @Query("SELECT * FROM Todo")
    List<Todo> getAll();
}
