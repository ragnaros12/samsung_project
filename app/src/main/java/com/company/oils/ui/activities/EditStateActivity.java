package com.company.oils.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.company.oils.Helper;
import com.company.oils.R;
import com.company.oils.core.models.Buy;
import com.company.oils.ui.adapters.HistoryAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class EditStateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_state);

        Context context = this;
        Helper.getFirebaseFirestore().collection("buy").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Buy> buys = new ArrayList<>();
                for (DocumentSnapshot snap : queryDocumentSnapshots.getDocuments()) {
                    String fio = snap.getString("fio");
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
                RecyclerView recyclerView = findViewById(R.id.list_edit);
                recyclerView.setAdapter(new HistoryAdapter(buys, context, true));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplication().getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}