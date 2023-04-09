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
import com.example.freshfood.model.AsiaFood;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AsiaFoodAdapter extends RecyclerView.Adapter<AsiaFoodAdapter.ViewHolder> {
    List<AsiaFood> asiaFoodList;
    Context context;

    public AsiaFoodAdapter(List<AsiaFood> asiaFoodList, Context context) {
        this.asiaFoodList = asiaFoodList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.asia_food_row_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AsiaFood asiaFood = asiaFoodList.get(position);
        holder.name.setText(asiaFood.getTenhang());
        holder.price.setText(asiaFood.getDongia());
        Picasso.get().load(asiaFood.getImage()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Details.class);
                intent.putExtra("image",asiaFood.getImage());
                intent.putExtra("name",asiaFood.getTenhang());
                intent.putExtra("price",asiaFood.getDongia());
                intent.putExtra("describe",asiaFood.getMota());
                intent.putExtra("id",asiaFood.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return asiaFoodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name,price,restaurant,rating;
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameFood);
            price = itemView.findViewById(R.id.priceFood);
            imageView = itemView.findViewById(R.id.imageFood);
            restaurant = itemView.findViewById(R.id.restaurant_name);
            rating = itemView.findViewById(R.id.rating);

        }
    }
}
