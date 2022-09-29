package com.company.oils.ui.fragments.admin;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.function.Consumer;

public class AdminViewModel extends AndroidViewModel {
    private FirebaseAuth auth;

    public AdminViewModel(@NonNull Application application) {
        super(application);
        auth = FirebaseAuth.getInstance();
    }

    public void login(String login, String password, Consumer<Boolean> state){
        auth.signInWithEmailAndPassword(login, password)
                .addOnSuccessListener((a) -> state.accept(true))
                .addOnFailureListener((a) -> state.accept(false));
    }
}