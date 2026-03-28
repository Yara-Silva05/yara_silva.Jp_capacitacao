package project.yara_silva.Jp_capacitacao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.yara_silva.Jp_capacitacao.dtos.request.CategoryRequestDTO;
import project.yara_silva.Jp_capacitacao.dtos.request.UpdateCategoryRequestDTO;
import project.yara_silva.Jp_capacitacao.dtos.response.CategoryResponseDTO;
import project.yara_silva.Jp_capacitacao.exceptions.CategoryAlreadyExistsException;
import project.yara_silva.Jp_capacitacao.exceptions.CategoryNotFoundException;
import project.yara_silva.Jp_capacitacao.models.main.CategoryModel;
import project.yara_silva.Jp_capacitacao.repository.CategoryRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public CategoryResponseDTO createCategory(CategoryRequestDTO body) {
        CategoryModel category = new CategoryModel(body.nameCategory());

        if (body.categoryParent() != null && !body.categoryParent().isBlank()) {
            CategoryModel categoryParent = categoryRepository.findByNameCategory(body.categoryParent())
                    .orElseThrow(CategoryNotFoundException::new);

            List<CategoryModel> subCategories = categoryRepository.findByParent(categoryParent);
            validateCategoryAlreadyExists(subCategories, body.nameCategory(), categoryParent);

            category.setParent(categoryParent);
        } else {
            List<CategoryModel> categoriesWithoutParent = categoryRepository.findByParentIsNull()
                    .orElse(new ArrayList<>());

            validateCategoryAlreadyExists(categoriesWithoutParent, body.nameCategory(), null);
        }

        categoryRepository.save(category);
        return convertCategoryToResponseDTO(category);
    }

    public List<CategoryResponseDTO> getAllCategories() {
        List<CategoryModel> categories = categoryRepository.findAll();

        return categories.stream()
                .map(this::convertCategoryToResponseDTO)
                .toList();
    }

    @Transactional
    public CategoryResponseDTO updateCategory(UUID id, UpdateCategoryRequestDTO body) {
        CategoryModel category = categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        if (body.nameCategory() != null) {
            if (!body.nameCategory().isBlank()) {
                category.setNameCategory(body.nameCategory());
            }
        }
        category.setUpdatedAt(LocalDateTime.now());
        categoryRepository.save(category);

        return convertCategoryToResponseDTO(category);
    }

    @Transactional
    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }

    private void validateCategoryAlreadyExists(List<CategoryModel> categories, String nameCategory, CategoryModel categoryParent) {
        boolean categoryAlreadyExists = categories.stream()
                .anyMatch(cat -> cat.getNameCategory().equalsIgnoreCase(nameCategory));

        if (categoryAlreadyExists) {
            if (categoryParent != null) {
                throw new CategoryAlreadyExistsException("Essa categoria já está cadastrada em: " + categoryParent.getNameCategory());
            }
            throw new CategoryAlreadyExistsException("Essa categoria já está cadastrada como categoria principal");
        }
    }

    private CategoryResponseDTO convertCategoryToResponseDTO(CategoryModel category) {
        return new CategoryResponseDTO(
                category.getId(),
                category.getNameCategory());
    }
}
