package ru.practicum.mainservice.categories.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainservice.categories.model.Categories;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {

    boolean existsCategoriesByName(String name);

    boolean existsCategoriesByNameAndIdNot(String name, Long id);


}
