package com.github.mytravelsapp.business.dto;

/**
 * Created by stefani on 17/01/2016.
 */
public class CategoryDto {

    private final long id;
    private final String name;

    public CategoryDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
