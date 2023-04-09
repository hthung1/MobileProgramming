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
import com.example.freshfood.model.PopularFood;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PopularFoodAdapter extends RecyclerView.Adapter<PopularFoodAdapter.ViewHolder> {
    List<PopularFood> popularFoodList;
    Context context;

    public PopularFoodAdapter(List<PopularFood> popularFoodList, Context context) {
        this.popularFoodList = popularFoodList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.popular_food_row_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PopularFood food = popularFoodList.get(position);
        Picasso.get().load(food.getImage()).into(holder.image);
        holder.name.setText(food.getTenhang());
        holder.price.setText(food.getDongia());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Details.class);
                intent.putExtra("image",food.getImage());
                intent.putExtra("name",food.getTenhang());
                intent.putExtra("price",food.getDongia());
                intent.putExtra("describe",food.getMota());
                intent.putExtra("id",food.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularFoodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView price,name;
        private ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.priceFood);
            name = itemView.findViewById(R.id.nameFood);
            image = itemView.findViewById(R.id.imageFood);
        }
    }
}
