package Producto.ProductoBack.usecases;

import Producto.ProductoBack.collections.Product;
import Producto.ProductoBack.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class DeleteUseCase implements Function<String,Mono<Void>> {

    private final ProductRepository productRepository;


    public DeleteUseCase(ProductRepository productRepository, MapperUtils mapperUtils) {
        this.productRepository = productRepository;

    }

    @Override
    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id,"id necesario");
        return productRepository.deleteById(id)
                .switchIfEmpty(Mono.defer(() -> productRepository.deleteById(id)));
    }
}
