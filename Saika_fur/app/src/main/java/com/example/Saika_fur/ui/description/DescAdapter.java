package com.example.Saika_fur.ui.description;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Saika_fur.databinding.ItemDescCardBinding;
import com.example.Saika_fur.models.FurnitureModel;

import java.util.ArrayList;
import java.util.List;

public class DescAdapter extends RecyclerView.Adapter<DescAdapter.ViewHolder> {

    ItemDescCardBinding binding;

    List<FurnitureModel> listD = new ArrayList<>();

    public void setListD(List<FurnitureModel> listD) {
        this.listD = listD;
    }

    @NonNull
    @Override
    public DescAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemDescCardBinding
                .inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DescAdapter.ViewHolder holder, int position) {
        holder.onBind(listD.get(position));
    }

    @Override
    public int getItemCount() {
        return listD.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull ItemDescCardBinding itemView) {
            super(itemView.getRoot());

        }

        public void onBind(FurnitureModel furnitureModel) {
            binding.nameCard.setText(furnitureModel.getTitle());
            binding.priceCard.setText(String.valueOf(furnitureModel.getPrice()));
            binding.descriptionCard.setText(furnitureModel.getDescription());
            binding.imageCard.setImageResource(furnitureModel.getImageInt());
        }
    }
}
