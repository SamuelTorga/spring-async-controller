package br.com.samueltorga.springasynccontroller.repository;

import br.com.samueltorga.springasynccontroller.repository.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.tags WHERE p IN :products")
    List<Product> findByProductsInWithTags(Collection<Product> products);
}
