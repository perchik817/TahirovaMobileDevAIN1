package com.example.Saika_fur.ui.sale;

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
import com.example.Saika_fur.databinding.FragmentSaleBinding;
import com.example.Saika_fur.models.CategoryModel;
import com.example.Saika_fur.models.FurnitureModel;

import java.util.ArrayList;
import java.util.List;


public class SaleFragment extends Fragment {

    FragmentSaleBinding binding;
    NavController navController;
    List<FurnitureModel> list_s = new ArrayList<>();

    MAdapter adapter;

    public SaleFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSaleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        createList();
        adapter = new MAdapter();
        adapter.setList_m(list_s);
        binding.rvSale.setAdapter(adapter);
        return root;
    }




    private void createList() {
        list_s.add(new FurnitureModel("zal","Мягкая мебель Трио Супер Стиль", "2820",
                " производство Италия," + " Mario Fabric " + "отделка: атлас и гобелен," +
                        "набивной, специальный состав", R.drawable.sofa_yellow));
        list_s.add(new FurnitureModel("zal","Диван Flow" ,
                "860", "Диван двухстворчатый раскладной" +
                " производство Германия, матрас набивной пружинный, кокосовая стружка", R.drawable.sofa_blu_charm));
        list_s.add(new FurnitureModel("zal","Диван Flow" ,
                "860", "Диван двухстворчатый раскладной" +
                " производство Германия, матрас набивной пружинный, кокосовая стружка", R.drawable.sofa_yellow));
        list_s.add(new FurnitureModel("zal","Диван Flow" ,
                "860", "Диван двухстворчатый раскладной" +
                " производство Германия, матрас набивной пружинный, кокосовая стружка", R.drawable.sofa_beige));
        list_s.add(new FurnitureModel("zal","Диван Flow" ,
                "860", "Диван двухстворчатый раскладной" +
                " производство Германия, матрас набивной пружинный, кокосовая стружка", R.drawable.sofa_trio_divia_dream));
        list_s.add(new FurnitureModel("zal","Диван Flow" ,
                "860", "Диван двухстворчатый раскладной" +
                " производство Германия, матрас набивной пружинный, кокосовая стружка", R.drawable.sofa_set_trio_martin));
        list_s.add(new FurnitureModel("zal","Диван Flow" ,
                "860", "Диван двухстворчатый раскладной" +
                " производство Германия, матрас набивной пружинный, кокосовая стружка", R.drawable.sofa_blu_charm));
        list_s.add(new FurnitureModel("zal","Диван Flow" ,
                "860", "Диван двухстворчатый раскладной" +
                " производство Германия, матрас набивной пружинный, кокосовая стружка", R.drawable.sofa_yellow));


    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnBack.setOnClickListener(v -> {
            navController = Navigation.findNavController(requireActivity(),
                    R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.action_saleFragment_to_navigation_home);
        });

    }
}