package com.tahirova_ain1.shop.ui.home;

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
import com.tahirova_ain1.shop.R;
import com.tahirova_ain1.shop.databinding.ItemProductBinding;
import com.tahirova_ain1.shop.models.ModelM;

import java.util.ArrayList;
import java.util.List;

public class JemAdapter  extends RecyclerView.Adapter<JemAdapter.ViewHolder> {
    ItemProductBinding binding;
    Context context;
    List<ModelM> list;
    ArrayList<ModelM> selectedList = new ArrayList<>();
    ArrayList<ModelM> selectedIntoBasketList = new ArrayList<>();
    NavController navController;

    public JemAdapter(Context context, List<ModelM> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public JemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull JemAdapter.ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    public ArrayList<ModelM> getSelectedIntoBasketList() {
        return selectedIntoBasketList;
    }

    public Double getItemPrice(int position){
        return  list.get(position).getPrice();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemProductBinding binding;

        public ViewHolder(@NonNull ItemProductBinding itemView) {
            super(itemView.getRoot());
            this.binding  = itemView;
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
            binding.btnZoom.setOnClickListener(v->{
                selectedList.add(modelM);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("see more", selectedList);
                navController = Navigation.findNavController((Activity) itemView.getContext(), R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.navigation_description, bundle);
                Log.e("TAG", "pass data to description.");
            });

            itemView.setOnClickListener(v1->{
                if(binding.itemFavCheck.getVisibility() == View.INVISIBLE){
                    binding.itemFavCheck.setVisibility(View.VISIBLE);
                    selectedIntoBasketList.add(modelM);
                }
                else {
                    binding.itemFavCheck.setVisibility(View.INVISIBLE);
                    selectedIntoBasketList.remove(modelM);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
