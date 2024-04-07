package com.tahirova_ain1.notepad.ui.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.room.Room;

import com.tahirova_ain1.notepad.MainActivity;
import com.tahirova_ain1.notepad.R;
import com.tahirova_ain1.notepad.databinding.FragmentHomeBinding;
import com.tahirova_ain1.notepad.models.Student;
import com.tahirova_ain1.notepad.room.AppDataBase;
import com.tahirova_ain1.notepad.room.StudentDao;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private AppDataBase appDataBase;
    private StudentDao studentDao;
    private ActivityResultLauncher<String> content1;
    private Bitmap bitmap;
    private boolean isImgSelected = false;
    NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnLoadPhoto.setOnClickListener(v1 ->{
            HomeFragment.this.content1.launch("image/*"); //все, что относится к фоткам
        });

        content1 = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        try {
                            bitmap = MediaStore
                                    .Images
                                    .Media
                                    .getBitmap(getContext()
                                            .getContentResolver(), result);
                            binding.imageInput.setImageBitmap(bitmap);
                            isImgSelected = true;
                        } catch (IOException e) {
                            e.printStackTrace();
                            isImgSelected = false;
                        }
                    }
                }
        );

        return root;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnSave.setOnClickListener(v2 -> {
            String nameSurnameStudent=binding.editNameSurname.getText().toString();
            String telStudent=binding.editTelNubmer.getText().toString();

            if(nameSurnameStudent.isEmpty() || telStudent.isEmpty()){
                Toast.makeText(requireActivity(),"Заполните поля ИМЯ КОНТАКТЫ",Toast.LENGTH_LONG).show();
                isImgSelected=false;
            }else {
                if(isImgSelected){
                    ByteArrayOutputStream baos_imageStudent=new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,baos_imageStudent);

                    byte[] imageStudent=baos_imageStudent.toByteArray();

                    Student student= new Student(nameSurnameStudent,telStudent,imageStudent);
                    this.appDataBase= Room.databaseBuilder(binding.getRoot().getContext(),
                            AppDataBase.class,"database").fallbackToDestructiveMigration().allowMainThreadQueries().build();
                    studentDao = appDataBase.studentDao();
                    studentDao.insert(student);
                    Intent intent= new Intent(getActivity(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                    navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
                    navController.navigate(R.id.action_navigation_home_to_navigation_dashboard);
                }else {
                    Toast.makeText(requireActivity(),"Upload photo",
                            Toast.LENGTH_LONG).show();
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