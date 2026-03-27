package project.yara_silva.Jp_capacitacao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.yara_silva.Jp_capacitacao.dtos.request.CategoryRequestDTO;
import project.yara_silva.Jp_capacitacao.dtos.request.UpdateCategoryRequestDTO;
import project.yara_silva.Jp_capacitacao.dtos.response.CategoryResponseDTO;
import project.yara_silva.Jp_capacitacao.exceptions.CategoryNotFoundException;
import project.yara_silva.Jp_capacitacao.models.main.CategoryModel;
import project.yara_silva.Jp_capacitacao.repository.CategoryRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Transactional
    public CategoryResponseDTO createCategory(CategoryRequestDTO body) {
        CategoryModel category = new CategoryModel(
                body.nameCategory());

        if (body.categoryParent() != null) {
            CategoryModel categoryParent = categoryRepository.findByNameCategory(body.categoryParent())
                    .orElseThrow(CategoryNotFoundException::new);

            List<CategoryModel> subCategories = categoryRepository.findByParent(categoryParent);

            boolean categoryAlreadyExists = subCategories.stream()
                    .anyMatch(subCategory -> subCategory.getNameCategory().equalsIgnoreCase(body.nameCategory()));

            if (categoryAlreadyExists) {
                throw new RuntimeException("Essa categoria já esta cadastrada em: " + categoryParent.getNameCategory());
            }
            category.setParent(categoryParent);
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

    private CategoryResponseDTO convertCategoryToResponseDTO(CategoryModel category) {
        return new CategoryResponseDTO(
                category.getNameCategory());
    }
}
