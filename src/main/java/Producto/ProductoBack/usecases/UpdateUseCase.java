package Producto.ProductoBack.usecases;

import Producto.ProductoBack.collections.Product;
import Producto.ProductoBack.model.ProductDTO;
import Producto.ProductoBack.repositories.ProductRepository;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.Integer.parseInt;

@Service
@Validated
public class UpdateUseCase  implements  SaveProduct{

    private final ProductRepository productRepository;
    private final MapperUtils mapperUtils;

    public UpdateUseCase(ProductRepository productRepository, MapperUtils mapperUtils) {
        this.productRepository = productRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<String> apply(ProductDTO productDTO) {
        Objects.requireNonNull(productDTO.getId(),"Id of the question is required");
        return productRepository
                .save(mapperUtils.mapperToProduct(productDTO.getId()).apply(productDTO))
                .map(Product::getId);
    }


    public  Mono<Product>UpdateID(String id,String quantity){

        return productRepository.findById(id)
                .flatMap(productold -> {
                            if (((productold.getInInventory()-productold.getMin()) >= parseInt(quantity))
                                    && productold.getMax()>=parseInt(quantity)) {
                                productold.setInInventory(productold.getInInventory() - parseInt(quantity));
                                if (productold.getInInventory()==productold.getMin()){
                                    productold.setEnabled(false);
                                }
                            } else {
                                productold.setInInventory(productold.getInInventory());

                                return Mono.error(new IllegalAccessException("hola"));

                            }
                            return productRepository.save(productold);
                }
                );
    }


}
