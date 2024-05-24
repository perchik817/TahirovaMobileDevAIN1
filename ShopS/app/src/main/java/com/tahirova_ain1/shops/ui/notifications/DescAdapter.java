package com.tahirova_ain1.shops.ui.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tahirova_ain1.shops.models.ModelM;

import java.util.ArrayList;

public class DescAdapter extends RecyclerView.Adapter<DescAdapter.ViewHolder> {

     binding;
    Context context;
    List<ModelM> listD = new ArrayList<>();

    public DescAdapter(Context context, List<ModelM> listD) {
        this.context = context;
        this.listD = listD;
    }

    public void setListD(List<ModelM> listD) {
        this.listD = listD;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemDescBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(listD.get(position));
    }

    @Override
    public int getItemCount() {
        return listD.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull ItemDescBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind(ModelM modelM) {
            binding.nameCard.setText(modelM.getModelTitle());
            binding.priceCard.setText(String.valueOf(modelM.getModelPrice()));
            binding.descriptionCard.setText(modelM.getModelDescription());
            // Для изображения возможно потребуется загрузка с помощью Glide или другой библиотеки
            Glide.with(itemView.getContext()).load(modelM.getModelImage()).into(binding.imageCard);
        }
    }
}
