package com.example.freshfood.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshfood.R;
import com.example.freshfood.database;
import com.example.freshfood.model.CartFood;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    List<CartFood> cartList;

    public CartAdapter(Context context, List<CartFood> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cart_row_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartFood cart = cartList.get(position);
        Picasso.get().load(cart.getImage()).into(holder.imageView);
        holder.name.setText(cart.getTenhang());
        holder.price.setText(cart.getDongia());
        holder.amount.setText(cart.getSoluong());
        String id = cart.getId();
        holder.delete.setOnClickListener(v -> {
            delete(id, position);

        });

    }

    private void delete(String id, int p) {
        database db = new database(context,"freshfood.sqlite",null,1);
        db.QueryData("DELETE FROM cart WHERE id_sanpham = '"+id+"'");
        Toast.makeText(context,"Delete id: "+id,Toast.LENGTH_SHORT).show();
        cartList.remove(p);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView,delete;
        private TextView name,price,amount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageCart);
            name = itemView.findViewById(R.id.nameFood);
            price = itemView.findViewById(R.id.priceFood);
            amount = itemView.findViewById(R.id.amount);
            delete = itemView.findViewById(R.id.Delete_cart);
        }
    }
}
