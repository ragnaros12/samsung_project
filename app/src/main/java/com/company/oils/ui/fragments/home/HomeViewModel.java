package com.company.oils.ui.fragments.home;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.company.oils.Helper;
import com.company.oils.core.models.Oil;
import com.company.oils.core.models.OilContainer;
import com.company.oils.ui.adapters.PriceAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.rpc.Help;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class HomeViewModel extends AndroidViewModel {


    FirebaseFirestore firebaseFirestore;

    @SuppressLint("StaticFieldLeak")
    Context context;

    public HomeViewModel(Application application) {
        super(application);
        context = application.getApplicationContext();
        FirebaseApp.initializeApp(context);
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void get(Consumer<OilContainer> cons){
        cons.accept(new OilContainer(Helper.getOils(), Helper.getNames()));
    }
}