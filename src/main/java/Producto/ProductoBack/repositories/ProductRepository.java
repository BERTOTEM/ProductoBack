package Producto.ProductoBack.repositories;

import Producto.ProductoBack.collections.Product;
import Producto.ProductoBack.model.ProductDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product,String> {

    Flux<Product> findProductByName(String name);

    Flux<ProductDTO> findAllBy(Pageable pageable);
}
