package com.delivery.ves.Models.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("0")
    @Expose
    private String _0;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("1")
    @Expose
    private String _1;
    @SerializedName("name_ar")
    @Expose
    private String nameAr;
    @SerializedName("2")
    @Expose
    private String _2;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("3")
    @Expose
    private String _3;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("4")
    @Expose
    private String _4;
    @SerializedName("cat")
    @Expose
    private String cat;
    @SerializedName("5")
    @Expose
    private String _5;
    @SerializedName("price2")
    @Expose
    private String price2;
    @SerializedName("6")
    @Expose
    private String _6;

    private String quantity;
    private int total;
    private int CountLaundry;
    private int CountCleaningLuandry;

    public Product(String id, String name, String nameAr, String price, String image, String quantity, int total, String cat) {
        this.id = id;
        this.name = name;
        this.nameAr = nameAr;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.total=total;
        this.cat=cat;
    }

    public Product(String id, String name, String nameAr, String price, String image, String quantity, int total, String cat,String CountLaundry,String CountCleaning) {
        this.id = id;
        this.name = name;
        this.nameAr = nameAr;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.total=total;
        this.cat=cat;
        this.CountCleaningLuandry= Integer.parseInt(CountCleaning);
        this.CountLaundry= Integer.parseInt(CountLaundry);
    }

    public String getCount() {
        return quantity;
    }

    public int getTotal() {
        return total;
    }

    public String getId() {
        return id;
    }

    public String get0() {
        return _0;
    }


    public String getName() {
        return name;
    }

    public String get1() {
        return _1;
    }


    public String getNameAr() {
        return nameAr;
    }

    public String get2() {
        return _2;
    }


    public String getImage() {
        return image;
    }

    public String get3() {
        return _3;
    }


    public String getPrice() {
        return price;
    }

    public String get4() {
        return _4;
    }

    public String getCat() {
        return cat;
    }

    public String get5() {
        return _5;
    }

    public String getPrice2() {
        return price2;
    }


    public String getCountLaundry() {
        return String.valueOf(CountLaundry);
    }

    public String getCountCleaningLaundry() {
        return String.valueOf(CountCleaningLuandry);
    }

    public void setCountLaundry(int countLaundry) {
        CountLaundry = countLaundry;
    }

    public void setCountCleaningLuandry(int countCleaningLuandry) {
        CountCleaningLuandry = countCleaningLuandry;
    }
}
