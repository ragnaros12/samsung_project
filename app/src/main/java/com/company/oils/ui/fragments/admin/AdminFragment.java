package com.company.oils.ui.fragments.admin;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.company.oils.databinding.FragmentAdminBinding;
import com.company.oils.ui.activities.EditStateActivity;

import java.util.function.Consumer;

public class AdminFragment extends Fragment {

    private FragmentAdminBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AdminViewModel adminViewModel =
                new ViewModelProvider(this).get(AdminViewModel.class);


        binding = FragmentAdminBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.enter.setOnClickListener(v -> adminViewModel.login(
                binding.enterEmail.getText().toString(),
                binding.enterPassword.getText().toString(),
                aBoolean -> {
                    if(aBoolean){
                        binding.loginLayout.setVisibility(View.GONE);
                        binding.layoutCheck.setVisibility(View.VISIBLE);
                        Toast.makeText(getContext(), "Вход успешен", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getContext(), "Данные не верны", Toast.LENGTH_LONG).show();
                    }
                }
        ));

        binding.editTovar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), EditStateActivity.class);
                startActivity(i);
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