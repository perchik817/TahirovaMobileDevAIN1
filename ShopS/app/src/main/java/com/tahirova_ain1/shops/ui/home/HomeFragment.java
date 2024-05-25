package com.tahirova_ain1.shops.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.tahirova_ain1.shops.R;
import com.tahirova_ain1.shops.databinding.FragmentHomeBinding;
import com.tahirova_ain1.shops.models.ModelM;
import com.tahirova_ain1.shops.remotedata.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    SharedPreferences preferences;
    Adapter adapter;
    String emailUserIdentify;
    NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //remember user
        preferences = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        if(getArguments() != null) emailUserIdentify = getArguments().getString("identify");
        if(preferences.getBoolean("loggedin", true)){
            binding.tvIdentify.setVisibility(View.VISIBLE);
            binding.tvIdentify.setText(emailUserIdentify);
        }

        Call<List<ModelM>> apiCall = RetrofitClient.getInstance().getApi().getStoreProducts();
        apiCall.enqueue(new Callback<List<ModelM>>() {
            @Override
            public void onResponse(Call<List<ModelM>> call, Response<List<ModelM>> response) {
                if(response.body() != null){
                    binding.progressBar.setVisibility(View.INVISIBLE);
                    adapter = new Adapter();
                    binding.rvCatalogM.setAdapter(adapter);
                    adapter.setList(response.body());
                } else {
                    Toast.makeText(requireActivity(), "No data from back", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelM>> call, Throwable throwable) {
                Toast.makeText(requireActivity(), "No data", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.basketBtn.setOnClickListener(v-> {
            PopupMenu popupMenu = new PopupMenu(requireActivity(), binding.basketBtn);
            popupMenu.getMenuInflater().inflate(R.menu.action_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getTitle().toString()) {
                        case "Add to basket":
                            navController = Navigation.findNavController(requireActivity(), R.id.nav_host);
                            Bundle bundle = new Bundle();
                            bundle.putParcelableArrayList("basket", adapter.getSelectedIntoBasketList());
                            navController.navigate(R.id.navigation_basket, bundle);
                            break;
                        case "Mark":
                            Toast.makeText(requireActivity(), "Marked", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(requireActivity(), "Default", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });
            popupMenu.show();
        });
        binding.signIn.setOnClickListener(v3 -> {
            navController.navigate(R.id.action_navigation_home_to_navigation_register);
        });
    }

    private void setUpOnBackPressed() {
        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (isEnabled()) {
                    requireActivity().finish();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}