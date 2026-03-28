package project.yara_silva.Jp_capacitacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.yara_silva.Jp_capacitacao.models.main.CategoryModel;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, UUID> {
   Optional<CategoryModel> findByNameCategory (String nameCategory);
   List<CategoryModel> findByParent (CategoryModel Parent);
   Optional<List<CategoryModel>> findByParentIsNull();
}
