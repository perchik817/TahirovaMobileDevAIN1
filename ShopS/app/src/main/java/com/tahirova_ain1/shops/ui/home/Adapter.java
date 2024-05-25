package com.tahirova_ain1.shops.ui.home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tahirova_ain1.shops.R;
import com.tahirova_ain1.shops.databinding.ItemOrderBinding;
import com.tahirova_ain1.shops.models.ModelM;
import com.tahirova_ain1.shops.models.Order;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    ItemOrderBinding binding;
    Context context;
    List<Order> list;
    NavController navController;
    ArrayList<Order> descriptionList = new ArrayList<>();
    ArrayList<Order> selectedList = new ArrayList<>();

    public void setList(List<Order> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemOrderBinding binding;
        public ViewHolder(@NonNull ItemOrderBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void onBind(Order order) {
            binding.productNameCard.setText(order.getNameProduct());
            binding.priceCard.setText(String.valueOf(order.getPriceProduct()));

            Glide.with(context)
                    .load(list.get(getAdapterPosition()).getImage())
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.ic_launcher_background)
                    .into(binding.imageCard);

            binding.btnZoom.setOnClickListener(v -> {
                selectedList.add(order);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("see more", selectedList);
                navController = Navigation.findNavController((Activity) itemView.getContext(), R.id.nav_host);
                navController.navigate(R.id.action_navigation_home_to_navigation_description, bundle);
                Log.e("TAG", "pass data ! !");
            });

            itemView.setOnClickListener(v1 -> {
                if (binding.itemFavCheck.getVisibility() == View.INVISIBLE) {
                    binding.itemFavCheck.setVisibility(View.VISIBLE);
                    selectedList.add(order);
                } else {
                    binding.itemFavCheck.setVisibility(View.INVISIBLE);
                    selectedList.remove(order);
                }
            });
        }
    }

    public ArrayList<Order> getSelectedList() {
        return selectedList;
    }

}
