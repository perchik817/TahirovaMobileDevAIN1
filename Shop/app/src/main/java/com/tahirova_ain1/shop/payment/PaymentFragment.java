package com.tahirova_ain1.shop.payment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tahirova_ain1.shop.R;
import com.tahirova_ain1.shop.databinding.FragmentPaymentBinding;

public class PaymentFragment extends Fragment {

    FragmentPaymentBinding binding;
    NavController navController;

    public PaymentFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPaymentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize NavController
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);

        binding.cardMbank.setOnClickListener(v1 -> openApp("mbank-app-scheme://"));
        binding.paypal.setOnClickListener(v2 -> openApp("paypal://"));
        binding.visaMastercard.setOnClickListener(v3 -> openApp("visa-app-scheme://"));
        binding.o.setOnClickListener(v4 -> openApp("dengi-app-scheme://"));

        binding.btnBack.setOnClickListener(v5 -> navController.navigate(R.id.action_navigation_basket_to_navigation_home));
    }

    private void openApp(String uriScheme) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriScheme));
        if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store"));
            startActivity(webIntent);
        }
    }
}