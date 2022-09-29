package com.company.oils.ui.fragments.history;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.company.oils.Helper;
import com.company.oils.core.models.Buy;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class HistoryViewModel extends AndroidViewModel {
    SharedPreferences preferences;

    public HistoryViewModel(Application application) {
        super(application);
        preferences = Helper.getPreferences();
    }

    public String getName() {
        return preferences.getString("name", null);
    }

    public void GetData(String name, Consumer<List<Buy>> supplier) {
        Helper.getFirebaseFirestore().collection("buy").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Buy> buys = new ArrayList<>();
                for (DocumentSnapshot snap : queryDocumentSnapshots.getDocuments()) {
                    String fio = snap.getString("fio");
                    if (fio.equals(name)) {
                        buys.add(new Buy(
                                fio,
                                snap.getString("date"),
                                snap.getLong("count"),
                                snap.getString("product"),
                                snap.getLong("status"),
                                snap.getString("variant"),
                                snap.getId()
                        ));
                    }
                }
                supplier.accept(buys);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplication().getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}