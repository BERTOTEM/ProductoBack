package Producto.ProductoBack.usecases;

import Producto.ProductoBack.collections.Product;
import Producto.ProductoBack.model.ProductDTO;
import Producto.ProductoBack.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;
@Service
@Validated
public class CreateUseCase implements SaveProduct {


    private final ProductRepository productRepository;
    private final MapperUtils mapperUtils;

    public CreateUseCase(ProductRepository productRepository, MapperUtils mapperUtils) {
        this.productRepository = productRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<String> apply(ProductDTO NewProduct) {
        return productRepository.save(mapperUtils
                .mapperToProduct(null)
                .apply(NewProduct))
                .map(Product::getId);
    }
}
