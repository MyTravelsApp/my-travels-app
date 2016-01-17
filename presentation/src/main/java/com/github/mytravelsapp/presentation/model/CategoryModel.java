package com.github.mytravelsapp.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by stefani on 14/01/2016.
 */
public class CategoryModel implements Serializable{

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
}
