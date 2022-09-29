package com.company.oils.ui.fragments.profile;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.company.oils.Helper;

public class ProfileViewModel extends AndroidViewModel {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        preferences = Helper.getPreferences();
        editor = preferences.edit();
    }

    public void save(String name, String phone, String email){
        editor.putString("name", name);
        editor.putString("phone", phone);
        editor.putString("email", email);

        editor.apply();
    }

    public String getName(){
        return preferences.getString("name", "");
    }

    public String getPhone(){
        return preferences.getString("phone", "");
    }

    public String getEmail(){
        return preferences.getString("email", "");
    }
}