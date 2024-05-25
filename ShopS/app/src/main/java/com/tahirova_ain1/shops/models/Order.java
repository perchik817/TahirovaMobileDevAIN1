package com.tahirova_ain1.shops.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Order implements Parcelable, Serializable {
    @SerializedName("username")
    @Expose
    String nameUser;
    @SerializedName("address")
    @Expose
    String addressUser;
    @SerializedName("image")
    @Expose
    String image;
    @SerializedName("title")
    @Expose
    String nameProduct;
    @SerializedName("price")
    @Expose
    String priceProduct;

    @SerializedName("counter_product")
    @Expose
    int productCount;

    public Order(String nameUser, String addressUser, String image, String nameProduct, String priceProduct, int productCount) {
        this.nameUser = nameUser;
        this.addressUser = addressUser;
        this.image = image;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.productCount = productCount;
    }

    protected Order(Parcel in) {
        nameUser = in.readString();
        addressUser = in.readString();
        image = in.readString();
        nameProduct = in.readString();
        priceProduct = in.readString();
        productCount = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameUser);
        dest.writeString(addressUser);
        dest.writeString(image);
        dest.writeString(nameProduct);
        dest.writeString(priceProduct);
        dest.writeInt(productCount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getAddressUser() {
        return addressUser;
    }

    public void setAddressUser(String addressUser) {
        this.addressUser = addressUser;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(String priceProduct) {
        this.priceProduct = priceProduct;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "nameUser='" + nameUser + '\'' +
                ", addressUser='" + addressUser + '\'' +
                ", image='" + image + '\'' +
                ", nameProduct='" + nameProduct + '\'' +
                ", priceProduct='" + priceProduct + '\'' +
                ", productCount=" + productCount +
                '}';
    }
}
