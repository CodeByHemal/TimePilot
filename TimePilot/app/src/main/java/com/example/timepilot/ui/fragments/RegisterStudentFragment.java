package com.example.timepilot.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.timepilot.R;
import com.example.timepilot.data.model.Student;
import com.example.timepilot.databinding.FragmentRegisterStudentBinding;
import com.example.timepilot.ui.viewholder.StudentViewModel;

public class RegisterStudentFragment extends Fragment {

    private FragmentRegisterStudentBinding binding;
    private StudentViewModel studentViewModel;

    public RegisterStudentFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterStudentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        // Handle Back Button
        binding.btnBack.setOnClickListener(v -> {
            if (getFragmentManager() != null) {
                getFragmentManager().popBackStack();
            }
        });


        // Handle Register Button
        binding.btnRegister.setOnClickListener(v -> {
            String name = binding.etStudentName.getText().toString().trim();
            String email = binding.etStudentEmail.getText().toString().trim();
            String roll = binding.etRollNumber.getText().toString().trim();
            String password = binding.etPassword.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || roll.isEmpty() || password.isEmpty()) {
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                Student student = new Student(name, email, password, roll);

                // Insert into database via ViewModel
                studentViewModel.insert(student);
                // Mock registration success
                Toast.makeText(getContext(), getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                // Return to previous screen
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
