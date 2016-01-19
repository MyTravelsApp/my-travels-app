package com.github.mytravelsapp.persistence.converter;

import com.github.mytravelsapp.business.dto.CategoryDto;
import com.github.mytravelsapp.persistence.entity.Category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * @author stefani
 */
public class CategoryConverter {

    @Inject
    public CategoryConverter() {
    }

    public Category convert(CategoryDto source) {
        if (source == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final Category target = new Category();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setIsSystem(source.isSystem());

        return target;
    }

    public List<Category> convert(List<CategoryDto> sourceList) {
        List<Category> resultList;
        if (sourceList == null || sourceList.isEmpty()) {
            resultList = Collections.emptyList();
        } else {
            resultList = new ArrayList<>();
            for (final CategoryDto source : sourceList) {
                resultList.add(convert(source));
            }
        }
        return resultList;
    }

    public CategoryDto convertToDto(Category source) {
        if (source == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final CategoryDto target = new CategoryDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setIsSystem(source.isSystem());
        return target;
    }

    public List<CategoryDto> convertToDto(List<Category> sourceList) {
        List<CategoryDto> resultList;
        if (sourceList == null || sourceList.isEmpty()) {
            resultList = Collections.emptyList();
        } else {
            resultList = new ArrayList<>();
            for (final Category source : sourceList) {
                resultList.add(convertToDto(source));
            }
        }
        return resultList;
    }
}
