package com.tahirova_ain1.furniture.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Furniture {
    Long id;
    String category;
    String title;
    String price;
    String description;
    String discount;
    int image;
    byte[] imageBytes;

    public Furniture(Long id, String category, String title, String price, String description, String discount, byte[] imageBytes) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.price = price;
        this.description = description;
        this.discount = discount;
        this.imageBytes = imageBytes;
    }

    public Furniture(String category, String title, String price, String description, String discount, int image) {
        this.category = category;
        this.title = title;
        this.price = price;
        this.description = description;
        this.discount = discount;
        this.image = image;
    }

    public Furniture(String category, String title, String price, String description, int image) {
        this.category = category;
        this.title = title;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    protected Furniture(Parcel in){
        id = in.readLong();
        category = in.readString();
        title = in.readString();
        price = in.readString();
        description = in.readString();
        discount = in.readString();
        image = in.readInt();
    }

    public static final Parcelable.Creator<Furniture> CREATOR = new Parcelable.Creator<Furniture>() {
        @Override
        public Furniture createFromParcel(Parcel in) {
            return new Furniture(in);
        }

        @Override
        public Furniture[] newArray(int size) {
            return new Furniture[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(category);
        dest.writeString(title);
        dest.writeString(price);
        dest.writeString(description);
        dest.writeString(discount);
        dest.writeInt(image);

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }
}
