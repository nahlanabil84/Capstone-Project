package com.nanodegree.nahla.capstoneproject.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Type implements Parcelable {

    String typeId;
    String typeTitle;
    String typeColor;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeTitle() {
        return typeTitle;
    }

    public void setTypeTitle(String typeTitle) {
        this.typeTitle = typeTitle;
    }

    public String getTypeColor() {
        return typeColor;
    }

    public void setTypeColor(String typeColor) {
        this.typeColor = typeColor;
    }

    public static Creator<Type> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.typeId);
        dest.writeString(this.typeTitle);
        dest.writeString(this.typeColor);
    }

    public Type() {
    }

    protected Type(Parcel in) {
        this.typeId = in.readString();
        this.typeTitle = in.readString();
        this.typeColor = in.readString();
    }

    public static final Creator<Type> CREATOR = new Creator<Type>() {
        @Override
        public Type createFromParcel(Parcel source) {
            return new Type(source);
        }

        @Override
        public Type[] newArray(int size) {
            return new Type[size];
        }
    };
}
