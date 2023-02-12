package Producto.ProductoBack.usecases;

import Producto.ProductoBack.collections.Product;
import Producto.ProductoBack.model.ProductDTO;
import Producto.ProductoBack.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UpdateUseCaseTest {
    @SpyBean
    UpdateUseCase updateUseCase;
    @Autowired
    private  MapperUtils mapperUtils;
    @Mock
    private ProductRepository productRepository;

    @Test
    void UpdateHappyPass() {
        Product product=new Product();
        ProductDTO productDTO = new ProductDTO("500gx","celular",1000
                ,true,8,200,"data:image/jpeg;base64,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",true,13000L);
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setInInventory(productDTO.getInInventory());
        product.setEnabled(productDTO.isEnabled());
        product.setMin(productDTO.getMin());
        product.setMax(productDTO.getMax());
        product.setImg(productDTO.getImg());
        product.setState(productDTO.isState());
        product.setPrice(productDTO.getPrice());

        Mockito.when(productRepository.save(product))
                .thenReturn(Mono.just(mapperUtils.mapperToProduct(productDTO.getId())
                .apply(productDTO)));

        StepVerifier
                .create(updateUseCase.apply(productDTO))
                .expectNextMatches(MonoP -> {

                    assert MonoP.equals("500gx");
                    System.out.println(MonoP);
                    return true;
                })
                .verifyComplete();

    }
}