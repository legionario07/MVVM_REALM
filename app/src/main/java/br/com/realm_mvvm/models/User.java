package br.com.realm_mvvm.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Patterns;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject implements Parcelable {

    @PrimaryKey
    private Integer id;
    private String name;
    private String email;


    public User(){

    }

    public User(String name, String email){
        this();
        this.name = name;
        this.email = email;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.email);
    }

    protected User(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.email = in.readString();
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

    public String validModel() {

        StringBuilder validStr = new StringBuilder();

        if (getName() == null || getName().isEmpty()) {
            validStr.append("Name Invalid");
        } else if (getEmail() == null || getEmail().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches()){
            validStr.append("Email invalid");
        }


        return validStr.toString();
    }
}
