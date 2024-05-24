package com.tahirova_ain1.shops.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.tahirova_ain1.shops.R;
import com.tahirova_ain1.shops.databinding.FragmentBasketBinding;
import com.tahirova_ain1.shops.databinding.FragmentDashboardBinding;
import com.tahirova_ain1.shops.models.ModelM;
import com.tahirova_ain1.shops.models.Order;

import java.util.ArrayList;

public class BasketFragment extends Fragment {

    private FragmentBasketBinding binding;
    private ArrayList<ModelM> basketProducts;
    BasketAdapter basketAdapter;
    NavController navController;
    String userAddress, userName;
    ArrayList<Order> orders;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentBasketBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if(getArguments() != null){
            basketProducts = new ArrayList<>();
            basketProducts = getArguments().getParcelableArrayList("basket");
        }
        if(basketProducts != null){
            binding.place_holder.setVisibillity(View.GONE);
            basketAdapter = new BasketAdapter(requireActivity(), basketProducts);
            binding.rv_basket.setAdapter(basketAdapter);
        } else {
            binding.place_holder.setVisibillity(View.VISIBLE);
        }

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnMakeOrder.setOnClickListener(v1 ->{
           if (basketProducts != null){
               if(!(binding.edit_user_address.getText().toString().isEmpty()) &&
                       !(binding.edit_user_name.getText().toString().isEmpty())){
                   userAddress = binding.edit_user_address.getText().toString();
                   userName = binding.edit_user_name.getText().toString();
                   orders = new ArrayList<>();
                   try{
                       for (int i = 0; i < basketProducts.size(); i++) {
                           orders.add(new Order(userName,
                                   userAddress,
                                   basketProducts.get(i).getImage(),
                                   basketProducts.get(i).getTitle(),
                                   String.valueOf(basketProducts.get(i).getPrice()),
                                   basketProducts.get(i).getCounterProduct()));

                       }
                   } catch (NullPointerException ex){
                       Toast.makeText(requireActivity(),  "Choose product", Toast.LENGTH_SHORT).show();
                   }
               } else {
                   Toast.makeText(requireActivity(), "Enter your name", Toast.LENGTH_SHORT).show();
               }

               Bundle bundle = new Bundle();
               bundle.getParcelableArrayList("payed", orders);
           } else {
               Toast.makeText(requireActivity(), "Choose product", Toast.LENGTH_SHORT).show();
           }
           try {
               binding.tvAnswer.setText("your order is in obrabotka, delivery 4656. \nOrder after payment \n");
           } catch (NullPointerException e){
               binding.btnPay.setVisibility(View.INVISIBLE);
               binding.tvAnswer.setText("you haven't choose any items, so to make order, please, go back to catalog and make order");
           }

           if(!(binding.tvAnswer.getText().toString().isEmpty())){
               binding.btnMakeOrder.setVisibility(View.INVISIBLE);
               binding.btnPay.setVisibility(View.VISIBLE);
           }
        });

        binding.btnPay.setOnClickListener(v2 -> {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.basket_to_payment);
            binding.btnBack.setOnClickListener(v3 -> {
                navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.basket_to_home);
            });
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}