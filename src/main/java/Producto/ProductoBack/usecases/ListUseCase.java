package Producto.ProductoBack.usecases;

import Producto.ProductoBack.model.ProductDTO;
import Producto.ProductoBack.repositories.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
        PageRequest pageRequest = PageRequest.of(pageNumber, 6);
        return productRepository.findAllBy(pageRequest).filter(p1->p1
                .isState());
    }

    public Mono<Integer> getTotalPages() {
        Mono<Integer> result = productRepository.count().map(count -> (int) Math.ceil(count / 5) + 1);
        return result;

    }

}
