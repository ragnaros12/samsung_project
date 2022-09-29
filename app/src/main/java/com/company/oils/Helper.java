package com.company.oils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.company.oils.core.models.Oil;
import com.company.oils.core.models.OilContainer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Helper {
    private static FirebaseFirestore firebaseFirestore;
    private static List<String> names = new ArrayList<>();
    private static List<Oil> oils = new ArrayList<>();
    private static Integer id = 0;
    private static SharedPreferences preferences;


    public static SharedPreferences getPreferences() {
        return preferences;
    }

    public static void Init(Context context, Supplier<Boolean> supplier){
        FirebaseApp.initializeApp(context);
        firebaseFirestore = FirebaseFirestore.getInstance();

        preferences = context.getSharedPreferences("name", Context.MODE_PRIVATE);

        firebaseFirestore.collection("tovars").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots1) {
                        names = (List<String>)queryDocumentSnapshots1.getDocuments().get(0).getData().get("tovars");
                        id++;
                        if(id == 2){
                            supplier.get();
                        }
                    }
                });

        firebaseFirestore.collection("prices").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                for (DocumentSnapshot snapshot : documentSnapshots.getDocuments()){
                    List<Double> i = new ArrayList<>();
                    List ints = (List<Object>) snapshot.getData().get("variants");
                    assert ints != null;
                    for (Object key : ints){
                        i.add(Double.parseDouble(key.toString()));
                    }
                    oils.add(new Oil(snapshot.getString("name"), i));
                }
                id++;
                if(id == 2){
                    supplier.get();
                }
            }
        });

    }

    public static List<Oil> getOils() {
        return oils;
    }

    public static List<String> getNames() {
        return names;
    }

    public static FirebaseFirestore getFirebaseFirestore() {
        return firebaseFirestore;
    }
}
