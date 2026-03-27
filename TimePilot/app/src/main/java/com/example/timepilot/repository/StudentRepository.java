package com.example.timepilot.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.timepilot.data.dao.StudentDao;
import com.example.timepilot.data.database.AppDatabase;
import com.example.timepilot.data.model.Student;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StudentRepository {

    private StudentDao studentDao;
    private LiveData<List<Student>> allStudents;
    private ExecutorService executorService;

    public StudentRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        studentDao = db.studentDao();
        allStudents = studentDao.getAll();
        executorService = Executors.newSingleThreadExecutor();
    }

    // Insert student (background thread)
    public void insert(Student student) {
        executorService.execute(() -> studentDao.insertAll(student));
    }

    // Get all students
    public LiveData<List<Student>> getAllStudents() {
        return allStudents;
    }

    public Student login(String email, String password) {
        return studentDao.login(email, password);
    }
}