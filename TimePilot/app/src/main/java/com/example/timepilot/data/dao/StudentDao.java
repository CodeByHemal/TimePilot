package com.example.timepilot.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.timepilot.data.model.Student;

import java.util.List;

@Dao
public interface StudentDao {
    @Query("SELECT * FROM student")
    LiveData<List<Student>> getAll();
    @Insert
    void insertAll(Student... students);
    @Update
    void update(Student student);
    @Delete
    void delete(Student student);

    @Query("SELECT * FROM student WHERE email = :email AND password = :password LIMIT 1")
    Student login(String email, String password);
}
