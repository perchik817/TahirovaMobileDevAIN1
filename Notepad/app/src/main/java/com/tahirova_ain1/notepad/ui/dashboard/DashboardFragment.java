package com.tahirova_ain1.notepad.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.tahirova_ain1.notepad.databinding.FragmentDashboardBinding;
import com.tahirova_ain1.notepad.room.AppDataBase;
import com.tahirova_ain1.notepad.room.StudentDao;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    private AppDataBase appDatabase;
    private StudentDao studentDao;
    private StudentAdapter studentAdapter;
    RecyclerView rvNoteBook;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        rvNoteBook = binding.rvNotepad;
        studentAdapter = new StudentAdapter();
        rvNoteBook.setAdapter(studentAdapter);

        appDatabase = Room.databaseBuilder(binding.getRoot().getContext(),
                AppDataBase.class,
                "database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        studentDao = appDatabase.studentDao();
        studentAdapter.setList(studentDao.getAll());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}