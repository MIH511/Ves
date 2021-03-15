package com.delivery.ves.Models.Login;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MessageLogin implements Serializable {
    @SerializedName("id")
    String id;
    @SerializedName("0")
    String zero;
    @SerializedName("fullname")
    String fullName;
    @SerializedName("1")
    String one;
    @SerializedName("phone")
    String phoneNumber;
    @SerializedName("2")
    String two;
    @SerializedName("email")
    String email;
    @SerializedName("3")
    String three;
    @SerializedName("gender")
    String gender;
    @SerializedName("4")
    String four;
    @SerializedName("address")
    String address;
    @SerializedName("5")
    String five;
    @SerializedName("birthdate")
    String birthDate;
    @SerializedName("6")
    String six;
    @SerializedName("password")
    String password;
    @SerializedName("7")
    String seven;
    @SerializedName("image")
    String image;
    @SerializedName("8")
    String eight;

    public MessageLogin(String id, String zero, String fullName, String one, String phoneNumber, String two, String email, String three, String gender, String four, String address, String five, String birthDate, String six, String password, String seven, String image, String eight) {
        this.id = id;
        this.zero = zero;
        this.fullName = fullName;
        this.one = one;
        this.phoneNumber = phoneNumber;
        this.two = two;
        this.email = email;
        this.three = three;
        this.gender = gender;
        this.four = four;
        this.address = address;
        this.five = five;
        this.birthDate = birthDate;
        this.six = six;
        this.password = password;
        this.seven = seven;
        this.image = image;
        this.eight = eight;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPassword() {
        return password;
    }

    public String getImage() {
        return image;
    }
}
