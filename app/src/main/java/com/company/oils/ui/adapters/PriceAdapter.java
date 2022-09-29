package com.company.oils.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.company.oils.R;
import com.company.oils.core.models.Oil;
import com.company.oils.ui.activities.BuyActivity;

import org.w3c.dom.Text;

import java.util.List;
import java.util.stream.Collectors;

import kotlin.jvm.internal.Lambda;

public class PriceAdapter extends RecyclerView.Adapter<PriceAdapter.PriceItem> {
    List<Oil> oils;
    List<String> names;
    Context context;
    LayoutInflater layoutInflater;

    public PriceAdapter(List<Oil> oils,List<String> names, Context context) {
        this.oils = oils;
        this.names = names;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PriceItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PriceItem(layoutInflater.inflate(R.layout.item_price, parent, false));
    }

    public static String doub(Double val){
        String NUMBER = val.toString();
        if(!NUMBER.contains(".")) {
            return NUMBER;
        }

        return NUMBER.replaceAll(".?0*$", "");
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PriceItem holder, int position) {

        if(position == 0){
            int i = 0;
            for (String key : names){
                holder.items[i].setText(key.replace("(", "\n("));
                holder.items[i].setTextSize(15);
                i++;
            }
            holder.top.setVisibility(View.GONE);
            holder.bottom.setVisibility(View.GONE);

            return;
        }
        holder.bottom.setVisibility(View.GONE);
        position--;
        holder.name.setText(oils.get(position).getName());
        int i = 0;
        for (Double key : oils.get(position).getVariants()){
            holder.items[i].setText(doub(key));
            holder.SetClick(i, oils.get(position).getName());
            i++;
        }
    }

    @Override
    public int getItemCount() {
        return oils.size() + 1;
    }

    class PriceItem extends RecyclerView.ViewHolder{
        TextView name;
        TextView[] items = new TextView[4];
        LinearLayout top, bottom;

        public PriceItem(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            top = itemView.findViewById(R.id.top_line);
            bottom = itemView.findViewById(R.id.bottom_line);
            items[0] = itemView.findViewById(R.id.item_price);
            items[1] = itemView.findViewById(R.id.item_price1);
            items[2] = itemView.findViewById(R.id.item_price2);
            items[3] = itemView.findViewById(R.id.item_price3);
        }

        public void SetClick(int iv, String name){
            items[iv].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, BuyActivity.class);
                    i.putExtra("name", name);
                    i.putExtra("pos", iv);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            });
        }
    }
}
