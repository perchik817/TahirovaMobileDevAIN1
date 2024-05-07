package com.tahirova_ain1.boredapi.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tahirova_ain1.boredapi.databinding.FragmentHomeBinding;
import com.tahirova_ain1.boredapi.models.DoModel;
import com.tahirova_ain1.boredapi.remotedata.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private boolean isLoading = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpOnBackPressed();

        binding.btnGo.setOnClickListener(v -> {
            if (!isLoading) {
                fetchData();
            }
        });

        binding.zoomBtn.setOnClickListener(v -> {
            String link = binding.link.getText().toString();
            if (!link.isEmpty()) {
                openLink(link);
            } else {
                showToast("Link is empty");
            }
        });
    }

    private void setUpOnBackPressed() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        requireActivity().finish();
                    }
                });
    }

    private void fetchData() {
        setLoading(true);
        RetrofitBuilder.getInstance().getActivities().enqueue(new Callback<DoModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<DoModel> call, @NonNull Response<DoModel> response) {
                setLoading(false);
                if (response.isSuccessful() && response.body() != null) {
                    DoModel doModel = response.body();
                    binding.activity.setText(doModel.getActivity());
                    binding.price.setText(doModel.getPrice() + "$");
                    binding.link.setText(doModel.getLink());
                    binding.participants.setText(String.valueOf(doModel.getParticipants()));
                    binding.category.setText(doModel.getCategory());
                } else {
                    showToast("Failed to fetch data");
                }
            }

            @Override
            public void onFailure(@NonNull Call<DoModel> call, @NonNull Throwable throwable) {
                setLoading(false);
                showToast("Failed to fetch data: " + throwable.getLocalizedMessage());
            }
        });
    }

    private void setLoading(boolean loading) {
        isLoading = loading;
        if (loading) {
            // Show loading indicator or disable UI elements
            // Example: binding.progressBar.setVisibility(View.VISIBLE);
            binding.btnGo.setEnabled(false);
        } else {
            // Hide loading indicator or enable UI elements
            // Example: binding.progressBar.setVisibility(View.GONE);
            binding.btnGo.setEnabled(true);
        }
    }

    private void openLink(String link) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            startActivity(intent);
        } catch (android.content.ActivityNotFoundException ex) {
            showToast("Failed to open link");
        }
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
