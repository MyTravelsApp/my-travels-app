package com.github.mytravelsapp.business.converter;

import com.github.mytravelsapp.business.dto.Dto;

import java.util.List;

/**
 * @author fjtorres
 */
public interface Converter<S extends Dto, T> {
    T convert(S source);

    List<T> convert(List<S> sourceList);

    S convertToDto(T target);

    List<S> convertToDto(List<T> targetList);
}
