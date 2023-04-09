package com.example.freshfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshfood.Details;
import com.example.freshfood.R;
import com.example.freshfood.model.SearchFood;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    List<SearchFood> searchFoodList;
    Context context;

    public SearchAdapter(List<SearchFood> searchFoodList, Context context) {
        this.searchFoodList = searchFoodList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SearchAdapter.ViewHolder holder, int position) {
        SearchFood searchFood = searchFoodList.get(position);
        holder.name.setText(searchFood.getTenhang());
        holder.price.setText(searchFood.getDongia());
        Picasso.get().load(searchFood.getImage()).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Details.class);
                intent.putExtra("name",searchFood.getTenhang());
                intent.putExtra("image",searchFood.getImage());
                intent.putExtra("price",searchFood.getDongia());
                intent.putExtra("describe",searchFood.getMota());
                intent.putExtra("id",searchFood.getId());
                context.startActivities(new Intent[]{intent});
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchFoodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name,price;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img_s);
            name = itemView.findViewById(R.id.name_s);
            price = itemView.findViewById(R.id.price_s);
        }
    }
}
