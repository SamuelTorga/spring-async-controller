package br.com.samueltorga.springasynccontroller.controller;

import br.com.samueltorga.springasynccontroller.controller.dto.ProductResponse;
import br.com.samueltorga.springasynccontroller.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<ProductResponse>> getProducts(Pageable pageable) {
        log.debug("Get products");
        return ResponseEntity.ok(productService.listProducts(pageable));
    }

    @GetMapping("/products/async")
    public CompletableFuture<ResponseEntity<Page<ProductResponse>>> getProductsAsync(
            Pageable pageable) {
        log.debug("Get products async");
        return productService.listProductsAsync(pageable).thenApply(ResponseEntity::ok);
    }

}
