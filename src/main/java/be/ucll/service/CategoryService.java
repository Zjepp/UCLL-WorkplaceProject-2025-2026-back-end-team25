package be.ucll.service;

import be.ucll.model.Category;
import be.ucll.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public Category createCategory(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Naam mag niet leeg zijn");
        }
        if (categoryRepository.existsByName(name)) {
            throw new IllegalArgumentException("Categorie bestaat al");
        }
        Category category = new Category(name);
        return categoryRepository.save(category);
    }

    public java.util.List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
