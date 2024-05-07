package com.tahirova_ain1.boredapi.ui.board;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.tahirova_ain1.boredapi.Prefs;
import com.tahirova_ain1.boredapi.R;
import com.tahirova_ain1.boredapi.databinding.ItemBoardBinding;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {
    ItemBoardBinding binding;
    NavController navController;
    private  int[] images = new int[]{
            R.drawable.for_world,
            R.drawable.for_earth,
            R.drawable.trophy};

    private  String[] titles = new String[]{
            "What have you done for the world? ",
            "What have you done for the ecology? ",
            "What have you done for yourself?"};
    @NonNull
    @Override
    public BoardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemBoardBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull ItemBoardBinding itemView) {
            super(itemView.getRoot());
        }

        public void bind(int position) {
            binding.textBoard.setText(titles[position]);
            binding.imageBoard.setImageResource(images[position]);
            if(position==titles.length-1){
                binding.btnStart.setVisibility(View.VISIBLE);
            }else {
                binding.btnStart.setVisibility(View.VISIBLE);
            }
            binding.btnStart.setOnClickListener(v->{
                new Prefs((Activity) itemView.getContext()).saveBoardState();

                navController = Navigation.findNavController((Activity) itemView.getContext(), R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.action_navigation_notifications_to_navigation_home);
            });
        }
    }
}