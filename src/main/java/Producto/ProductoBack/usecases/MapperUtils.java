package Producto.ProductoBack.usecases;

import Producto.ProductoBack.collections.Product;
import Producto.ProductoBack.model.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class MapperUtils {

    public Function<ProductDTO, Product> mapperToProduct(String id) {
        return updateProduct -> {
            var product = new Product();
            product.setId(id);
            product.setName(updateProduct.getName());
            product.setEnabled(updateProduct.isEnabled());
            product.setInInventory(updateProduct.isInInventory());
            product.setMin(updateProduct.getMin());
            product.setMax(updateProduct.getMax());
            product.setImg(updateProduct.getImg());
            product.setState(updateProduct.isState());
            product.setPrice(updateProduct.getPrice());

            return product;
        };
    }


    public Function<Product, ProductDTO> mapEntityToProduct() {
        return entity -> new ProductDTO(
                entity.getId(),
                entity.getName(),
                entity.isEnabled(),
                entity.isInInventory(),
                entity.getMin(),
                entity.getMax(),
                entity.getImg(),
                entity.isState(),
                entity.getPrice()
        );
    }
}
