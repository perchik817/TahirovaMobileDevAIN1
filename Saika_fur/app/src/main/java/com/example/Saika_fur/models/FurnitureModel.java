package com.example.Saika_fur.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class FurnitureModel implements Parcelable {

    private long id;
    private String category;
    private String title;
    private String price;
    private String description;
    private String discount;
    private int imageInt;
    private byte[] image;

    public FurnitureModel(long id, String category, String title, String price, String description, String discount, byte[] image) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.price = price;
        this.description = description;
        this.discount = discount;
        this.image = image;
    }

    public FurnitureModel(String category, String title, String price, String description, String discount, int imageInt) {
        this.category = category;
        this.title = title;
        this.price = price;
        this.description = description;
        this.discount = discount;
        this.imageInt = imageInt;
    }

    public FurnitureModel(String category, String title, String price, String description, int imageInt) {
        this.category = category;
        this.title = title;
        this.price = price;
        this.description = description;
        this.imageInt = imageInt;
    }

    protected FurnitureModel(Parcel in) {
        id = in.readLong();
        category = in.readString();
        title = in.readString();
        price = in.readString();
        description = in.readString();
        discount = in.readString();
        imageInt = in.readInt();
        image = in.createByteArray();
    }

    public static final Creator<FurnitureModel> CREATOR = new Creator<FurnitureModel>() {
        @Override
        public FurnitureModel createFromParcel(Parcel in) {
            return new FurnitureModel(in);
        }

        @Override
        public FurnitureModel[] newArray(int size) {
            return new FurnitureModel[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public int getImageInt() {
        return imageInt;
    }

    public void setImageInt(int imageInt) {
        this.imageInt = imageInt;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

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
        dest.writeInt(imageInt);
        dest.writeByteArray(image);
    }
}
