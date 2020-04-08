package com.example.myapplicationcomponnetdatadeliver;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private int age;
    private String name;
    private float tall;

    public User(){

    }


    protected User(Parcel in) {
        age = in.readInt();
        name = in.readString();
        tall = in.readFloat();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User( in );
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTall() {
        return tall;
    }

    public void setTall(float tall) {
        this.tall = tall;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt( age );
        dest.writeString( name );
        dest.writeFloat( tall );
    }
}
