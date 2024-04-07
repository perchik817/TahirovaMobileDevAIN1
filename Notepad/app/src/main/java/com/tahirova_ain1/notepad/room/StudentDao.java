package com.tahirova_ain1.notepad.room;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.tahirova_ain1.notepad.models.Student;

import java.util.List;

public interface StudentDao {
    @Query("select * from students")
    List<Student> getAll();

    @Insert
    void insert(Student student);

    @Delete
    void delete(Student student);
    @Update
    void update(Student student);

    @Query("select * from students order by name_surname asc")
    List<Student> sortAll();
}
