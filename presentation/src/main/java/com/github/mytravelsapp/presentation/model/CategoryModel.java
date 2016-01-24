package com.github.mytravelsapp.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by stefani on 14/01/2016.
 */
public class CategoryModel implements Parcelable{

    public static final long DEFAULT_ID = -1;

    private long id = DEFAULT_ID;

    private String name;

    private boolean isSystem;

    public CategoryModel(long id, String name, boolean isSystem) {
        this.id = id;
        this.name = name;
        this.isSystem = isSystem;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSystem() {
        return isSystem;
    }

    public void setIsSystem(boolean isSystem) {
        this.isSystem = isSystem;
    }

    /**
     * Class constructor. Read from {@link android.os.Parcel} object.
     *
     * @param in
     *            Input data object.
     */
    public CategoryModel(final Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<CategoryModel> CREATOR = new Creator<CategoryModel>() {
        public CategoryModel createFromParcel(Parcel in) {
            return new CategoryModel(in);
        }

        public CategoryModel[] newArray(int size) {
            return new CategoryModel[size];
        }
    };

    /**
     * Write this object to {@link android.os.Parcel} destination object.
     */
    @Override
    public void writeToParcel(final Parcel out, final int flags) {
        out.writeLong(getId());
        out.writeString(getName());
    }

    /**
     * Read from {@link android.os.Parcel} object to this object.
     *
     * @param in
     *            Input data.
     */
    private void readFromParcel(Parcel in) {
        this.setId(in.readLong());
        this.setName(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryModel that = (CategoryModel) o;

        if (id != that.id) return false;
        if (isSystem != that.isSystem) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + (isSystem ? 1 : 0);
        return result;
    }
}
