package Producto.ProductoBack.usecases;

import Producto.ProductoBack.model.ProductDTO;
import Producto.ProductoBack.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class GetUseCase implements Function<String, Mono<ProductDTO>> {


    private final ProductRepository productRepository;
    private final MapperUtils mapperUtils;

    public GetUseCase(ProductRepository productRepository, MapperUtils mapperUtils) {
        this.productRepository = productRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<ProductDTO> apply(String id) {
        Objects.requireNonNull(id, "Id is required");
        return productRepository.findById(id)
                .map(mapperUtils.mapEntityToProduct());

    }
}
