package br.com.samueltorga.springasynccontroller.service;

import br.com.samueltorga.springasynccontroller.controller.dto.ProductResponse;
import br.com.samueltorga.springasynccontroller.mapper.ProductResponseMapper;
import br.com.samueltorga.springasynccontroller.repository.ProductRepository;
import br.com.samueltorga.springasynccontroller.repository.model.Product;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<ProductResponse> listProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductResponseMapper::map);
    }

    public Page<ProductResponse> listProductsQuery(Pageable pageable) {
        Page<Product> productsPaged = productRepository.findAll(pageable);
        List<ProductResponse> productsWithTags = productRepository.findByProductsInWithTags(productsPaged.getContent())
                .stream()
                .map(ProductResponseMapper::map)
                .toList();
        return new PageImpl<>(productsWithTags, pageable, productsPaged.getTotalElements());
    }

    @Async
    @Transactional(Transactional.TxType.REQUIRED)
    public CompletableFuture<Page<ProductResponse>> listProductsAsync(Pageable pageable) {
        return CompletableFuture.completedFuture(listProducts(pageable));
    }

    @Async
    public CompletableFuture<Page<ProductResponse>> listProductsAsyncQuery(Pageable pageable) {
        return CompletableFuture.completedFuture(listProductsQuery(pageable));
    }

}
