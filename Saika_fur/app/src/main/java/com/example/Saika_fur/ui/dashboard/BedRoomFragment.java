package com.example.Saika_fur.ui.dashboard;

import android.app.Activity;
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
import com.example.Saika_fur.databinding.FragmentBedRoomBinding;
import com.example.Saika_fur.models.FurnitureModel;

import java.util.ArrayList;
import java.util.List;


public class BedRoomFragment extends Fragment {
    private FragmentBedRoomBinding binding;
    List<FurnitureModel> list_bedroom = new ArrayList<>();
    MAdapter adapter;
    NavController navController;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentBedRoomBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        createList();
        adapter = new MAdapter();
        adapter.setList_m(list_bedroom);
        binding.rvBedRoom.setAdapter(adapter);
        return root;
    }

    private void createList() {
        list_bedroom.add(new FurnitureModel("bed_room","Кровать Flame", "1690",
                "Кровать апапапв размер 2.6м х 2.4м производство Турция," +
                        " матрас набивной пружинный, кокосовая стружка", R.drawable.bed));
        list_bedroom.add(new FurnitureModel("bed_room","Кровать Wella", "1100",
                " производствпаавапо Италия, размер 2.6м х 2.4м" + " Mario Fabric " + "отделка: натуральнаая кожа  и гобелен," +
                        "набивной, специальный состав"
                , R.drawable.bedd));
        list_bedroom.add(new FurnitureModel("bed_room","Кровать Plot", "1340",
                " производавапвапство Италия, раавпвпапавпзмер 2.2м х 2.15м" + " Riotello " + "отделка: хлопок и гобелен," +
                        "набивной, специальный состав"
                , R.drawable.bed_super_stable));
        list_bedroom.add(new FurnitureModel("bed_room","Кровать Parlавпвапапавament", "1200",
                " производство Италия, размеравппвап 2.2м х 2.4м" + "Mario Fabric " + "отделка: хлопок и атлас," +
                        "набивной, специальный составааппвв"
                , R.drawable.bad_red_flame));
        list_bedroom.add(new FurnitureModel("bed_room","Кровать Flame", "1690",
                "аваарппар" +
                        " матрас апр ап, кокосовая стружкаававпа", R.drawable.bed));
        list_bedroom.add(new FurnitureModel("bed_room","Кровать Wella", "1100",
                " пвапап Италия, размер 2.6м х 2.4м" + " Mario Fabric " + "отделка: натуральнаая кожа  и гобелен," +
                        "набивной, специальный состав"
                , R.drawable.bedd));
        list_bedroom.add(new FurnitureModel("bed_room","Кровать Plot", "1340",
                " произвапаводство Италия, размер 2.2м х 2.15м" + " Riotello " + "отделка: хлопок и гобелен," +
                        "набивной, специальный состав"
                , R.drawable.bed_super_stable));
        list_bedroom.add(new FurnitureModel("bed_room","Кровать Parlament", "1200",
                " производство Италапавпвапия, размер 2.2м х 2.4м" + "Mario Fabric " + "отделка: хлопок и атлас," +
                        "набивной, специальный состав"
                , R.drawable.bed));

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnBack.setOnClickListener(v->{
            navController = Navigation.findNavController(requireActivity(),
                    R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.action_navigation_bed_room_to_navigation_home);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}