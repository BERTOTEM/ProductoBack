package Producto.ProductoBack.usecases;

import Producto.ProductoBack.model.ProductDTO;
import Producto.ProductoBack.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Function;
@Service
@Validated
public class OthersListUseCase implements Function<String,Flux<ProductDTO>> {

    private final ProductRepository productRepository;
    private final MapperUtils mapperUtils;

    public OthersListUseCase(ProductRepository productRepository, MapperUtils mapperUtils) {
        this.productRepository = productRepository;
        this.mapperUtils = mapperUtils;
    }
    @Override
    public Flux<ProductDTO> apply(String name) {
        return productRepository.findProductByName(name).filter(p1->p1
                        .isState()).filter(p2->p2
                        .isEnabled())
                        .map(mapperUtils.mapEntityToProduct());
    }
}
