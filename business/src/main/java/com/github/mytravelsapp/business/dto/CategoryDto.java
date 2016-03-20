package com.github.mytravelsapp.business.dto;

import java.util.Objects;

/**
 * Created by stefani on 17/01/2016.
 */
public class CategoryDto implements Dto {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryDto)) return false;
        CategoryDto that = (CategoryDto) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(isSystem(), that.isSystem()) &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), isSystem());
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
