package Producto.ProductoBack.usecases;

import Producto.ProductoBack.collections.Product;
import Producto.ProductoBack.model.ProductDTO;
import Producto.ProductoBack.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;




 @Service
 @Validated
public class DeleteLogicoUseCase implements Function<String, Mono<Product>> {

    private final ProductRepository productRepository;
    private final MapperUtils mapperUtils;

    public DeleteLogicoUseCase(ProductRepository productRepository, MapperUtils mapperUtils) {
        this.productRepository = productRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<Product> apply(String id) {
        Objects.requireNonNull(id, "Id is required");
        return productRepository.findById(id)
                .flatMap(existingProduct -> {
                    existingProduct.setState(false);
                    return productRepository.save(existingProduct);
                });
    }





    }
