package Producto.ProductoBack.usecases;

import Producto.ProductoBack.collections.Product;
import Producto.ProductoBack.model.ProductDTO;
import Producto.ProductoBack.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Function;
@Service
@Validated
public class TrueListsUseCase  {

    private final ProductRepository productRepository;
    private final MapperUtils mapperUtils;

    public TrueListsUseCase(ProductRepository productRepository, MapperUtils mapperUtils) {
        this.productRepository = productRepository;
        this.mapperUtils = mapperUtils;
    }


    public Flux<ProductDTO> apply() {
        return productRepository.findProductByEnabled(true)
                        .filter(productDTO->productDTO
                        .isState()).map(mapperUtils.mapEntityToProduct());



    }


}
