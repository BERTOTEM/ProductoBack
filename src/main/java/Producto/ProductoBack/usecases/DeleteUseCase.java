package Producto.ProductoBack.usecases;

import Producto.ProductoBack.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class DeleteUseCase implements Function<String,Mono<Void>> {

    private final ProductRepository productRepository;

    public DeleteUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Mono<Void> apply(String id) {
        return productRepository.deleteById(id).switchIfEmpty(Mono.defer(()->productRepository.deleteById(id)));
    }
}
