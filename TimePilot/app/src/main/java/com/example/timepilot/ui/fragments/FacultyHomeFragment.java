package com.example.timepilot.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.timepilot.databinding.FragmentFacultyHomeBinding;

public class FacultyHomeFragment extends Fragment {

    private FragmentFacultyHomeBinding binding;

    public FacultyHomeFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFacultyHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        // Handle Add Student FAB click
        binding.fabAddStudent.setOnClickListener(v -> {
            if (getActivity() instanceof com.example.timepilot.ui.main.faculty.FacultyActivity) {
                ((com.example.timepilot.ui.main.faculty.FacultyActivity) getActivity())
                        .loadFragment(new RegisterStudentFragment(), true);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
