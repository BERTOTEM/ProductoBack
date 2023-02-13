package Producto.ProductoBack.usecases;

import Producto.ProductoBack.model.ProductDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveProduct {
    Mono<String> apply(@Valid ProductDTO productDTO);
}
