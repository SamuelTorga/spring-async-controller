package br.com.samueltorga.springasynccontroller.databaseload;

import br.com.samueltorga.springasynccontroller.config.DatabaseLoadProperties;
import br.com.samueltorga.springasynccontroller.repository.ProductRepository;
import br.com.samueltorga.springasynccontroller.repository.TagRepository;
import br.com.samueltorga.springasynccontroller.repository.model.Product;
import br.com.samueltorga.springasynccontroller.repository.model.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseLoad {

    private static final Random random = new Random();

    private static final int MINIMUM_TAGS_PER_PRODUCT = 5;

    private final ProductRepository productRepository;

    private final TagRepository tagRepository;

    private final DatabaseLoadProperties properties;

    public void databaseLoad() {
        log.debug("Database Load");
        List<Tag> tagsToBeSavedInBatch = new ArrayList<>();
        for (int i = 0; i < properties.getSize().getTagsSize(); i++) {
            Tag tag = new Tag();
            tag.setName("Tag " + i);
            tagsToBeSavedInBatch.add(tag);
        }

        log.debug("Saving tags in batch");
        tagRepository.saveAll(tagsToBeSavedInBatch);

        log.debug("Listing tags");
        List<Tag> tags = tagRepository.findAll();
        log.debug("Tags found: {}", tags.size());

        log.debug("Saving products in batch");
        List<Product> productsToBeSavedInBatch = new ArrayList<>();
        for (int i = 0; i < properties.getSize().getProductsSize(); i++) {

            Product product = new Product();
            product.setName("Product " + i);
            product.setDefaultPrice(BigDecimal.valueOf(random.nextDouble(0, 1000)));

            List<Tag> randomTags = tags.stream()
                    .skip(random.nextInt(0, Math.min(tags.size(), MINIMUM_TAGS_PER_PRODUCT)))
                    .limit(MINIMUM_TAGS_PER_PRODUCT).toList();
            product.setTags(randomTags);

            productsToBeSavedInBatch.add(product);
        }
        productRepository.saveAll(productsToBeSavedInBatch);
        log.debug("Products saved");
    }

    public boolean shouldLoadDatabase() {
        return Boolean.TRUE.equals(properties.isEnabled());
    }
}
