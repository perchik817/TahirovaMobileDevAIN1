package com.tahirova_ain1.shopsstock.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tahirova_ain1.shopsstock.databinding.ItemUserStockBinding;
import com.tahirova_ain1.shopsstock.models.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    ItemUserStockBinding binding;
    Context context;
    ArrayList<User> list;

    public UserAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemUserStockBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemUserStockBinding binding;
        public ViewHolder(@NonNull ItemUserStockBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void onBind(User user) {
            binding.nameUser.setText(user.getName());
            binding.emailUser.setText(user.getEmail());
        }
    }
}
