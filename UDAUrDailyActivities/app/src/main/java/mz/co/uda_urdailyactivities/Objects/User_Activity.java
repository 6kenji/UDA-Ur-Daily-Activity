package mz.co.uda_urdailyactivities.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;

public class User_Activity implements Serializable, Parcelable {

    private String name, id, description;
    public User_Activity(String name, String id, String description){
        this.name = name;
        this.id = id;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    protected User_Activity(Parcel in) {
        name = in.readString();
        id = in.readString();
        description = in.readString();
    }

    public static final Creator<User_Activity> CREATOR = new Creator<User_Activity>() {
        @Override
        public User_Activity createFromParcel(Parcel in) {
            return new User_Activity(in);
        }

        @Override
        public User_Activity[] newArray(int size) {
            return new User_Activity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(id);
        dest.writeString(description);
    }

    @Override
    public String toString() {
        return "User_Activity{" +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
