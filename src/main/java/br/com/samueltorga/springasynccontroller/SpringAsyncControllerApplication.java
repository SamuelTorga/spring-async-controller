package br.com.samueltorga.springasynccontroller;

import br.com.samueltorga.springasynccontroller.databaseload.DatabaseLoad;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class SpringAsyncControllerApplication implements CommandLineRunner {

    @Value("${load_database}")
    private Boolean shouldLoadDatabase;

    private final DatabaseLoad databaseLoad;

    public static void main(String[] args) {
        SpringApplication.run(SpringAsyncControllerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("Load database is set to {}", shouldLoadDatabase);
        if (Boolean.TRUE.equals(shouldLoadDatabase)) {
            databaseLoad.databaseLoad();
        }
    }
}
