package Producto.ProductoBack.usecases;

import Producto.ProductoBack.collections.Product;
import Producto.ProductoBack.model.ProductDTO;
import Producto.ProductoBack.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
@Service
@Validated
public class UpdateUseCase  implements  SaveProduct{

    private final ProductRepository productRepository;
    private final MapperUtils mapperUtils;

    public UpdateUseCase(ProductRepository productRepository, MapperUtils mapperUtils) {
        this.productRepository = productRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<String> apply(ProductDTO productDTO) {
        Objects.requireNonNull(productDTO.getId(),"Id of the question is required");
        return productRepository
                .save(mapperUtils.mapperToProduct(productDTO.getId()).apply(productDTO))
                .map(Product::getId);
    }
}
