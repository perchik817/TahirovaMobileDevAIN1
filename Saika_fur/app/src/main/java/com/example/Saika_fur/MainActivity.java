package com.example.Saika_fur;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.Saika_fur.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.saleFragment)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        NavigationUI.setupWithNavController(binding.navView, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination destination, @Nullable Bundle bundle) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(R.id.navigation_zal);
                list.add(R.id.navigation_bed_room);
                list.add(R.id.saleFragment);

                if (list.contains(destination.getId())) {
                    navView.setVisibility(View.GONE);
                } else {
                    navView.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
