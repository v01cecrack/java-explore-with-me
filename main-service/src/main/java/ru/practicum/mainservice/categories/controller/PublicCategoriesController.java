package ru.practicum.mainservice.categories.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.categories.dto.CategoryDto;
import ru.practicum.mainservice.categories.service.CategoriesService;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Valid
@RequestMapping("/categories")
public class PublicCategoriesController {
    private final CategoriesService categoriesService;

    @GetMapping
    public List<CategoryDto> getCategories(
            @RequestParam(defaultValue = "0")
            @PositiveOrZero Integer from,
            @RequestParam(defaultValue = "10")
            @PositiveOrZero Integer size) {
        return categoriesService.getCategories(from, size);
    }

    @GetMapping("/{catId}")
    public CategoryDto getCategoriesId(@PathVariable Long catId) {
        return categoriesService.getCategoriesId(catId);
    }

}
