package com.example.timepilot.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.timepilot.R;
import com.example.timepilot.databinding.ActivityLoginBinding;
import com.example.timepilot.ui.main.faculty.FacultyActivity;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Edge-to-Edge Padding fix
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Handle Role Toggling for Student/Faculty
        binding.toggleRole.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (isChecked) {
                if (checkedId == R.id.btnStudent) {
                    binding.btnStudent.setTextColor(getColor(R.color.purple_primary));
                    binding.btnFaculty.setTextColor(getColor(R.color.text_secondary));
                    binding.btnFaculty.setBackgroundColor(android.graphics.Color.TRANSPARENT);
                } else if (checkedId == R.id.btnFaculty) {
                    binding.btnFaculty.setTextColor(getColor(R.color.purple_primary));
                    binding.btnStudent.setTextColor(getColor(R.color.text_secondary));
                    binding.btnStudent.setBackgroundColor(android.graphics.Color.TRANSPARENT);
                }
            }
        });

        // Sign In Action: Determine role and redirect
        binding.btnSignIn.setOnClickListener(v -> {
            boolean isFaculty = (binding.toggleRole.getCheckedButtonId() == R.id.btnFaculty);
            
            Intent intent;
            if (isFaculty) {
                intent = new Intent(LoginActivity.this, FacultyActivity.class);
            } else {
                intent = new Intent(LoginActivity.this, MainActivity.class);
            }
            
            startActivity(intent);
            // Finish this activity so the user cannot navigate back to the login screen
            finish();
        });
    }
}
