package ru.practicum.mainservice.categories.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.mainservice.categories.dto.CategoriesMapper;
import ru.practicum.mainservice.categories.dto.CategoryDto;
import ru.practicum.mainservice.categories.dto.NewCategoryDto;
import ru.practicum.mainservice.categories.model.Category;
import ru.practicum.mainservice.categories.repository.CategoriesRepository;
import ru.practicum.mainservice.event.repository.EventRepository;
import ru.practicum.mainservice.exception.ConflictException;
import ru.practicum.mainservice.exception.ObjectNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {
    private final CategoriesRepository categoriesRepository;
    private final EventRepository eventRepository;


    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> getCategories(Integer from, Integer size) {
        int offset = from > 0 ? from / size : 0;
        PageRequest page = PageRequest.of(offset, size);
        List<Category> categoriesList = categoriesRepository.findAll(page).getContent();
        log.info("Получение списка категорий");
        return categoriesList.stream().map(CategoriesMapper::toCategoryDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoriesId(Long catId) {
        Category categories = getCategoriesIfExist(catId);
        return CategoriesMapper.toCategoryDto(categories);
    }

    @Override
    public CategoryDto createCategories(NewCategoryDto newCategoryDto) {
        if (categoriesRepository.existsCategoriesByName(newCategoryDto.getName())) {
            throw new ConflictException("Такая категория уже есть");
        }
        Category categories = categoriesRepository.save(CategoriesMapper.toCategories(newCategoryDto));
        log.info("Сохранение категории: {}", newCategoryDto.getName());
        return CategoriesMapper.toCategoryDto(categories);
    }

    @Override
    public void deleteCategories(Long catId) {
        var category = categoriesRepository.findById(catId).orElseThrow(() -> new ObjectNotFoundException("Не найдена выбранная категория"));
        if (eventRepository.existsEventsByCategory_Id(catId)) {
            throw new ConflictException("Такого id нет");
        }
        categoriesRepository.deleteById(catId);
        log.info("Удаление категории: c id: {}", catId);
    }

    @Override
    public CategoryDto updateCategories(CategoryDto categoryDto) {
        Category categories = getCategoriesIfExist(categoryDto.getId());
        if (categoriesRepository.existsCategoriesByNameAndIdNot(categoryDto.getName(), categoryDto.getId())) {
            throw new ConflictException("Такая категория уже есть");
        }
        categories.setName(categoryDto.getName());
        log.info("Изменение категории: c id: {}", categoryDto.getId());
        return CategoriesMapper.toCategoryDto(categoriesRepository.save(categories));
    }

    private Category getCategoriesIfExist(Long catId) {
        return categoriesRepository.findById(catId).orElseThrow(
                () -> new ObjectNotFoundException("Не найдена выбранная категория"));
    }

}
