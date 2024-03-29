package com.mahmutgundogdu.todoapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert()
    void insert(Todo entity);


    @Query("SELECT * FROM Todo")
    List<Todo> getAll();

    @Query("SELECT * FROM Todo WHERE id = :id")
    Todo get(int id);

    @Update
    void update(Todo u);
}
