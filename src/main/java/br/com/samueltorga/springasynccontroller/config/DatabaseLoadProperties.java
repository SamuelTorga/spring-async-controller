package br.com.samueltorga.springasynccontroller.config;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("database.load")
public class DatabaseLoadProperties {

    private boolean enabled = false;
    private DatabaseLoadSize size = DatabaseLoadSize.SMALL;

    @Getter
    @RequiredArgsConstructor
    public enum DatabaseLoadSize {

        SMALL(5, 20), MEDIUM(20, 1000), LARGE(50, 10_000);

        private final int tagsSize;
        private final int productsSize;

    }

}
