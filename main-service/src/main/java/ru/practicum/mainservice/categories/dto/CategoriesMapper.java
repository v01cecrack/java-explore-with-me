package ru.practicum.mainservice.categories.dto;

import lombok.experimental.UtilityClass;
import ru.practicum.mainservice.categories.model.Category;

@UtilityClass
public class CategoriesMapper {
    public static CategoryDto toCategoryDto(Category categories) {
        return CategoryDto.builder()
                .id(categories.getId())
                .name(categories.getName())
                .build();
    }

    public static Category toCategories(NewCategoryDto newCategoryDto) {
        return Category.builder()
                .name(newCategoryDto.getName())
                .build();
    }

}
