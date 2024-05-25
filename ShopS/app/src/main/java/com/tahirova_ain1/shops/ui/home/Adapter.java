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
import com.tahirova_ain1.shops.databinding.ProductItemBinding;
import com.tahirova_ain1.shops.models.ModelM;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    ProductItemBinding binding;
    Context context;
    List<ModelM> list;
    NavController navController;
    ArrayList<ModelM> descriptionList = new ArrayList<>();
    ArrayList<ModelM> selectedList = new ArrayList<>();

    public void setList(List<ModelM> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ProductItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
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
        ProductItemBinding binding;
        public ViewHolder(@NonNull ProductItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void onBind(ModelM modelM) {
            binding.productNameCard.setText(modelM.getTitle());
            binding.priceCard.setText(String.valueOf(modelM.getPrice()));
            binding.descriptionCard.setText(modelM.getDescription());

            Glide.with(context)
                    .load(list.get(getAdapterPosition()).getImage())
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.ic_launcher_background)
                    .into(binding.imageCard);

            binding.btnZoom.setOnClickListener(v -> {
                selectedList.add(modelM);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("see more", selectedList);
                navController = Navigation.findNavController((Activity) itemView.getContext(), R.id.nav_host);
                navController.navigate(R.id., bundle);
                Log.e("TAG", "pass data ! !");
            });

            itemView.setOnClickListener(v1 -> {
                if (binding.itemFavCheck.getVisibility() == View.INVISIBLE) {
                    binding.itemFavCheck.setVisibility(View.VISIBLE);
                    selectedList.add(modelM);
                } else {
                    binding.itemFavCheck.setVisibility(View.INVISIBLE);
                    selectedList.remove(modelM);
                }
            });
        }
    }

    public ArrayList<ModelM> getSelectedList() {
        return selectedList;
    }

}
