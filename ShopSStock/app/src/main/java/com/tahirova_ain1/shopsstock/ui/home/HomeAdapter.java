package com.tahirova_ain1.shopsstock.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tahirova_ain1.shopsstock.databinding.ItemOrderStockBinding;
import com.tahirova_ain1.shopsstock.models.Order;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    ItemOrderStockBinding binding;
    Context context;
    ArrayList<Order> list;

    public HomeAdapter(Context context, ArrayList<Order> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemOrderStockBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemOrderStockBinding binding;
        public ViewHolder(@NonNull ItemOrderStockBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
        public void onBind(Order order){
            binding.nameUser.setText(order.getNameUser());
            binding.nameProductCard.setText(order.getNameProduct());
            binding.priceCard.setText(order.getPriceProduct());
            binding.addressUser.setText(order.getAddressUser());
            binding.productsCounter.setText(order.getProductCount());
            Picasso.get().load(order.getImage()).into(binding.imageCard);
        }
    }
}
