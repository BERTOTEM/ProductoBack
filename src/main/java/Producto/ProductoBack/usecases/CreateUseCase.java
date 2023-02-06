package Producto.ProductoBack.usecases;

import Producto.ProductoBack.model.ProductDTO;
import reactor.core.publisher.Mono;

public class CreateUseCase implements SaveProduct {
    @Override
    public Mono<String> apply(ProductDTO productDTO) {
        return null;
    }
}
