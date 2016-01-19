package com.github.mytravelsapp.business.dto;

/**
 * Created by stefani on 17/01/2016.
 */
public class CategoryDto {

    private long id;
    private String name;
    private boolean isSystem;

    public CategoryDto(long id, String name, boolean isSystem) {
        this.id = id;
        this.name = name;
        this.isSystem = isSystem;
    }

    public CategoryDto() {
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
