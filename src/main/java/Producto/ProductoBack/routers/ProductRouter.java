package Producto.ProductoBack.routers;

import Producto.ProductoBack.collections.Product;
import Producto.ProductoBack.model.ProductDTO;
import Producto.ProductoBack.repositories.ProductRepository;
import Producto.ProductoBack.usecases.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMethod;
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
    private final ProductRepository productRepository;

    public ProductRouter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Bean
    @ExceptionHandler(IllegalAccessError.class)
    @RouterOperation(path="/getAllProducts",
            produces={MediaType.APPLICATION_JSON_VALUE},method = RequestMethod.GET,
            beanClass =ProductRouter.class ,
            beanMethod = "getAllProducts",
            operation = @Operation(operationId = "getAllProducts",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "ok",
                                    content = @Content(schema=@Schema(implementation = ProductDTO.class))
                            ),@ApiResponse(responseCode = "400",description = "Bad request")
                    }))
    public RouterFunction<ServerResponse>getAllProducts(ListUseCase listUseCase){
        return route(GET("/getAllProducts"),
                request -> ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listUseCase.get(), ProductDTO.class)));

    }


    @Bean
    @RouterOperation(path="/get/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET,
            beanClass = ProductRouter.class,
            beanMethod = "get",
            operation = @Operation(operationId = "get",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "OK",
                                    content = @Content(schema = @Schema(implementation = ProductDTO.class))
                            ),@ApiResponse(responseCode = "404",description = "Error")

                    },parameters = {
                    @Parameter(in = ParameterIn.PATH,name = "id")}
            )

    )
    public RouterFunction<ServerResponse> get(GetUseCase getUseCase) {
        return route(
                GET("/get/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getUseCase.apply(
                                        request.pathVariable("id")),
                                ProductDTO.class
                        ))
        );
    }


    @Bean
    @RouterOperation(
            path = "/create",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST,
            beanClass = ProductRouter.class,
            beanMethod = "Create",
            operation = @Operation(operationId = "Create",
                    responses = {
                            @ApiResponse (
                                    responseCode = "200",
                                    description = "OK",
                                    content = @Content(schema = @Schema(implementation = ProductDTO.class))
                            ), @ApiResponse(responseCode = "404",description = "Error")
                    },
                    requestBody = @RequestBody(
                            content = @Content(schema = @Schema(
                                    implementation = ProductDTO.class
                            ))

                    )

            ))
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

    @Bean
    @RouterOperation(
            path = "/update",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.PUT,
            beanClass = ProductRouter.class,
            beanMethod = "editProducts",
            operation = @Operation(operationId = "editProducts",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "OK",
                                    content = @Content(schema = @Schema(implementation = ProductDTO.class))
                            ),
                            @ApiResponse(responseCode = "404",description = "Error")
                    }
                    ,
                    requestBody = @RequestBody(
                            content = @Content(schema = @Schema(
                                    implementation = ProductDTO.class
                            ))

                    )//parameters = {
                    //@Parameter(in = ParameterIn.PATH,name = "id")}
            ))
    public  RouterFunction<ServerResponse>editProducts(UpdateUseCase updateUseCase){
        return route(PUT("/update").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProductDTO.class)
                        .flatMap(updateUseCaseDTO -> updateUseCase.apply(updateUseCaseDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );

    }

    @Bean
    @RouterOperation(path="/getName/{name}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET,
            beanClass = ProductRouter.class,
            beanMethod = "getName",
            operation = @Operation(operationId = "getName",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "OK",
                                    content = @Content(schema = @Schema(implementation = ProductDTO.class))
                            ),@ApiResponse(responseCode = "404",description = "Error")
                    },parameters = {
                    @Parameter(in = ParameterIn.PATH,name = "name")}
            )

    )
    public  RouterFunction<ServerResponse>getName(OthersListUseCase othersListUseCase){
        return route(GET("/getName/{name}"),
                request -> ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(othersListUseCase
                                .apply(request.pathVariable("name")), ProductDTO.class))


        );


    }

    @Bean
    @RouterOperation(path="/pagination/{pageNumber}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET,
            beanClass = ProductRouter.class,
            beanMethod = "GetpagesAll",
            operation = @Operation(operationId = "GetpagesAll",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "OK",
                                    content = @Content(schema = @Schema(implementation = ProductDTO.class))
                            ),@ApiResponse(responseCode = "404",description = "Error")
                    },parameters = {
                    @Parameter(in = ParameterIn.PATH,name = "pageNumber")}
            )

    )
    public RouterFunction<ServerResponse> GetpagesAll(ListUseCase listUseCase) {
        return route(GET("/pagination/{pageNumber}"),
                request -> ok().body(listUseCase.getPages(
                        Integer.valueOf(request.pathVariable("pageNumber"))
                ), ProductDTO.class));
    }


    @Bean
    @RouterOperation(path="/changeState/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.PATCH,
            beanClass = ProductRouter.class,
            beanMethod = "patchState",
            operation = @Operation(operationId = "patchState",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "OK",
                                    content = @Content(schema = @Schema(implementation = ProductDTO.class))
                            ),@ApiResponse(responseCode = "500",description  = "Cantidad con error")
                    },parameters = {
                    @Parameter(in = ParameterIn.PATH,name = "id")}
            )

    )
    public RouterFunction<ServerResponse> patchState(DeleteLogicoUseCase deleteLogicoUseCase) {
        return route(
                PATCH("/changeState/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .body(BodyInserters.fromPublisher(deleteLogicoUseCase.apply(request.pathVariable("id")),Product.class))
        );
    }
    @Bean
    @RouterOperation(path="/update/{id}/{quantity}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.PATCH,
            beanClass = ProductRouter.class,
            beanMethod = "updateID",
            operation = @Operation(operationId = "updateID",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "OK",
                                    content = @Content(schema = @Schema(implementation = ProductDTO.class))
                            ),@ApiResponse(responseCode = "404",description = "Error")
                    },parameters = {
                    @Parameter(in = ParameterIn.PATH,name = "id"),
                    @Parameter(in = ParameterIn.PATH,name = "quantity")}
            )

    )
    public RouterFunction<ServerResponse>updateID(UpdateUseCase updateUseCase){
        return route(PATCH("/update/{id}/{quantity}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(updateUseCase
                                .UpdateID(request.pathVariable("id")
                                ,request.pathVariable("quantity")),Product.class))

        );


    }





}
