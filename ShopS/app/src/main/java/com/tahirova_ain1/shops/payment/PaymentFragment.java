package com.tahirova_ain1.shops.payment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import com.tahirova_ain1.shops.R;
import com.tahirova_ain1.shops.databinding.FragmentPaymentBinding;
import com.tahirova_ain1.shops.models.Order;
import com.tahirova_ain1.shops.remotedata.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentFragment extends Fragment {

    FragmentPaymentBinding binding;
    NavController navController;
    List<Order> payedList;
    SharedPreferences preferences;

    public PaymentFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPaymentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        preferences = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);

        if(getArguments() != null) payedList = getArguments().getParcelableArrayList("payed");

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnBack.setOnClickListener(v5 -> navController.navigate(R.id.action_navigation_basket_to_navigation_home));

        binding.btnFinallyPay.setOnClickListener(v2 -> {
            binding.progressBar.setVisibility(View.VISIBLE);
            try {
                for (int i = 0; i < payedList.size(); i++) {
                    Call<Order> apiCall = RetrofitClient.getInstance().getApi().createNewOrder(payedList.get(i));
                    apiCall.enqueue(new Callback<Order>() {
                        @Override
                        public void onResponse(Call<Order> call, Response<Order> response) {
                            if(response.isSuccessful() && response.body() != null){
                                binding.progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(requireActivity(), "success", Toast.LENGTH_SHORT).show();
                                SharedPreferences.Editor prefPayed = preferences.edit();
                                prefPayed.putBoolean("Order", true);
                                prefPayed.commit();
                                binding.tvAnswer.setText("Your order will be in 5 days");
                            }
                        }

                        @Override
                        public void onFailure(Call<Order> call, Throwable throwable) {
                            Toast.makeText(requireActivity(), "Order is not available", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } catch (Exception e){
                Toast.makeText(requireActivity(), "Items were not chosen", Toast.LENGTH_SHORT).show();
            }

        });
    }

}