package com.tahirova_ain1.notepad.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
@Entity(tableName = "students")
public class Student {
    @PrimaryKey(autoGenerate = true)
    long id;
    @ColumnInfo(name = "name_surname")
    private String nameSurname;
    @ColumnInfo(name = "tel_number")
    private String telNumber;
    @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    @Ignore
    public Student(long id, String nameSurname, String telNumber, byte[] image) {
        this.id = id;
        this.nameSurname = nameSurname;
        this.telNumber = telNumber;
        this.image = image;
    }

    public Student(String nameSurname, String telNumber, byte[] image) {
        this.nameSurname = nameSurname;
        this.telNumber = telNumber;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
