package com.example.Saika_fur.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.example.Saika_fur.MAdapter;
import com.example.Saika_fur.R;
import com.example.Saika_fur.databinding.FragmentZalBinding;
import com.example.Saika_fur.models.FurnitureModel;

import java.util.ArrayList;
import java.util.List;

public class ZalFragment extends Fragment {
    private FragmentZalBinding binding;

    List<FurnitureModel> list_zal = new ArrayList<>();
    MAdapter adapter;

NavController navController;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentZalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        createList();
        adapter = new MAdapter();
        adapter.setList_m(list_zal);
        binding.rvZalRoom.setAdapter(adapter);
        return root;
    }

    private void createList() {
        list_zal.add(new FurnitureModel("zal","Мягкая мебель Трио Супер Стиль", "2820",
                " производство Италия," + " Mario Fabric " + "отделка: атлас и гобелен," +
                        "набивной, специальный состав", R.drawable.sofa_yellow));
        list_zal.add(new FurnitureModel("zal","Диван Flow" ,
                "860", "Диван двухстворчатый раскладной" +
                " производство Германия, матрас набивной пружинный, кокосовая стружка", R.drawable.sofa_blu_charm));
        list_zal.add(new FurnitureModel("zal","Диван Flow" ,
                "860", "Диван двухстворчатый раскладной" +
                " производство Германия, матрас набивной пружинный, кокосовая стружка", R.drawable.sofa_yellow));
        list_zal.add(new FurnitureModel("zal","Диван Flow" ,
                "860", "Диван двухстворчатый раскладной" +
                " производство Германия, матрас набивной пружинный, кокосовая стружка", R.drawable.sofa_beige));
        list_zal.add(new FurnitureModel("zal","Диван Flow" ,
                "860", "Диван двухстворчатый раскладной" +
                " производство Германия, матрас набивной пружинный, кокосовая стружка", R.drawable.sofa_trio_divia_dream));
        list_zal.add(new FurnitureModel("zal","Диван Flow" ,
                "860", "Диван двухстворчатый раскладной" +
                " производство Германия, матрас набивной пружинный, кокосовая стружка", R.drawable.sofa_set_trio_martin));
        list_zal.add(new FurnitureModel("zal","Диван Flow" ,
                "860", "Диван двухстворчатый раскладной" +
                " производство Германия, матрас набивной пружинный, кокосовая стружка", R.drawable.sofa_blu_charm));
        list_zal.add(new FurnitureModel("zal","Диван Flow" ,
                "860", "Диван двухстворчатый раскладной" +
                " производство Германия, матрас набивной пружинный, кокосовая стружка", R.drawable.sofa_yellow));

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

            binding.btnBack.setOnClickListener(v->{
                navController = Navigation.findNavController(requireActivity(),
                        R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.action_navigation_zal_to_navigation_home);
            });

          

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}