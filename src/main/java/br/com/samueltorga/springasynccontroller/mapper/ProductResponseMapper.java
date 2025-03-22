package br.com.samueltorga.springasynccontroller.mapper;

import br.com.samueltorga.springasynccontroller.controller.dto.ProductResponse;
import br.com.samueltorga.springasynccontroller.repository.model.Product;
import br.com.samueltorga.springasynccontroller.repository.model.Tag;

import java.util.List;

public interface ProductResponseMapper {

    static ProductResponse map(Product product) {
        List<ProductResponse.TagResponse> tags = map(product.getTags());
        return new ProductResponse(product.getName(), tags);
    }

    static List<ProductResponse.TagResponse> map(List<Tag> tags) {
        return tags.stream()
                .map(tag -> new ProductResponse.TagResponse(tag.getId(), tag.getName()))
                .toList();
    }

}
