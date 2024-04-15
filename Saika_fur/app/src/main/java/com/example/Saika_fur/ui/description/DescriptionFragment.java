package com.example.Saika_fur.ui.description;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.Saika_fur.R;
import com.example.Saika_fur.databinding.FragmentDescriptionBinding;
import com.example.Saika_fur.databinding.ItemDescCardBinding;
import com.example.Saika_fur.models.FurnitureModel;

import java.util.ArrayList;


public class DescriptionFragment extends Fragment {

    FragmentDescriptionBinding binding;

    ArrayList<FurnitureModel> d_list = new ArrayList<>();
    DescAdapter adapter;
    NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new DescAdapter();

        if (getArguments()!= null) {
            d_list = getArguments().getParcelableArrayList("favorite");

        }else {
            Toast.makeText(requireActivity(), " there are nothing", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDescriptionBinding.inflate(inflater,
                container, false);
        View root = binding.getRoot();

        return  root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (d_list!= null) {
            adapter.setListD(d_list);
        } else  {
            Toast.makeText(requireActivity(),
                    "Вы не выбрали", Toast.LENGTH_SHORT).show();
        }

        binding.rvDetailsCatalog.setAdapter(adapter);

        binding.btnBack.setOnClickListener(v2-> {
            navController = Navigation.findNavController(requireActivity(),
                    R.id.nav_host_fragment_activity_main);

            navController.navigate(R.id.action_descriptionFragment_to_navigation_home);
        });
    }
}