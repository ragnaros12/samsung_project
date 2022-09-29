package com.company.oils.ui.fragments.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.company.oils.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.enterEmail.setText(profileViewModel.getEmail());
        binding.enterFio.setText(profileViewModel.getName());
        binding.enterPhone.setText(profileViewModel.getPhone());

        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileViewModel.save(binding.enterFio.getText().toString(), binding.enterPhone.getText().toString(),
                        binding.enterEmail.getText().toString());
                Toast.makeText(getContext(), "Данные успешно сохранены", Toast.LENGTH_LONG).show();

                binding.enterEmail.setText(profileViewModel.getEmail());
                binding.enterFio.setText(profileViewModel.getName());
                binding.enterPhone.setText(profileViewModel.getPhone());
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}