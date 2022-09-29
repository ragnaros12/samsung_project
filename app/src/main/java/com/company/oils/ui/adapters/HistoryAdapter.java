package com.company.oils.ui.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.company.oils.Helper;
import com.company.oils.R;
import com.company.oils.core.models.Buy;
import com.company.oils.core.models.Status;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {
    List<Buy> buys;
    Context context;
    LayoutInflater layoutInflater;
    boolean isClick;

    public HistoryAdapter(List<Buy> buys, Context context, boolean isClick) {
        this.buys = buys;
        this.isClick = isClick;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryHolder(layoutInflater.inflate(R.layout.item_history, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
        Buy buy = buys.get(position);
        holder.characters.setText(buy.getProduct() + " - " + buy.getVariant() + "x" + buy.getCount());
        holder.status.setTextColor(buy.getStatus().getColor());
        holder.status.setText(buy.getStatus().getText());


        if(isClick){
            holder.itemView.findViewById(R.id.clicker).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(context).setTitle("Выберите значение")
                            .setItems(
                                    Arrays.stream(Status.values()).map(Status::getText).toArray(String[]::new),
                                    (dialog, which) -> {
                                        Helper.getFirebaseFirestore().collection("buy").document(buy.getDocId())
                                                .update("status", which).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Toast.makeText(v.getContext(), "Успешно сохранено", Toast.LENGTH_LONG).show();
                                                        buy.setStatus(Status.values()[which]);
                                                        notifyDataSetChanged();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(v.getContext(), "Неудачно", Toast.LENGTH_LONG).show();
                                                    }
                                                });
                                    }
                            ).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return buys.size();
    }

    class HistoryHolder extends RecyclerView.ViewHolder{
        TextView characters;
        TextView status;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            characters = itemView.findViewById(R.id.characters_history);
            status = itemView.findViewById(R.id.status_history);
        }
    }
}
