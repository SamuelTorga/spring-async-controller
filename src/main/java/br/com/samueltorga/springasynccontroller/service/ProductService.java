package br.com.samueltorga.springasynccontroller.service;

import br.com.samueltorga.springasynccontroller.controller.dto.ProductResponse;
import br.com.samueltorga.springasynccontroller.mapper.ProductResponseMapper;
import br.com.samueltorga.springasynccontroller.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<ProductResponse> listProducts(Pageable pageable) {
        log.info("Listing products");
        return productRepository.findAll(pageable).map(ProductResponseMapper::map);
    }

    @Async
    @Transactional(Transactional.TxType.REQUIRED)
    public CompletableFuture<Page<ProductResponse>> listProductsAsync(Pageable pageable) {
        return CompletableFuture.completedFuture(listProducts(pageable));
    }

}
