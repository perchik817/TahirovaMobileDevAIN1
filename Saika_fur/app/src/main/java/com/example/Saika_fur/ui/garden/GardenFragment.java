package com.example.Saika_fur.ui.garden;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Saika_fur.MAdapter;
import com.example.Saika_fur.R;
import com.example.Saika_fur.databinding.FragmentGardenBinding;
import com.example.Saika_fur.models.FurnitureModel;

import java.util.ArrayList;
import java.util.List;

public class GardenFragment extends Fragment {

    FragmentGardenBinding binding;
    NavController navController;
    List<FurnitureModel> list_g = new ArrayList<>();
    MAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      binding = FragmentGardenBinding
              .inflate(inflater,container,false);
      View root = binding.getRoot();
      createList();
      adapter = new MAdapter();
      adapter.setList_m(list_g);
      binding.rvGarden.setAdapter(adapter);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnBack.setOnClickListener(v->{
            navController = Navigation.findNavController(requireActivity(),
                    R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.action_gardenFragment_to_navigation_home);

        });

    }
    public void createList(){
        list_g.add(new FurnitureModel("Садовая мебель", "Cтол и стулья ", "980",
                "материал - ротанговое дерево", "40%",R.drawable.garden_furn));
        list_g.add(new FurnitureModel("Садовая мебель", "стулья ", "380",
                "материал - ротанговое дерево", "60%",R.drawable.garden_furn));
        list_g.add(new FurnitureModel("Садовая мебель", "Кресло ", "280",
                "материал - ротанговое дерево", "55%",R.drawable.garden_furn));
        list_g.add(new FurnitureModel("Садовая мебель", "Cтол", "600",
                "материал - ротанговое дерево", "60%",R.drawable.garden_furn));
        list_g.add(new FurnitureModel("Садовая мебель", "Cтол и стулья ", "780",
                "материал - ротанговое дерево", "45%",R.drawable.garden_furn));
        list_g.add(new FurnitureModel("Садовая мебель", "Cтол и стулья ", "800",
                "материал - ротанговое дерево", "60%",R.drawable.garden_furn));
        list_g.add(new FurnitureModel("Садовая мебель", "Cтол", "660",
                "материал - ротанговое дерево", "70%",R.drawable.garden_furn));
        list_g.add(new FurnitureModel("Садовая мебель", "стулья ", "390",
                "материал - ротанговое дерево", "60%",R.drawable.garden_furn));
    }
}