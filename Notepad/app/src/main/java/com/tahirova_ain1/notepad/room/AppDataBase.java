package com.tahirova_ain1.notepad.room;

import androidx.room.RoomDatabase;

public abstract class AppDataBase extends RoomDatabase {
    public abstract StudentDao studentDao();
}
