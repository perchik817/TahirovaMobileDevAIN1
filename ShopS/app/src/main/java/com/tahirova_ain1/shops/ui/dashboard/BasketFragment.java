package com.tahirova_ain1.shops.ui.dashboard;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.tahirova_ain1.shops.R;
import com.tahirova_ain1.shops.databinding.FragmentBasketBinding;
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
    String tokenN;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentBasketBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if(getArguments() != null){
            basketProducts = new ArrayList<>();
            basketProducts = getArguments().getParcelableArrayList("basket");
            tokenN = getArguments().getString("key_token");
        }
        if(basketProducts != null){
            binding.placeHolder.setVisibility(View.GONE);
            basketAdapter = new BasketAdapter(requireActivity(), basketProducts);
            binding.rvBasket.setAdapter(basketAdapter);
        } else {
            binding.placeHolder.setVisibility(View.VISIBLE);
        }

        return root;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnMakeOrder.setOnClickListener(v1 ->{
           if (basketProducts != null){
               if(!binding.editUserAddress.getText().toString().isEmpty() &&
                       !binding.editUserName.getText().toString().isEmpty() &&
                       !binding.placeCounter.getText().toString().isEmpty()){
                   userAddress = binding.editUserAddress.getText().toString();
                   userName = binding.editUserName.getText().toString();
                   int counterProd = Integer.parseInt(binding.placeCounter.getText().toString());
                   orders = new ArrayList<>();
                   try{
                       for (int i = 0; i < basketProducts.size(); i++) {
                           orders.add(new Order(
                                   userName,
                                   userAddress,
                                   basketProducts.get(i).getImage(),
                                   basketProducts.get(i).getTitle(),
                                   String.valueOf(basketProducts.get(i).getPrice()),
//                                   basketProducts.get(i).getCounterProduct()
                                   counterProd
                           ));

                       }
                   } catch (NullPointerException ex){
                       Toast.makeText(requireActivity(),  "Choose product", Toast.LENGTH_SHORT).show();
                   }
               } else {
                   Toast.makeText(requireActivity(), "Enter your data", Toast.LENGTH_SHORT).show();
               }

//               Bundle bundle = new Bundle();
//               bundle.getParcelableArrayList("payed", orders);
           }
//           else {
//               Toast.makeText(requireActivity(), "Choose product", Toast.LENGTH_SHORT).show();
//           }
           try {
               binding.tvAnswer.setText("your order is in obrabotka, delivery 4656. " +
                       "\nOrder after payment " +
                       "\nyour order:" +
                       "\nname" + orders.get(orders.size() - 1).getNameUser() +
                       "\ncount: " + binding.placeCounter.getText().toString());
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
            Bundle bundleOrders = new Bundle();
            bundleOrders.putParcelableArrayList("payed", orders);
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host);
            navController.navigate(R.id.action_navigation_basket_to_payment, bundleOrders);

        });
        binding.btnBack.setOnClickListener(v3 -> {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host);
            navController.navigate(R.id.action_navigation_basket_to_navigation_home);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}