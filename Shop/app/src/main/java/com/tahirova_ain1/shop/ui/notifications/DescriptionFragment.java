package com.tahirova_ain1.shop.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.tahirova_ain1.shop.R;
import com.tahirova_ain1.shop.databinding.DescriptionFragmentBinding;
import com.tahirova_ain1.shop.models.ModelM;

import java.util.ArrayList;

public class DescriptionFragment extends Fragment {

    private DescriptionFragmentBinding binding;
    NavController navController;
    DescAdapter adapter;
    ArrayList<ModelM> dList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DescriptionViewModel descriptionViewModel =
                new ViewModelProvider(this).get(DescriptionViewModel.class);

        binding = DescriptionFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (getArguments() != null) {
            dList = getArguments().getParcelableArrayList("see more");
        } else {
            Toast.makeText(requireActivity(), " There are nothing", Toast.LENGTH_SHORT).show();
        }

        adapter = new DescAdapter(requireActivity(), dList);
        binding.rvDescription.setAdapter(adapter);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnBack.setOnClickListener(v -> {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.action_navigation_description_to_navigation_home);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}