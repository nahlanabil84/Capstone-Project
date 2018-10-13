package com.nanodegree.nahla.capstoneproject.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class User implements Parcelable {

    String uId;
    String email;
    String name;
    String profileImg;
    ArrayList<Type> types;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public ArrayList<Type> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<Type> types) {
        this.types = types;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uId);
        dest.writeString(this.email);
        dest.writeString(this.name);
        dest.writeString(this.profileImg);
        dest.writeTypedList(this.types);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.uId = in.readString();
        this.email = in.readString();
        this.name = in.readString();
        this.profileImg = in.readString();
        this.types = in.createTypedArrayList(Type.CREATOR);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
