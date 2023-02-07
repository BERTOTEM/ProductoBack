package Producto.ProductoBack.usecases;

import Producto.ProductoBack.model.ProductDTO;
import Producto.ProductoBack.repositories.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class ListUseCase implements Supplier<Flux<ProductDTO>> {
    private final ProductRepository productRepository;
    private final MapperUtils mapperUtils;

    public ListUseCase(ProductRepository productRepository, MapperUtils mapperUtils) {
        this.productRepository = productRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Flux<ProductDTO> get() {
        return productRepository
                .findAll().map(mapperUtils.mapEntityToProduct());
    }

    public  Flux<ProductDTO> getPages(int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber, 3);
        return productRepository.findAllBy(pageRequest);
    }

}
