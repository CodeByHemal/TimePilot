package com.example.timepilot.ui.viewholder;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.timepilot.data.model.Student;
import com.example.timepilot.repository.StudentRepository;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    private StudentRepository repository;
    private LiveData<List<Student>> allStudents;

    public StudentViewModel(@NonNull Application application) {
        super(application);
        repository = new StudentRepository(application);
        allStudents = repository.getAllStudents();
    }

    public void insert(Student student) {
        repository.insert(student);
    }

    public LiveData<List<Student>> getAllStudents() {
        return allStudents;
    }

    public Student login(String email, String password) {
        return repository.login(email, password);
    }
}
