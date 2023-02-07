package Producto.ProductoBack.routers;

import Producto.ProductoBack.model.ProductDTO;
import Producto.ProductoBack.usecases.CreateUseCase;
import Producto.ProductoBack.usecases.ListUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
@Configuration
public class ProductRouter {



    @Bean
    public RouterFunction<ServerResponse>getall(ListUseCase listUseCase){
        return route(GET("/getAllProducts"),
                request -> ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listUseCase.get(), ProductDTO.class)));

    }


    @Bean
    public RouterFunction<ServerResponse>Create(CreateUseCase createUseCase){
        Function<ProductDTO, Mono<ServerResponse>>executor = productDTO -> createUseCase.apply(productDTO)
                .flatMap(result -> ok()
                .contentType(MediaType.TEXT_PLAIN)
                .bodyValue(result));

        return route(
                POST("/create").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProductDTO.class).flatMap(executor)
        );


    }
}
