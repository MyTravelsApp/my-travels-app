package com.github.mytravelsapp.presentation.converter;

import com.github.mytravelsapp.business.dto.CategoryDto;
import com.github.mytravelsapp.presentation.model.CategoryModel;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * @author stefani
 */
public class CategoryModelConverter {

    @Inject
    public CategoryModelConverter() {
    }

    public CategoryModel convert(CategoryDto source) {
        if (source == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final CategoryModel target = new CategoryModel(source.getId(),source.getName(),source.isSystem());
        return target;
    }

    public List<CategoryModel> convert(List<CategoryDto> sourceList) {
        List<CategoryModel> resultList;
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

    public CategoryDto convertToDto(CategoryModel source) {
        if (source == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final CategoryDto target = new CategoryDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setIsSystem(source.isSystem());
        return target;
    }

    public List<CategoryDto> convertToDto(List<CategoryModel> sourceList) {
        List<CategoryDto> resultList;
        if (sourceList == null || sourceList.isEmpty()) {
            resultList = Collections.emptyList();
        } else {
            resultList = new ArrayList<>();
            for (final CategoryModel source : sourceList) {
                resultList.add(convertToDto(source));
            }
        }
        return resultList;
    }
}
