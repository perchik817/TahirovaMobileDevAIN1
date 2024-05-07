package com.tahirova_ain1.boredapi.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.tahirova_ain1.boredapi.Prefs;
import com.tahirova_ain1.boredapi.R;
import com.tahirova_ain1.boredapi.databinding.FragmentBoardBinding;

public class BoardFragment extends Fragment {

    FragmentBoardBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentBoardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        new Prefs(requireContext()).saveBoardState();

        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                changeColor();
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                changeColor();
            }
        });

        return root;
    }

    private void changeColor() {

        if(binding.viewPager2.getCurrentItem() == 0){
            binding.iv1.setImageResource(R.drawable.active_bg);
            binding.iv2.setImageResource(R.drawable.grey_bg);
            binding.iv3.setImageResource(R.drawable.grey_bg);
        } else{
            if(binding.viewPager2.getCurrentItem() == 1){
                binding.iv2.setImageResource(R.drawable.active_bg);
                binding.iv1.setImageResource(R.drawable.grey_bg);
                binding.iv3.setImageResource(R.drawable.grey_bg);
            } else{
                binding.iv2.setImageResource(R.drawable.active_bg);
                binding.iv3.setImageResource(R.drawable.grey_bg);
                binding.iv1.setImageResource(R.drawable.grey_bg);
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BoardAdapter boardAdapter = new BoardAdapter();
        binding.viewPager2.setAdapter(boardAdapter);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}