package ru.practicum.mainservice.categories.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainservice.categories.model.Category;

public interface CategoriesRepository extends JpaRepository<Category, Long> {

    boolean existsCategoriesByName(String name);

    boolean existsCategoriesByNameAndIdNot(String name, Long id);


}
