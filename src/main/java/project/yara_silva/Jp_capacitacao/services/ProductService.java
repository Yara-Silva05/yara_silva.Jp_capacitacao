package project.yara_silva.Jp_capacitacao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.yara_silva.Jp_capacitacao.dtos.request.ProductRequestDTO;
import project.yara_silva.Jp_capacitacao.dtos.request.UpdateProductRequestDTO;
import project.yara_silva.Jp_capacitacao.dtos.response.ProductFullResponseDTO;
import project.yara_silva.Jp_capacitacao.dtos.response.ProductSimpleResponseDTO;
import project.yara_silva.Jp_capacitacao.exceptions.CategoryNotFoundException;
import project.yara_silva.Jp_capacitacao.exceptions.ProductNotFoundException;
import project.yara_silva.Jp_capacitacao.models.main.CategoryModel;
import project.yara_silva.Jp_capacitacao.models.main.ProductModel;
import project.yara_silva.Jp_capacitacao.repository.CategoryRepository;
import project.yara_silva.Jp_capacitacao.repository.ProductRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public ProductFullResponseDTO createProduct(ProductRequestDTO body) {
        CategoryModel category = categoryRepository.findByNameCategory(body.category())
                .orElseThrow(() -> new RuntimeException("Categoria inválida"));

        ProductModel product = new ProductModel(
                authenticationService.extractUser(),
                body.nameProduct(),
                category,
                body.sku(),
                body.description(),
                body.price(),
                body.costPrice(),
                body.stockQuantity()
        );
        productRepository.saveAndFlush(product);

        return convertProductFullToResponseDTO(product);
    }

    public List<ProductSimpleResponseDTO> getAllProducts() {

        List<ProductModel> products = productRepository.findAll();

        return products.stream()
                .map(this::convertProductSimpleToResponseDTO)
                .toList();
    }

    public ProductFullResponseDTO getByIdFull(UUID id) {
        Optional<ProductModel> product = productRepository.findById(id);
        if (product.isPresent()) {
            return convertProductFullToResponseDTO(product.get());
        } else {
            throw new ProductNotFoundException();
        }
    }

    public ProductSimpleResponseDTO getByIdSimple(UUID id) {
        Optional<ProductModel> product = productRepository.findById(id);
        if (product.isPresent()) {
            return convertProductSimpleToResponseDTO(product.get());
        } else {
            throw new ProductNotFoundException();
        }
    }

    @Transactional
    public ProductFullResponseDTO updateProduct(UUID id, UpdateProductRequestDTO body) {
        ProductModel product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (body.category() != null) {
            if (!body.category().isBlank()) {
                CategoryModel category = categoryRepository.findByNameCategory(body.category())
                        .orElseThrow(CategoryNotFoundException::new);
                product.setCategory(category);
            }
        }

        if (body.nameProduct() != null) {
            if (!body.nameProduct().isBlank()) {
                product.setNameProduct(body.nameProduct());
            }
        }

        if (body.description() != null) {
            if (!body.description().isBlank()) {
                product.setDescription(body.description());
            }
        }

        if (body.price() != null) {
            product.setPrice(body.price());
        }

        if (body.costPrice() != null) {
            product.setCostPrice(body.costPrice());
        }

        if (body.active() != null) {
            product.setActive(body.active());
        }

        product.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product);

        return convertProductFullToResponseDTO(product);
    }

    @Transactional
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }

    private ProductFullResponseDTO convertProductFullToResponseDTO(ProductModel product) {
        return new ProductFullResponseDTO(
                product.getNameProduct(),
                product.getCategory().getNameCategory(),
                product.getSku(),
                product.getDescription(),
                product.getPrice(),
                product.getCostPrice(),
                product.getStockQuantity()
        );
    }

    private ProductSimpleResponseDTO convertProductSimpleToResponseDTO(ProductModel product) {
        return new ProductSimpleResponseDTO(
                product.getCategory().getNameCategory(),
                product.getNameProduct(),
                product.getDescription(),
                product.getPrice(),
                product.getActive()
        );
    }
}
