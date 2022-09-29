package com.company.oils.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.company.oils.Helper;
import com.company.oils.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.rpc.Help;
import com.google.type.DateTime;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Objects;

public class BuyActivity extends AppCompatActivity {

    String name;
    int index;
    int count;
    TextView countText;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        preferences = Helper.getPreferences();

        name = getIntent().getStringExtra("name");
        index = getIntent().getIntExtra("pos", -1);

        ((TextView)findViewById(R.id.name_tovar)).setText(name);
        ((TextView)findViewById(R.id.variant_tovar)).setText(Helper.getNames().get(count));
        countText = (TextView)findViewById(R.id.counts);

        Button buy = findViewById(R.id.buy);
        if(preferences.getString("name", null) == null){
            buy.setEnabled(true);
            findViewById(R.id.error).setVisibility(View.VISIBLE);
        }
        buy.setOnClickListener(this::Buy);
    }

    public void Plus(View w){
        count++;
        countText.setText(count + "");
    }

    public void Minus(View w){
        count--;
        if(count == 0){
            count++;
        }
        countText.setText(count + "");
    }

    public void Buy(View w){
        HashMap<String, Object> data = new HashMap<>();
        data.put("count", count);
        data.put("date", DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDateTime.now()));
        data.put("fio", preferences.getString("name", null));
        data.put("product", name);
        data.put("status", 0);
        data.put("variant", Helper.getNames().get(index));
        Helper.getFirebaseFirestore().collection("buy").add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Товар успешно добавлен", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Товар не добавлен", Toast.LENGTH_LONG).show();
                    }
                });
    }
}