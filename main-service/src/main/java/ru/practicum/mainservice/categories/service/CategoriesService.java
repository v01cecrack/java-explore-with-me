package ru.practicum.mainservice.categories.service;

import ru.practicum.mainservice.categories.dto.CategoryDto;
import ru.practicum.mainservice.categories.dto.NewCategoryDto;

import java.util.List;

public interface CategoriesService {
    List<CategoryDto> getCategories(Integer from, Integer size);

    CategoryDto getCategoriesId(Long catId);

    CategoryDto createCategories(NewCategoryDto newCategoryDto);

    void deleteCategories(Long catId);

    CategoryDto updateCategories(CategoryDto categoryDto);

}
